<template>
    <div id="searchPage">
      <div v-if="searchingType == 'profile'">
        <div class="profile" v-for="(res,i) in profiles" :key="i" :res="res">
          <div v-on:click="goToProfile(res.username)">
            <img src="../assets/user-no-picture.png" class="profile_image" alt="Profile picture">
            <div class="profile_name"><i v-if="res.status === 'APPROVED'" class="fas fa-user-check"/> {{res.fullName}}</div>
            <div class="profile_title">@{{res.username}}</div>
          </div>
        </div>
      </div>

      <br>

      <div v-if="searchingType == 'tag' || searchingType == 'location'">
        <br>
        <div v-for="(img,i) in info" :key="i">
          <b-card
              tag="article"
              style="max-width: 30rem; background:transparent; box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);display:block; margin-left:auto; margin-right:auto"
              class="mb-2">
            <h4 @click="goToProfile(img.username)" class="clickable">@{{img.username}}</h4>
            <h6 style="margin-top:-30px; margin-left: 350px">{{img.timestamp | formatDate}}</h6>
            <button v-if="username != null" style="margin-top:-30px; margin-left: 390px" class="heart inter" @click="reportPost(img.mediaId)">
              <i class="fa fa-ban fa-fw"></i>
            </button>
            
            <p style="color:blue">{{img.location.name}}</p>

            <div v-for="(img, q) in img.imageBytes" :key="'D'+q">
                <img v-if="img.image" v-bind:src="img.imageByte" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
                <video autoplay controls v-if="!img.image" v-bind:src="img.imageByte" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
                    The video is not supported by your browser.
                </video>
            </div>
            <div style="margin-top:-30px; margin-left:20px">
              <span v-for="(tag,t) in img.userTags" :key="'UT' + t">
                <i class="fas fa-user-tag"></i> {{tag.username}}
              </span>
            </div>

            <br><br>
            <button class="heart inter" v-bind:class="{'black': !img.liked, 'red': img.liked}" @click="likePost(img.id, true)">
              <i class="fas fa-thumbs-up"></i>
            </button>
            <span>{{img.numLikes}}</span>
            <button class="heart inter" v-bind:class="{'black': !img.disliked, 'red': img.disliked}" @click="likePost(img.id, false)" style="margin-left:20px">
              <i class="fas fa-thumbs-down"></i>
            </button>
            <span>{{img.numDislikes}}</span>
            <router-link :to="{ name: 'AddComment', params: { id: img.id} }" class="inter link">
              <i class="far fa-comment"></i>
            </router-link>
            <router-link :to="{ name: 'AddComment', params: { id: img.id} }" class="inter" style="margin-left:240px">
              <i class="fas fa-bookmark"></i>
            </router-link>
            <b-card-text>
              <span @click="goToProfile(img.username)" class="clickable"><b>{{img.username}}:  </b></span>{{img.description}}
              <br>
              <span v-for="(tag,t) in img.hashtags" :key="t">
                  #{{tag.name}}
              </span>
            </b-card-text>
            <hr>
            <span v-for="(comm,c) in img.comments" :key="c">
              <span @click="goToProfile(comm.username)" class="clickable"><b>{{comm.username}}:  </b></span>{{comm.text}}<br>
            </span>
          </b-card>              
        </div>
        <a v-if="username != null" href="/postimage" class="float">
            <i class="fas fa-plus my-float"></i>
        </a>
      </div>
      <h3 v-if="info.length == 0">There is no any photo: {{this.searchingInput}}</h3>
      <h3 v-if="profiles.length == 0 && searchingType == 'profile'">There is no user with username: {{this.searchingInput}}</h3>
    </div>
</template>

<script>
import { SEARCH_USER_PATH, SEARCH_TAG_PATH, SEARCH_LOCATION_PATH, SERVER_NOT_RESPONDING, USER_PATH} from '../util/constants';
import { getUsernameFromToken, getToken } from '../util/token';
export default {
  name: 'Search',
  data(){
        return{
            user: '',
            username:'',
            searchingType: '',
            searchingInput: '',
            profiles: [],
            results: [],
            info: [{
              location:{name:''},
              imageBytes:[{
                image:'',
                imageByte:''
              }],
              userTags:[{username:''}],
              numLikes:'',
              numDislikes:'',
              liked: false,
              disliked: false
            }],
            liked: false,
            formLike:{
              postId: 0,
              username:''
            },
            report:{
              requestedBy: '',
              mediaId:''
            },
        }
    },
  mounted: function(){
    this.searchingType = localStorage.getItem("name");
    this.searchingInput = localStorage.getItem("find");

    this.username = getUsernameFromToken();
    if(this.username != null){
      this.axios.get(USER_PATH + '/' + this.username, {   
      headers:{ Authorization: "Bearer " + getToken(),}                                          
      }).then(response => {
        this.user = response.data;
        console.log(response.data);
      }).catch(error => { 
        if(!error.response) {
          this.makeToast(SERVER_NOT_RESPONDING, "warning");
          return
        }
      })
    }

    if(this.searchingType == "profile"){
      this.axios.get(SEARCH_USER_PATH + "?username=" + this.searchingInput)
      .then(response => { 
        this.profiles = response.data;
        console.log(this.profiles);
      })
      .catch(error => { 
        console.log(error);
        this.makeToast("Error occured.", "danger");
      })
    }

    if(this.searchingType == "tag"){
      this.axios.get(SEARCH_TAG_PATH + "?tag=" + this.searchingInput)
      .then(response => { 
        this.info = response.data;
        this.loadResults(response.data.length);
      })
      .catch(error => { 
        console.log(error);
        this.makeToast("Error occured.", "danger");
      })
    }

    if(this.searchingType == "location"){
      this.axios.get(SEARCH_LOCATION_PATH + "?location=" + this.searchingInput)
      .then(response => { 
        this.info = response.data;
        this.loadResults(response.data.length); 
      }).catch(error => { 
        console.log(error);
        this.makeToast("Error occurred.", "danger");
      });
    }
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

    likePost(id, liked) {
      console.log(this.form);
      this.formLike.postId = id;
      this.formLike.username = getUsernameFromToken();
      this.formLike.liked = liked;
      if(this.formLike.username != null){
        this.axios.post('/media-api/like', this.formLike)
        .then(response => { 
          console.log(response.data);
          this.$router.go();
          this.makeToast("Liked !!!", "success");
        })
        .catch(error => { 
          console.log(error);
          this.makeToast("Error occured.", "danger");
        })
      }else{
        this.makeToast("Please log in.", "info");
      }     
    },

    reportPost(id){
      this.report.requestedBy = getUsernameFromToken();
      this.report.mediaId = id;
      if(this.report.requestedBy != null){
      this.axios.post('/media-api/inappropriate', this.report)
        .then(response => { 
          console.log(response.data);
          this.makeToast("Reported !!!", "success");
        })
        .catch(error => { 
          console.log(error);
          this.makeToast("Error occured.", "danger");
        })
      }else{
        this.makeToast("Please log in.", "info");
      }
    },

    loadResults: function(length){
      for(let i=0; i < length; i++){
        for(let j=0; j<this.info[i].imageBytes.length; j++){
          if(this.info[i].imageBytes[j].image){
            this.info[i].imageBytes[j].imageByte = 'data:image/jpeg;base64,' + this.info[i].imageBytes[j].imageByte; 
          }else{
            this.info[i].imageBytes[j].imageByte = 'data:video/mp4;base64,' + this.info[i].imageBytes[j].imageByte;
          }
        }
        this.info[i].numLikes = 0;
        this.info[i].numDislikes = 0;
        if(this.info[i].likes.length > 0){
          for(var like of this.info[i].likes){
            if(like.liked){
              this.info[i].numLikes += 1;
            }else if(!like.liked){
              this.info[i].numDislikes += 1;
            }
            if(getUsernameFromToken() != null && like.username == this.username){
              if(like.liked){
                this.info[i].liked = true;
              }else{
                this.info[i].disliked = true;
              } 
            }
          }
        }
      }                                    
    },

  goToProfile: function(username) {
        console.log(username);
        window.location.href = "/user/" + username;               
      },
  },
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

.profile{
  text-align: center;
  max-width: 250px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  padding: 20px;
  margin: 30px;
  display: inline-block;
}

.profile_image{
  width: 150px;
  height: 150px;
  object-fit: cover;
  border-radius: 50%;
  margin: 0 auto 20px auto;
  display: block;
}

.profile_name{
  font-size: 1.2em;
  font-weight: bold;
}

.profile_title{
  margin-bottom: 20px;
}

.profile_detail{
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 0.9em;
}

</style>
