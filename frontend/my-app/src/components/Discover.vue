<template>
    <div id="discoverPage">
      <br>
        <div v-for="(img,i) in info" :key="i">
          <b-card
              tag="article"
              style="max-width: 30rem; background:transparent; box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);display:block; margin-left:auto; margin-right:auto"
              class="mb-2">
            <h4>@{{img.username}}</h4>
            <p style="color:blue">{{img.location.name}}</p>
            <img v-if="img.image" v-bind:src="img.imageBytes" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
            <video autoplay controls v-if="!img.image" v-bind:src="img.imageBytes" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
              The video is not supported by your browser.
            </video>
            <br>
            <button class="heart inter" v-bind:class="{'black': !img.liked, 'red': img.liked}" @click="likePost(img.id)">
              <i class="fas fa-thumbs-up"></i>
            </button>
            <span>{{img.numLikes}}</span>
            <button class="heart inter" v-bind:class="{'black': !img.liked, 'red': img.liked}" @click="likePost(img.id)" style="margin-left:20px">
              <i class="fas fa-thumbs-down"></i>
            </button>
            <span>{{img.numLikes}}</span>
            <router-link v-if="username != null" :to="{ name: 'AddComment', params: { id: img.id} }" class="inter link">
              <i class="far fa-comment"></i>
            </router-link>
            <router-link v-if="username != null" :to="{ name: 'AddComment', params: { id: img.id} }" class="inter" style="margin-left:240px">
              <i class="fas fa-bookmark"></i>
            </router-link>
            <b-card-text>
              <span><b>{{img.username}}:  </b></span>{{img.description}}
              <br>
              <span v-for="(tag,t) in img.hashtags" :key="t">
                  #{{tag.name}}
              </span>
            </b-card-text>
            <hr>
            <span v-for="(comm,c) in img.comments" :key="c">
              <span><b>{{comm.username}}:  </b></span>{{comm.text}}<br>
            </span>
          </b-card>              
        </div>
        <a v-if="username != null" href="/postimage" class="float">
            <i class="fas fa-plus my-float"></i>
        </a>
    </div>
</template>



<script>
import { SERVER_NOT_RESPONDING, USER_PATH } from '../util/constants';
import { getEmailFromToken } from '../util/token';
export default {
  name: 'Discover',
  data(){
        return{
            user: '',
            username:'',
            info: [{
              numLikes:'',
              liked: false
            }],
            liked: false,
            formLike:{
              postId: 0,
              username:''
            },
        }
    },
  mounted: function(){
    this.username = getEmailFromToken();
    if(this.username != null){
        this.axios.get(USER_PATH + '/' + this.username, {   headers:{
                                                                Authorization: "Bearer " + localStorage.getItem('JWT'),
                                                            }                                          
            }).then(response => {
                                this.user = response.data;
                                console.log(response.data);
            }).catch(error => { if(!error.response) {
                                    this.makeToast(SERVER_NOT_RESPONDING, "warning");
                                    return
                                }
            })
    }
    this.$nextTick(function () {
      this.axios.get('/image/discover/' + this.username)
                  .then(response => { this.info = response.data;
                                      for(let i=0; i< response.data.length; i++){
                                          if(this.info[i].image){
                                            this.info[i].imageBytes = 'data:image/jpeg;base64,' + this.info[i].imageBytes; 
                                          }else{
                                            this.info[i].imageBytes = 'data:video/mp4;base64,' + this.info[i].imageBytes;
                                          }
                                          this.info[i].numLikes = this.info[i].likes.length;
                                          for(var like of this.info[i].likes){
                                            if(like.username == this.username){
                                                this.info[i].liked = true;
                                            }
                                          }
                                      }  
                  }).catch(error => { console.log(error);
                                      this.makeToast("Error occurred.", "danger");
          });
    })
  },
  methods:{
        makeToast(message, variant) {
            this.$bvToast.toast(message, {
                                title: `Nistagram`,
                                autoHideDelay: 5000,
                                variant: variant,
                                toaster: 'b-toaster-bottom-right',
                                solid: true,
                                appendToast: false
                            })
        },
        likePost(id) {
        console.log(this.form);
        this.formLike.postId = id;
        this.formLike.username = getEmailFromToken();
        if(this.formLike.username != null){
          this.axios.post('/like', this.formLike)
          .then(response => { console.log(response.data);
                              this.makeToast("Liked !!!", "success");
                            })
          .catch(error => { console.log(error);
                            this.makeToast("Error occured.", "danger");
                          })
        }else{
          this.makeToast("Please log in.", "info");
        }
        
      },
    }
}
</script>



<style scoped>
.float{
	position:fixed;
	width:60px;
	height:60px;
	bottom:40px;
	right:40px;
	background-color:#155CA2;
	color:white;
	border-radius:50px;
	text-align:center;
	box-shadow: 2px 2px 3px #999;
}
.my-float{
	margin-top:22px;
}
.inter{
  color:black;
  font-size: 25px;
  background: transparent;
  border: none;
}
.link{
  margin-left: 20px;
}
.black {
  color: black;
}
.red {
  color: red;
}
</style>
