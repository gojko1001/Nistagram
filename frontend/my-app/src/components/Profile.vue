<template>
    <div style="padding:30px 20%; width: 100%;">
        <div id="userInfo">
            <img src="../assets/user-no-picture.png" class="profilePic" alt="Profile picture">
            <span><span class="fullName">{{user.fullName}}</span><br>
                  @{{user.username}} <br>
                  {{user.bio}}<br>
                  <a :href="'//' + user.webSite">{{user.webSite}}</a>
                  <b-link href="/edit_profile">Edit profile</b-link><hr>
            </span>
            <span>
                <b-btn pill variant="outline-dark" class="mainBtn"><i class="fas fa-photo-video"></i>  0 Posts</b-btn> <br>
                <b-btn pill variant="outline-dark" class="mainBtn"><i class="fas fa-users"></i>  0 Following</b-btn> <br>
                <b-btn pill variant="outline-dark" class="mainBtn"><i class="fas fa-user-friends"></i>  0 Followers</b-btn>
            </span>
            <br/>
        </div>
        <div class="vl"></div>
        <div id="userMedia">
            <div id="stories">
                <h2 style="text-align:center">Place for story highlights</h2><br>
            </div>
            <div id="posts">
                <b-tabs content-class="mt-3" fill>
                    <b-tab title="Grid" active>
                        <div style="margin-top:20px; margin-left:100px;" v-for="(img,i) in info" :key="i">
                            <b-card
                                tag="article"
                                style="max-width: 30rem; background:transparent; box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2)"
                                class="mb-2"
                            >
                            <h4>@{{img.username}}</h4>
                            <p style="color:blue">{{img.location.name}}</p>
                            <keep-alive>
                                <img v-bind:src="img.imageBytes" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
                            </keep-alive>
                            <b-card-text>{{img.description}}<br>
                            <span v-for="(tag,i) in img.tags" :key="i">
                                #{{tag.name}}
                            </span>
                            </b-card-text>
                         </b-card>
                            
                        </div>
                    </b-tab>
                    <b-tab title="List"><p>Single image view</p></b-tab>
                    <b-tab title="Tagged photos"><p>Photos user is tagged on</p></b-tab>
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
            info: [],
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
                                        for(let i=0; i< response.data.length; i++){
                                            this.info[i].imageBytes = 'data:image/jpeg;base64,' + this.info[i].imageBytes;  
                                        }  
                    }).catch(error => { console.log(error.message);
                                        this.makeToast("Error occurred.", "danger");
            }); 
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
</style>