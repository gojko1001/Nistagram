<template>
    <div id="searchPage">
      <table v-if="searchingType == 'profile' && showProfile==false">
      <tbody>
        <tr v-for="(res,i) in profiles" :key="i" :res="res">
          <td><button v-on:click="getProfile(res.username)" variant="primary" style="width:200px;"><i v-if="res.status === 'APPROVED'" class="fas fa-user-check"/> {{res.fullName}}</button></td>
        </tr>
      </tbody>
    </table>
    <br>
      <div v-if="searchingType == 'tag' || searchingType == 'location' || showProfile">
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
              <i class="fas fa-thumbs-up"></i>
            <span>{{img.numLikes}}</span>
              <i class="fas fa-thumbs-down"></i>
            <span>{{img.numDislikes}}</span>
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
      </div>
      <h3 v-if="info.length == 0">There is no any photo: {{this.searchingInput}}</h3>
      <h3 v-if="profiles.length == 0 && searchingType == 'profile'">There is no user with username: {{this.searchingInput}}</h3>
    </div>
</template>

<script>
import { SEARCH_USER_PATH, SEARCH_TAG_PATH, SEARCH_LOCATION_PATH} from '../util/constants';
export default {
  name: 'Search',
  data(){
        return{
            searchingType: '',
            searchingInput: '',
            results: [],
            info: [{
              numLikes:'',
              numDislikes:'',
              liked: false,
              disliked: false
            }],
            profiles: [],
            showProfile: false,
        }
    },
  mounted: function(){
        this.searchingType = this.$route.params.name;
        this.searchingInput = this.$route.params.find;

        if(this.searchingType == "profile"){
          this.axios.get(SEARCH_USER_PATH + "?username=" + this.searchingInput)
              .then(response => { 
                              this.profiles = response.data;
                              console.log(this.profiles);
                            })
              .catch(error => { console.log(error);
                            this.makeToast("Error occured.", "danger");
                          })
        }

        if(this.searchingType == "tag"){
          this.axios.get(SEARCH_TAG_PATH + "?tag=" + this.searchingInput)
              .then(response => { 
                              this.info = response.data;
                              this.loadResults(response.data.length);
                            })
              .catch(error => { console.log(error);
                            this.makeToast("Error occured.", "danger");
                          })

        }

        if(this.searchingType == "location"){
          this.axios.get(SEARCH_LOCATION_PATH + "?location=" + this.searchingInput)
              .then(response => { this.info = response.data;
                                  this.loadResults(response.data.length); 
                  }).catch(error => { console.log(error);
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

    loadResults: function(length){
      for(let i=0; i< length; i++){
        if(this.info[i].image){
          this.info[i].imageBytes = 'data:image/jpeg;base64,' + this.info[i].imageBytes; 
        }else{
          this.info[i].imageBytes = 'data:video/mp4;base64,' + this.info[i].imageBytes;
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
          }
        }
      }   

    },

  getProfile: function(username) {
        console.log(username);
        this.axios.get('/media-api/image/profile/' + username)
                  .then(response => { console.log(response);
                    this.showProfile = true;
                    this.searchingType = '';
                    this.info = response.data;
                    this.loadResults(response.data.length);                               
                })
                  .catch(error => { console.log(error);
                                    this.makeToast("Error occurred. ", "danger");
                                  });                  
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

button{
  background:#007bff;
  color: white;
  border-radius: 5px;
  margin-left: 50%;
  margin-top: 10%;
}

</style>
