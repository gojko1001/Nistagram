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
            <li class="nav-item" v-if="username != null&& isAdmin ==false">
                <button class="nav-btn">
                  <i class="fas fa-envelope"></i>
                </button>
            </li>
            <li class="nav-item" v-if="username != null&& isAdmin ==false">
                <button class="heart nav-btn">
                  <i class="fas fa-heart"></i>
                </button>
            </li>
            <li class="nav-item" v-if="username != null && isAdmin ==true">
                <button class="nav-btn" @click='inappropriateContent'>
                  <i class="fas fa-ban"></i>
                </button>
            </li>
            <li class="nav-item" v-if="username != null && isAdmin ==true">
                <button class="nav-btn" @click='profileVerification'>
                  <i class="fas fa-user-check"></i>
                </button>
            </li>
            <li class="nav-item" v-if="username != null && isAdmin ==true">
                <button class="nav-btn">
                  <i class="fas fa-user-secret"></i>
                </button>
            </li>
            <li class="nav-item" v-if="username != null&& isAdmin ==false">
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
  </div>
</template>


<script>
import { SERVER_NOT_RESPONDING, USER_CREDENTIALS_PATH } from '../util/constants';
import { getUsernameFromToken, removeToken } from '../util/token';
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
        username:'',
        searchInput:'',
        isAdmin: true,
      }
  },
  mounted: function(){
    this.username = getUsernameFromToken();
      if(this.username != null){
        this.axios.get(USER_CREDENTIALS_PATH + '/isAdmin/' + this.username).then(response => {
                                this.isAdmin = response.data;
                                console.log(response.data);
            }).catch(error => { if(!error.response) {
                                    this.makeToast(SERVER_NOT_RESPONDING, "warning");
                                    return
                                }
            })
    }
    
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
    search: function() {
      localStorage.setItem("name", this.selected);
      localStorage.setItem("find", this.searchInput);
      this.$router.push({path: '/search'})        
    },
    profileVerification: function(){
      window.location.href = "/all_requests";
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
