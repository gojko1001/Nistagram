<template>
    <div style="padding:30px 20%; width: 100%;">
        <div id="userInfo">
            <img src="../assets/user-no-picture.png" class="profilePic" alt="Profile picture">
            <span><span class="fullName">{{user.fullName}}</span><br>
                  @{{user.username}} <br>
                  {{user.bio}}<br>
                  <a :href="'//' + user.webSite">{{user.webSite}}</a><br>
                  <b-link href="/edit_profile">Edit profile</b-link><hr>
            </span>
            <span>
                <b-btn pill variant="outline-dark" class="mainBtn"><i class="fas fa-photo-video"></i>  {{numPost}} Posts</b-btn> <br>
                <b-btn pill variant="outline-dark" class="mainBtn"><i class="fas fa-users"></i>  0 Following</b-btn> <br>
                <b-btn pill variant="outline-dark" class="mainBtn"><i class="fas fa-user-friends"></i>  0 Followers</b-btn>
            </span>
            <br/>
        </div>
        <i class="fas fa-photo-video"></i>
        <div class="vl"></div>
        <div id="userMedia">
            <div id="stories">
                <b-button v-b-modal.modal-1 style="font-size:25px;">@{{user.username}}'s stories <i class="fas fa-camera-retro fa-lg" style="margin-left:15px"></i></b-button>
                <b-modal id="modal-1">
                    <button>
                        <i class="fas fa-user-friends"></i>
                    </button>
                     <div v-for="(img,p) in stories" :key="p">
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
                                <b-card-text>
                                    <span><b>{{img.username}}:  </b></span>{{img.description}}
                                    <br>
                                    <span v-for="(tag,t) in img.hashtags" :key="t">
                                        #{{tag.name}}
                                    </span>
                                </b-card-text>
                            </b-card>              
                        </div>
                </b-modal>
            </div>
            <br>
            <div id="posts">
                <b-tabs content-class="mt-3" fill>
                    <b-tab title="Photos" active>
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
                                    <i class="fas fa-heart"></i>
                                </button>
                                <span>{{img.numLikes}}</span>
                                <router-link :to="{ name: 'AddComment', params: { id: img.id} }" class="inter link">
                                    <i class="far fa-comment"></i>
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
                    </b-tab>
                    <b-tab title="Tagged posts"><p>Photos user is tagged on</p></b-tab>
                    <b-tab title="Liked posts"><p>Liked posts</p></b-tab>
                    <b-tab title="Story archive">
                        <div v-for="(img,j) in archivedStories" :key="j">
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
                                <b-card-text>
                                    <span><b>{{img.username}}:  </b></span>{{img.description}}
                                    <br>
                                    <span v-for="(tag,t) in img.hashtags" :key="t">
                                        #{{tag.name}}
                                    </span>
                                </b-card-text>
                            </b-card>              
                        </div>
                    </b-tab>
                </b-tabs>
            </div>
        </div>
    </div>
</template>



<script>
import { SERVER_NOT_RESPONDING, USER_PATH } from '../util/constants';
import { getEmailFromToken } from '../util/token';
export default {
    name: 'Profile',
    data(){
        return{
            user: '',
            username:'',
            info: [{
                numLikes:'',
                liked: false
            }],
            hideCommenting: true,
            numPost:0,
            liked: false,
            formLike:{
              postId: 0,
              username:''
            },
            stories:[],
            archivedStories:[]
        }
    },
    mounted: function(){
        this.username = getEmailFromToken();
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
                                window.location.href = '/home'
            })
        this.axios.get('/image/profile/' + this.username)
                    .then(response => { this.info = response.data;
                                        this.numPost = response.data.length;
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
                    }).catch(error => { console.log(error.message);
                                        this.makeToast("Error occurred.", "danger");
            });
        this.getStories();
        this.getArchivedStories();
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
        commenting: function(id){
            this.hideCommenting = false;
            console.log(id);
        },
        likePost(id) {
        console.log(this.form);
        this.formLike.postId = id;
        this.formLike.username = getEmailFromToken();
        this.axios.post('/like', this.formLike)
          .then(response => { console.log(response.data);
                              this.makeToast("Liked !!!", "success");
                            })
          .catch(error => { console.log(error);
                            this.makeToast("Error occured.", "danger");
                          })
      },
      getStories(){
        this.axios.get('/story/profile/' + this.username)
                    .then(response => { this.stories = response.data;
                                        for(let i=0; i< response.data.length; i++){
                                            if(this.stories[i].image){
                                              this.stories[i].imageBytes = 'data:image/jpeg;base64,' + this.stories[i].imageBytes; 
                                            }else{
                                              this.stories[i].imageBytes = 'data:video/mp4;base64,' + this.stories[i].imageBytes;
                                            } 
                                        }   
                    }).catch(error => { console.log(error.message);
                                        this.makeToast("Error occurred.", "danger");
            });
      },
      getArchivedStories(){
        this.axios.get('/story/archive/' + this.username)
                    .then(response => { this.archivedStories = response.data;
                                        for(let i=0; i< response.data.length; i++){
                                            if(this.archivedStories[i].image){
                                              this.archivedStories[i].imageBytes = 'data:image/jpeg;base64,' + this.archivedStories[i].imageBytes; 
                                            }else{
                                              this.archivedStories[i].imageBytes = 'data:video/mp4;base64,' + this.archivedStories[i].imageBytes;
                                            } 
                                        }   
                    }).catch(error => { console.log(error.message);
                                        this.makeToast("Error occurred.", "danger");
            });
      }
    }
}
</script>



<style scoped>
#userInfo{
    display: inline-block;
    max-width: 200px;
    word-wrap: break-word;
}
.profilePic{
    width: 150px;
    height: 150px;
    border-radius: 50%;
    object-fit: cover;
    margin: 0 50px 20px auto;
    display: block;
}
.fullName{
    font-size: 1.2rem;
    font-weight: 800;
}
.mainBtn{
    margin: 5px
}
#userMedia{
    margin-left: 20px;
    position: absolute;
    display: inline-block;
}
#posts{
    width: 800px; /* Set width according to window */
}
.vl {
    border-left: 2px solid rgb(131, 131, 131);
    height: 400px;
    display: inline-block;
}
.inter{
    color:black;
    font-size: 25px;
    background: transparent;
    border: none;
    margin-left: 0px;
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