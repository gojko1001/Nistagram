<template>
  <div id="navbarPage">
    <nav class="navbar navbar-expand navbar-light" style="background-color: #e3f2fd;">
    <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <a class="navbar-brand" href="/">
            <img src="../assets/logo.png" height="40" alt="" />
        </a>
        <form style="padding: 0px 0px 0px 60px;">
          <input type="text" class="search-input" v-model="searchInput">
          <button @click="search()" type="submit" class="search-btn">
            <i class="fas fa-search"></i>
          </button>
          <br>
          <div>
          <b-form-radio-group
            v-model="selected"
            :options="options"
            class="mb-3"
            value-field="item"
            text-field="name"
            disabled-field="notEnabled"
          ></b-form-radio-group>
        </div>

        </form>

        <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
            <li class="nav-item">
                <button class="nav-btn" @click='discoverPage'>
                  <i class="fas fa-globe"></i>
                </button>
            </li>
            <li class="nav-item" v-if="username != null && role == 'ROLE_USER'">
                <button class="nav-btn">
                  <i class="fas fa-envelope"></i>
                </button>
            </li>
            <li class="nav-item" v-if="username != null && role == 'ROLE_USER'">
                <button class="heart nav-btn">
                  <i class="fas fa-user-plus" v-b-modal.modal-follow-request></i>
                  <small id="pendingnum" v-if="followRequests.length > 0"><b-badge variant="danger" pill>{{followRequests.length}}</b-badge></small>
                </button>
                
            </li>
            <li class="nav-item" v-if="username != null && role == 'ROLE_USER'">
                <button class="heart nav-btn" @click="notificationPage">
                  <i class="fas fa-heart"></i>
                </button>
            </li>
            <li class="nav-item" v-if="username != null && role == 'ROLE_ADMIN'">
                <button class="nav-btn" @click='inappropriateContent'>
                  <i class="fas fa-ban"></i>
                </button>
            </li>
            <li class="nav-item" v-if="username != null && role == 'ROLE_ADMIN'">
                <button class="nav-btn" @click='profileVerification'>
                  <i class="fas fa-user-check"></i>
                </button>
            </li>
            <li class="nav-item" v-if="username != null && role == 'ROLE_ADMIN'">
                <button class="nav-btn">
                  <i class="fas fa-user-secret"></i>
                </button>
            </li>
            <li class="nav-item" v-if="username != null && role == 'ROLE_USER'">
                <button class="nav-btn" @click='myProfile'>
                  <i class="fas fa-user"></i>
                </button>
            </li>
            <li class="nav-item" v-if="username != null">
                <button class="nav-btn" @click="logout">
                  <i class="fas fa-sign-out-alt"></i>
                </button>
            </li>
        </ul>
    </div>
    </nav>
    <b-modal id="modal-follow-request" title="Following requests">
      <span v-if="followRequests.length == 0" style="text-align:center; color: blue"><h5>No follow requests!</h5></span>
        <div v-for="(request,idx) in followRequests" :key="idx">
            <span v-on:click="goToProfile(request.username)" class="clickable">@{{request.username}} (<b>{{request.fullName}}</b>)</span>
            <b-btn size="sm" variant="outline-danger" class="float-right" @click="deleteRequest(request.username)">Delete</b-btn>
            <b-btn size="sm" variant="outline-info" class="float-right" @click="acceptRequest(request.username, idx)">Accept</b-btn>
            <hr>
        </div>
        <template #modal-footer="{ cancel }">
            <b-button variant="secondary" @click="cancel()">
                Cancel
            </b-button>
        </template>
    </b-modal>
  </div>
</template>


<script>
import { ACCEPT_FOLLOWER_PATH, GET_REQUESTS_PATH, SERVER_NOT_RESPONDING, DELETE_REQUEST_PATH } from '../util/constants';
import { getRoleFromToken, getToken, getUsernameFromToken, removeToken } from '../util/token';
export default {
  name: 'Navbar',
  data() {
      return {
        selected: 'profile',
        options: [
          { item: 'profile', name: 'Profiles' },
          { item: 'tag', name: 'Tags' },
          { item: 'location', name: 'Locations' },
        ],
        username: getUsernameFromToken(),
        role: getRoleFromToken(),
        searchInput:'',
        followRequests: []
      }
  },
  mounted: function(){
    this.axios.get(GET_REQUESTS_PATH, {  headers:{
                                            Authorization: "Bearer " + getToken(),
                                         }   
    }).then(response => {
                      this.followRequests = response.data;
    })
  },
  methods:{
    myProfile:function(){
      window.location.href = "/user/" + this.username;
    },
    logout: function(){
      removeToken();
      window.location.href = "/"
    },
    inappropriateContent:function(){
      window.location.href = "/inappropriate_content";
    },
    discoverPage:function(){
      window.location.href = "/discover";
    },
    notificationPage:function(){
      window.location.href = "/notification";
    },
    search: function() {
      localStorage.setItem("name", this.selected);
      localStorage.setItem("find", this.searchInput);
      this.$router.push({path: '/search'})        
    },
    profileVerification: function(){
      window.location.href = "/all_requests";
    },
    goToProfile(username){
      window.location.href = "/user/" + username;
    },
    acceptRequest(username, idx){
      this.axios.put(ACCEPT_FOLLOWER_PATH + "/" + username, null, {  headers:{
                                            Authorization: "Bearer " + getToken(),
                                         }  
      }).then(() => {
                  this.followRequests.splice(idx, 1);
      }).catch(err => {
                        if(!err.response)
                            this.makeToast(SERVER_NOT_RESPONDING, "danger");
                        else
                            this.makeToast(err.message, "danger");
                    })
    },
    deleteRequest(username, idx){
      this.axios.delete(DELETE_REQUEST_PATH + "/" + username,{   headers:{
                                                                Authorization: "Bearer " + getToken(),
                                                            }
                    }).then(() => {
                        this.followRequests.splice(idx, 1);
                    }).catch(err => {
                        if(!err.response)
                            this.makeToast(SERVER_NOT_RESPONDING, "danger");
                        else
                            this.makeToast(err.message, "danger");
                    })
    }
  },
}
</script>

<style>
.nav-item{
  font-size: 25px;
}
.clickable:hover{
  cursor: pointer;
}

.fas{
  cursor: pointer;
  transition: all 0.5s ease;
}

.nav-btn {
    color:black;
    padding: 0.5rem;
    font-size: 2rem;
    border: none;
    background: #e3f2fd;
}
.nav-btn:hover{
    background: #0275d8;
    color: white;
    border-radius: 7px;
}
.heart:hover{
  color:red;
}
.search{
  padding: 0.5rem;
  font-size: 2rem;
}
.search:hover{
  border-radius: 0px;
  background: #e3f2fd;
  color: #0275d8;
}
.search-btn,
.search-input{
    padding: 0.5rem;
    font-size: 20px;
    border: none;
    background: white;
}
.search-btn{
    color: black;
    background: #e3f2fd;
    cursor: pointer;
    font-size: 25px;
    transition: all 0.3s ease;
}
.search-btn:hover{
    color: #0275d8;
    background: #e3f2fd;
}

</style>
