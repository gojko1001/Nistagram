<template>
    <div style="padding:30px 20%; width: 100%;">
        <div id="userInfo">
            <img src="../assets/user-no-picture.png" class="profilePic" alt="Profile picture">
            <span><span class="fullName">{{user.fullName}}</span><br>
                  @{{user.username}} <br>
                  {{user.bio}}<br>
                  <a :href="'//' + user.webSite">{{user.webSite}}</a><br>
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
                    <b-tab title="Grid" active><p>Grided view</p></b-tab>
                    <b-tab title="List"><p>Single image view</p></b-tab>
                    <b-tab title="Tagged photos"><p>Photos user is tagged on</p></b-tab>
                </b-tabs>
            </div>
        </div>
    </div>
</template>

<script>
import { SERVER_NOT_RESPONDING, USER_PATH } from '../util/constants';
export default {
    name: 'Profile',
    data(){
        return{
            user: ''
        }
    },
    mounted: function(){
        this.axios.get(USER_PATH + '/' + this.$route.params.username, {
      headers:{
        Authorization: "Bearer " + localStorage.getItem('JWT'),
      }
    })
    .then(response => {
                        this.user = response.data;
                        console.log(response.data);
    })
    .catch(error => {
                        if(!error.response){
                            this.makeToast(SERVER_NOT_RESPONDING, "warning")
                            return
                        }
                        window.location.href = '/home'
                        console.error(error);
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
        }
    }
}
</script>
<style scoped>
    #userInfo{
        display: inline-block;
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