<template>
    <div style="padding:30px 20%; width: 100%;">
        <div id="userInfo">
            <img src="../assets/user-no-picture.png" class="profilePic" alt="Profile picture">
            <span><span class="fullName"><i v-if="user.status == 'APPROVED'" class="fas fa-user-check"/> {{user.fullName}}</span><br>
                  @{{user.username}}<br>
                  {{user.bio}}<br>
                  <a :href="'//' + user.webSite">{{user.webSite}}</a><br>
                  <b-link v-if="isUserProfile" href="/edit_profile">Edit profile</b-link><br/>
                  <b-link v-if="isUserProfile && user.status != 'APPROVED'" href="/verification_request">Verification request</b-link><br/>
                  <b-link v-if="isUserProfile" href="/all_requests">Pending verification requests</b-link>
                  <!-- <b-btn class="w-75 mx-3" v-if="!isUserProfile && isFollowing" variant="primary" @click="unfollowUser(username)">Unfollow</b-btn> -->
                  <b-dropdown menu-class="menu-it" id="dropdown-right" right text="Following" variant="primary" class="w-75 mx-3" v-if="!isUserProfile && isFollowing">
                    <b-dropdown-text href="#"><b-form-checkbox v-model="userRelation.status" name="closeFriend" @change="closeChange()"
                        value="CLOSE_FRIEND"
                        unchecked-value="FOLLOWING">
                        Close friend
                    </b-form-checkbox></b-dropdown-text>
                    <b-dropdown-divider></b-dropdown-divider>
                    <b-dropdown-text href="#"><b-form-checkbox v-model="userRelation.mutePost" name="PostMute" switch
                        value=true
                        unchecked-value=false>
                        Mute posts
                    </b-form-checkbox></b-dropdown-text>
                    <b-dropdown-text href="#"><b-form-checkbox v-model="userRelation.muteStory" name="StoryMute" switch
                        value=true
                        unchecked-value=false>
                        Mute stories
                    </b-form-checkbox></b-dropdown-text>
                    <b-dropdown-item-button @click="unfollowUser(username)" variant="danger">Unfollow</b-dropdown-item-button>
                  </b-dropdown>
                  <b-btn class="w-75 mx-3" v-if="!isUserProfile && userRelation.status == 'PENDING'" variant="outline-primary" @click="unfollowUser(username)">Pending</b-btn>
                  <b-btn class="w-75 mx-3" v-if="!isUserProfile && userRelation.status == 'NOT_FOLLOWING'" variant="primary" @click="followUser()">Follow</b-btn><hr>
            </span>
            <span>
                <b-btn pill variant="outline-dark" class="mainBtn"><i class="fas fa-photo-video"></i>  {{numPost}} Posts</b-btn> <br>
                <b-btn v-b-modal.modal-followings pill variant="outline-dark" class="mainBtn" >
                    <i class="fas fa-users"></i>  {{numFollowing}} Following</b-btn> <br>
                <b-modal id="modal-followings" title="Following" @show="checkPermission">
                    <div v-for="(follow,p) in followings" :key="p">
                        <span v-on:click="goToProfile(follow.username)" class="clickable">@{{follow.username}} (<b>{{follow.fullName}}</b>)</span>
                        <b-btn size="sm" variant="outline-info" class="float-right" @click="unfollowUser(follow.username)">Unfollow</b-btn><hr>
                    </div>
                    <template #modal-footer="{ cancel }">
                        <b-button variant="secondary" @click="cancel()">
                            Cancel
                        </b-button>
                    </template>
                </b-modal>
                <b-btn v-b-modal.modal-followers pill variant="outline-dark" class="mainBtn">
                    <i class="fas fa-user-friends"></i>  {{numFollowers}} Followers</b-btn>
                <b-modal id="modal-followers" title="Followers" @show="checkPermission">
                    <div v-for="(follow,p) in followers" :key="p">
                        <span @click="goToProfile(follow.username)" class="clickable">@{{follow.username}} (<b>{{follow.fullName}}</b>)</span>
                        <b-btn size="sm" variant="outline-info" class="float-right">Remove</b-btn><hr>
                    </div>
                    <template #modal-footer="{ cancel }">
                        <b-button variant="secondary" @click="cancel()">
                            Cancel
                        </b-button>
                    </template>
                </b-modal>
            </span>
            <br/>
        </div>
        <i class="fas fa-photo-video"></i>
        <div class="vl"></div>
        <div v-if="isUserProfile || isFollowing || user.publicProfile" id="userMedia">
            <div id="stories">
                <b-button v-b-modal.modal-2 style="font-size:20px;" @click="getStories()">@{{user.username}}'s stories <i class="fas fa-camera-retro fa-lg" style="margin-left:15px"></i></b-button>
                <b-button v-b-modal.modal-3 style="font-size:20px;margin-left:20px" @click="getHighlightedStories()">highlighted stories <i class="fas fa-highlighter fa-lg" style="margin-left:15px"></i></b-button>
                <!-- Stories -->
                <b-modal id="modal-2" title="Stories">
                     <div v-for="(img,p) in stories" :key="p">
                            <b-card
                                tag="article"
                                style="max-width: 30rem; background:transparent; box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);display:block; margin-left:auto; margin-right:auto"
                                class="mb-2">
                                <h4>@{{img.username}}</h4>
                                <button v-if="username != null" style="margin-top:-30px; margin-left: 390px" class="heart inter" @click="reportPost(img.mediaId)">
                                    <i class="fa fa-ban fa-fw"></i>
                                </button>
                                <p style="color:blue">{{img.location.name}}</p>

                                <div v-for="(img, q) in img.imageBytes" :key="'S'+q">
                                    <img v-if="img.image" v-bind:src="img.imageByte" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
                                    <video autoplay controls v-if="!img.image" v-bind:src="img.imageByte" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
                                        The video is not supported by your browser.
                                    </video>
                                </div>

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
                <!-- Highlighted stories -->
                <b-modal id="modal-3" title="Highlighted stories">
                     <div v-for="(img,p) in stories" :key="p">
                            <b-card
                                tag="article"
                                style="max-width: 30rem; background:transparent; box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);display:block; margin-left:auto; margin-right:auto"
                                class="mb-2">
                                <h4>@{{img.username}}</h4>
                                <p style="color:blue">{{img.location.name}}</p>

                                <div v-for="(img, q) in img.imageBytes" :key="'S'+q">
                                    <img v-if="img.image" v-bind:src="img.imageByte" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
                                    <video autoplay controls v-if="!img.image" v-bind:src="img.imageByte" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
                                        The video is not supported by your browser.
                                    </video>
                                </div>

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
                    <!-- My posts -->
                    <b-tab title="Posts" active>
                        <div v-for="(img,i) in info" :key="i">
                            <b-card
                                tag="article"
                                style="max-width: 30rem; background:transparent; box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);display:block; margin-left:auto; margin-right:auto"
                                class="mb-2">
                                <h4>@{{img.username}}</h4>
                                <h6 style="margin-top:-30px; margin-left: 350px">{{img.timestamp | formatDate}}</h6>
                                <button v-if="username != null" style="margin-top:-30px; margin-left: 390px" class="heart inter" @click="reportPost(img.mediaId)">
                                    <i class="fa fa-ban fa-fw"></i>
                                </button>
                                <p style="color:blue">{{img.location.name}}</p>
                                
                                <div v-for="(img, a) in img.imageBytes" :key="'P'+a">
                                    <img v-if="img.image" v-bind:src="img.imageByte" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
                                    <video autoplay controls v-if="!img.image" v-bind:src="img.imageByte" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
                                        The video is not supported by your browser.
                                    </video>
                                </div>
                                

                                <br>
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
                                <router-link v-if="username != null" :to="{ name: 'AddComment', params: { id: img.id} }" class="inter" style="margin-left:250px">
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
                                    <span @click="goToProfile(comm.username)" class="clickable"><b>{{comm.username}}:  </b></span>{{comm.text}}<br>
                                </span>
                            </b-card>              
                        </div>
                    </b-tab>
                    <!-- Collections -->
                    <b-tab title="Collections" @click="getCollections()">
                        <div v-for="(coll,c) in collections" :key="c">
                            <p style="font-size:30px">{{coll.name}}:</p>
                            <div v-for="(img,i) in coll.favourites" :key="i">
                            <b-card
                                tag="article"
                                style="max-width: 30rem; background:transparent; box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);display:block; margin-left:auto; margin-right:auto"
                                class="mb-2">
                                <h4>@{{img.username}}</h4>
                                <h6 style="margin-top:-30px; margin-left: 350px">{{img.timestamp | formatDate}}</h6>
                                <p style="color:blue">{{img.location.name}}</p>

                                <div v-for="(img, q) in img.imageBytes" :key="'C'+q">
                                    <img v-if="img.image" v-bind:src="img.imageByte" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
                                    <video autoplay controls v-if="!img.image" v-bind:src="img.imageByte" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
                                        The video is not supported by your browser.
                                    </video>
                                </div>
                                <br>
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
                            <hr>
                        </div>
                    </b-tab>
                    <!-- Liked posts -->
                    <b-tab title="Liked posts" @click="historyOfLikedPosts()">
                        <div v-for="(img,u) in history" :key="u">
                            <b-card
                                tag="article"
                                style="max-width: 30rem; background:transparent; box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);display:block; margin-left:auto; margin-right:auto"
                                class="mb-2">
                                <h4>@{{img.username}}</h4>
                                <h6 style="margin-top:-30px; margin-left: 350px">Reacted on <br>{{img.date | formatDate}}</h6>
                                <button v-if="username != null" style="margin-top:-30px; margin-left: 390px" class="heart inter" @click="reportPost(img.mediaId)">
                                    <i class="fa fa-ban fa-fw"></i>
                                </button>
                                <p style="color:blue">{{img.location.name}}</p>
                                <div v-for="(img, q) in img.imageBytes" :key="'L'+q">
                                    <img v-if="img.image" v-bind:src="img.imageByte" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
                                    <video autoplay controls v-if="!img.image" v-bind:src="img.imageByte" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
                                        The video is not supported by your browser.
                                    </video>
                                </div>
                                <br>
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
                                <router-link v-if="username != null" :to="{ name: 'AddComment', params: { id: img.id} }" class="inter" style="margin-left:250px">
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
                    </b-tab>
                    <!-- Story archived -->
                    <b-tab title="Story archive" @click="getArchivedStories()">
                        <div v-for="(img,j) in archivedStories" :key="j">
                            <b-card
                                tag="article"
                                style="max-width: 30rem; background:transparent; box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);display:block; margin-left:auto; margin-right:auto"
                                class="mb-2">
                                <h4>@{{img.username}}</h4>
                                <h6 style="margin-top:-30px; margin-left: 350px">{{img.timestamp | formatDate}}</h6>
                                <button v-if="username != null" style="margin-top:-30px; margin-left: 390px" class="heart inter" @click="reportPost(img.mediaId)">
                                    <i class="fa fa-ban fa-fw"></i>
                                </button>
                                <p style="color:blue">{{img.location.name}}</p>

                                <div v-for="(img, q) in img.imageBytes" :key="'SA'+q">
                                    <img v-if="img.image" v-bind:src="img.imageByte" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
                                    <video autoplay controls v-if="!img.image" v-bind:src="img.imageByte" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
                                        The video is not supported by your browser.
                                    </video>
                                </div>
                                <button class="heart inter" v-bind:class="{'black': !img.highlighted, 'red': img.highlighted}" @click="highlightStory(img.id)" style="margin-left:380px">
                                    <i class="fas fa-highlighter"></i>
                                </button>
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
        <div v-if="!isUserProfile && !isFollowing && !user.publicProfile" id="locked">
            <img src="../assets/locker.png" class="lockedImg" alt="Profile is private">
            <h3>Profile is private</h3>
        </div>
    </div>
</template>
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<script src="https://unpkg.com/vue-router/dist/vue-router.js"></script>


<script>
import { FOLLOW_PATH, GET_FOLLOWERS_PATH, GET_FOLLOWINGS_PATH, SERVER_NOT_RESPONDING, USER_PATH, DELETE_RELATION_PATH, USER_RELATION_PATH, RELATION_STATUS_UPDATE } from '../util/constants';
import { getToken, getUsernameFromToken } from '../util/token';
export default {
    name: 'Profile',
    data(){
        return{
            isUserProfile: false,
            isFollowing: false,
            userRelation: {status: 'NOT_FOLLOWING', enableNotifications: false},
            user: '',
            username: this.$route.params.pUsername,
            info: [{
                username:'',
                location:{name:''},
                imageBytes:[{
                    imageByte:'',
                    image:''
                }],
                numLikes:'',
                numDislikes:'',
                liked: false,
                disliked: false
            }],
            favourites: [{
                username:'',
                location:{ name:''},
                comments:[],
                likes:[],
                imageBytes:[{
                    imageByte:''
                }],
                numLikes:'',
                numDislikes:'',
                liked: false,
                disliked: false
            }],
            hideCommenting: true,
            numPost:0,
            liked: false,
            formLike:{
              postId: 0,
              username:''
            },
            stories:[],
            archivedStories:[],
            collections:[],
            numFollowing:0,
            numFollowers:0,
            followings:[],
            followers:[],
            relationDto: {
                username: '',
                relatedUsername: ''
            },
            report:{
              requestedBy: '',
              mediaId:''
            },
            history:[{
                location:{name:''},
                comments:[],
                imageBytes:[{
                    image:'',
                    imageByte:''
                }],
                likes:[],
                numLikes:'',
                numDislikes:'',
                liked: false,
                disliked: false,
                date:''
            }]
        }
    },
    mounted: function(){
        this.isUserProfile = this.username == getUsernameFromToken();
        if(!this.isUserProfile)
            this.axios.get(USER_RELATION_PATH + "/" + this.username, {  headers:{
                                                                            Authorization: "Bearer " + getToken(),
                                                                        }   
                
            }).then(response => {
                                this.userRelation.status = response.data.status,
                                this.userRelation.enableNotifications = response.data.enableNotifications
                                if(response.data.status == "FOLLOWING" || response.data.status == "MUTED" ||
                                    response.data.status == "CLOSE_FRIEND")
                                    this.isFollowing = true;
            })
        this.axios.get(USER_PATH + '/' + this.username, {   headers:{
                                                                Authorization: "Bearer " + getToken(),
                                                            }                                          
            }).then(response => {
                                this.user = response.data;
                                console.log(response.data);
                                this.axios.get(GET_FOLLOWERS_PATH + "/" + this.username)
                                            .then(response => {
                                                this.numFollowers = response.data.length;
                                                this.followers = response.data;
                                                this.getPosts(); // TODO: Hide posts from non followers
                                            })
            }).catch(error => { if(!error.response) {
                                    this.makeToast(SERVER_NOT_RESPONDING, "warning");
                                    return
                                }
                                window.location.href = '/home'
            });
        this.axios.get(GET_FOLLOWINGS_PATH + "/" + this.username)
                        .then(response => {
                            this.numFollowing = response.data.length;
                            this.followings = response.data;    
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
        goToProfile(username){
            window.location.href = "/user/" + username;
        },
        commenting: function(id){
            this.hideCommenting = false;
            console.log(id);
        },
        reportPost(id){
        this.report.requestedBy = getUsernameFromToken();
        this.report.mediaId = id;
        if(this.report.requestedBy != null){
          this.axios.post('/media-api/inappropriate', this.report)
          .then(response => { console.log(response.data);
                              this.makeToast("Reported !!!", "success");
                            })
          .catch(error => { console.log(error);
                            this.makeToast("Error occured.", "danger");
                          })
        }else{
          this.makeToast("Please log in.", "info");
        }
      },
        likePost(id, liked) {
            console.log(this.form);
            this.formLike.postId = id;
            this.formLike.username = getUsernameFromToken();
            this.formLike.liked = liked;
            this.axios.post('/media-api/like', this.formLike)
            .then(response => { console.log(response.data);
                                this.makeToast("Liked !!!", "success");
                                })
            .catch(error => { console.log(error);
                                this.makeToast("Error occured.", "danger");
                            })
        },
        getPosts(){
            this.axios.get('/media-api/image/profile/' + this.username)
                    .then(response => { this.info = response.data;
                                        this.numPost = response.data.length;
                                        for(let i=0; i< response.data.length; i++){
                                            for(let j=0; j< this.info[i].imageBytes.length; j++){
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
                                                    if(like.username == this.username){
                                                        if(like.liked){
                                                            this.info[i].liked = true;
                                                        }else{
                                                            this.info[i].disliked = true;
                                                        } 
                                                    }
                                                }
                                            }
                                        }    
                    }).catch(error => { console.log(error.message);
                                        this.makeToast("Error occurred.", "danger");
            });
        },
        getStories(){
            this.axios.get('/media-api/story/profile/' + this.username)
                        .then(response => { this.stories = response.data;
                                            for(let i=0; i< response.data.length; i++){
                                                for(let j=0; j< this.stories[i].imageBytes.length; j++){
                                                    if(this.stories[i].imageBytes[j].image){
                                                        this.stories[i].imageBytes[j].imageByte = 'data:image/jpeg;base64,' + this.stories[i].imageBytes[j].imageByte; 
                                                    }else{
                                                        this.stories[i].imageBytes[j].imageByte = 'data:video/mp4;base64,' + this.stories[i].imageBytes[j].imageByte;
                                                    }
                                                }
                                            }   
                        }).catch(error => { console.log(error.message);
                                            this.makeToast("Error occurred.", "danger");
                });
        },
        getArchivedStories(){
            this.axios.get('/media-api/story/archive/' + this.username)
                        .then(response => { this.archivedStories = response.data;
                                            for(let i=0; i< response.data.length; i++){
                                                for(let j=0; j< this.archivedStories[i].imageBytes.length; j++){
                                                    if(this.archivedStories[i].imageBytes[j].image){
                                                        this.archivedStories[i].imageBytes[j].imageByte = 'data:image/jpeg;base64,' + this.archivedStories[i].imageBytes[j].imageByte; 
                                                    }else{
                                                        this.archivedStories[i].imageBytes[j].imageByte = 'data:video/mp4;base64,' + this.archivedStories[i].imageBytes[j].imageByte;
                                                    } 
                                                }
                                            }   
                        }).catch(error => { console.log(error.message);
                                            this.makeToast("Error occurred.", "danger");
                });
        },
        getHighlightedStories(){
            this.axios.get('/media-api/story/highlights/' + this.username)
                        .then(response => { this.stories = response.data;
                                            for(let i=0; i< response.data.length; i++){
                                                for(let j=0; j< this.stories[i].imageBytes.length; j++){
                                                    if(this.stories[i].imageBytes[j].image){
                                                        this.stories[i].imageBytes[j].imageByte = 'data:image/jpeg;base64,' + this.stories[i].imageBytes[j].imageByte; 
                                                    }else{
                                                        this.stories[i].imageBytes[j].imageByte = 'data:video/mp4;base64,' + this.stories[i].imageBytes[j].imageByte;
                                                    }
                                                }
                                            }     
                        }).catch(error => { console.log(error.message);
                                            this.makeToast("Error occurred.", "danger");
                });
        },
        highlightStory(storyId){
            this.axios.post('/media-api/story/highlight/' + storyId)
                        .then(response => { console.log(response.data); 
                                            this.makeToast(response.data, "success");                
                        }).catch(error => { console.log(error.message);
                                            this.makeToast(error.message, "danger");
                });
        },
        getCollections() {
        var user = getUsernameFromToken();
        this.axios.get('/media-api/collection/' + user)
          .then(response => {   this.collections = response.data;
                                for(let i=0; i< this.collections.length; i++){
                                    for(let j=0; j < this.collections[i].favourites.length; j++){
                                        for(let p=0; p<this.collections[i].favourites[j].imageBytes.length; p++){
                                            if(this.collections[i].favourites[j].imageBytes[p].image){
                                            this.collections[i].favourites[j].imageBytes[p].imageByte = 'data:image/jpeg;base64,' + this.collections[i].favourites[j].imageBytes[p].imageByte; 
                                            }else{
                                                this.collections[i].favourites[j].imageBytes[p].imageByte = 'data:video/mp4;base64,' + this.collections[i].favourites[j].imageBytes[p].imageByte;
                                            }
                                        }
                                        this.collections[i].favourites[j].numLikes = 0;
                                        this.collections[i].favourites[j].numDislikes = 0;
                                        if(this.collections[i].favourites[j].likes.length > 0){
                                            for(var like of this.collections[i].favourites[j].likes){
                                                if(like.liked){
                                                this.collections[i].favourites[j].numLikes += 1;
                                                }else if(!like.liked){
                                                this.collections[i].favourites[j].numDislikes += 1;
                                                }
                                                if(like.username == this.username){
                                                    if(like.liked){
                                                        this.collections[i].favourites[j].liked = true;
                                                    }else{
                                                        this.collections[i].favourites[j].disliked = true;
                                                    } 
                                                }
                                            }
                                        }
                                    }
                                }
                            })
          .catch(error => { console.log(error);
                            this.makeToast("Error occured.", "danger");
                          })
        },
        historyOfLikedPosts(){
            this.axios.get('/media-api/like/history/' + this.username)
                        .then(response => { this.history = response.data;
                                        for(let i=0; i< response.data.length; i++){
                                            for(let j=0; j<this.history[i].imageBytes.length; j++){
                                                if(this.history[i].imageBytes[j].image){
                                                    this.history[i].imageBytes[j].imageByte = 'data:image/jpeg;base64,' + this.history[i].imageBytes[j].imageByte; 
                                                }else{
                                                    this.history[i].imageBytes[j].imageByte = 'data:video/mp4;base64,' + this.history[i].imageBytes[j].imageByte;
                                                }
                                            }
                                            this.history[i].numLikes = 0;
                                            this.history[i].numDislikes = 0;
                                            if(this.history[i].likes.length > 0){
                                                for(var like of this.history[i].likes){
                                                    if(like.liked){
                                                        this.history[i].numLikes += 1;
                                                    }else if(!like.liked){
                                                        this.history[i].numDislikes += 1;
                                                    }
                                                    if(like.username == this.username){
                                                        if(like.liked){
                                                            this.history[i].liked = true;
                                                            this.history[i].date = like.timestamp
                                                        }else{
                                                            this.history[i].disliked = true;
                                                            this.history[i].date = like.timestamp
                                                        } 
                                                    }
                                                }
                                            }
                                        }         
                        }).catch(error => { console.log(error.message);
                                            this.makeToast("Error occurred.", "danger");
                });
        },

        followUser(){
            this.axios.post(FOLLOW_PATH + "/" + this.username, null, {   headers:{
                                                                Authorization: "Bearer " + getToken(),
                                                            } 
                    }).then(() => {
                        this.makeToast("Follow request sent!", "success");
                        window.location.reload();
                    }).catch(err => {
                        if(!err.response)
                              this.makeToast(SERVER_NOT_RESPONDING, "danger");
                        else
                            this.makeToast(err.message, "danger");
                    })
        },

        unfollowUser(toUnfollow){
            this.axios.delete(DELETE_RELATION_PATH + "/" + toUnfollow,{   headers:{
                                                                Authorization: "Bearer " + getToken(),
                                                            }
                    }).then(() => {
                        this.makeToast(toUnfollow + " unfollowed!", "success");
                        window.location.reload();
                    }).catch(err => {
                        if(!err.response)
                              this.makeToast(SERVER_NOT_RESPONDING, "danger");
                        else
                            this.makeToast(err.message, "danger");
                    })
        },
        closeChange(){
            this.axios.put(RELATION_STATUS_UPDATE + "/" + this.username + "/" + this.userRelation.status, null, {   
                                                            headers:{
                                                                Authorization: "Bearer " + getToken(),
                                                            } 
                    }).catch(err => {
                        if(!err.response)
                              this.makeToast(SERVER_NOT_RESPONDING, "danger");
                        else
                            this.makeToast("Couldn't update relation status!", "danger");
                    })
        },

        checkPermission(bvModalEvt){
            if(!this.isUserProfile && !this.isFollowing && !this.user.publicProfile)
                bvModalEvt.preventDefault();
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

#locked {
    display: inline-block;
    position: absolute;
    margin-left: 150px;
}
.lockedImg{
    height: 150px;
    width: 150px;
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
.menu-it{
    width: 300px;
}
</style>