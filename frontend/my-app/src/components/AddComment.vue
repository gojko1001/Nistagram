<template>
    <div id="discoverPage">
      <br>
        <div>
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
            <button class="heart inter" v-bind:class="{'black': !img.liked, 'red': img.liked}">
              <i class="fas fa-heart"></i>
            </button>
            <span>{{img.numLikes}}</span>
            <b-card-text>
                  <span><b>{{img.username}}:  </b></span>{{img.description}}
                  <br>
                  <span v-for="(tag,t) in img.tags" :key="t">#{{tag.name}}  </span>
              </b-card-text>
              <hr>
              <span v-for="(comm,c) in img.comments" :key="c">
                  <span><b>{{comm.username}}:  </b></span>{{comm.text}}<br>
              </span>
              <br>
              <b-input-group>
                  <b-form-input v-model="form.text" placeholder="Add comment"></b-form-input>
                  <b-input-group-append>
                      <b-button variant="outline-dark" type="submit" @click="onSubmit">Post</b-button>
                  </b-input-group-append>
              </b-input-group>
          </b-card>              
        </div>
    </div>
</template>



<script>
import { getEmailFromToken } from '../util/token';
export default {
  name: 'AddComment',
  data(){
        return{
            username:'',
            img: {
              location:{},
              numLikes:'',
              liked: false,
            },
            postId:'',
            form: {
                postId: 0,
                username: '',
                text: '',
            },
        }
    },
  mounted: function(){
    this.postId = this.$route.params.id;
    this.username = getEmailFromToken();
    this.axios.get('/image/' + this.postId)
        .then(response => { this.img = response.data;
                            if(this.img.image){
                              this.img.imageBytes = 'data:image/jpeg;base64,' + this.img.imageBytes; 
                            }else{
                              this.img.imageBytes = 'data:video/mp4;base64,' + this.img.imageBytes;
                            } 
                            this.img.numLikes = this.img.likes.length; 
                            // TODO: izmeniti ako ostane vremena
                            for(var like of this.img.likes){
                              if(like.username == this.username){
                                  this.img.liked = true;
                              }
                            }
        })
        .catch(error => { console.log(error);
                            this.makeToast("Error occured. Try again later.", "danger");
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
        onSubmit() {
        console.log(this.form);
        this.form.postId = this.postId;
        this.form.username = getEmailFromToken();
        this.axios.post('/comment', this.form)
          .then(response => { console.log(response.data);
                              this.makeToast("Comment has been posted.", "success");
                              window.location.href = '/profile/' + this.form.username;
                            })
          .catch(error => { console.log(error);
                            this.makeToast("Error occured.", "danger");
                          })
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
