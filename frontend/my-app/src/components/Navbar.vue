<template>
  <div id="navbarPage">
    <nav class="navbar navbar-expand navbar-light" style="background-color: #e3f2fd;">
    <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <a class="navbar-brand" href="/">
            <img src="../assets/logo.png" height="40" alt="" />
        </a>
        <form style="padding: 0px 0px 0px 60px;">
          <input type="text" class="search-input">
          <button class="search-btn" type="submit">
            <i class="fas fa-search"></i>
          </button>
        </form>
        <!-- <div align="center">
          <b-nav pills >
            <b-nav-item active>Explore</b-nav-item>
            <b-nav-item>Following</b-nav-item>
            <b-nav-item>Profile</b-nav-item>
            <b-nav-item disabled>Disabled</b-nav-item>
          </b-nav>
        </div> -->

        <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
            <li class="nav-item">
                <button class="nav-btn">
                  <i class="fas fa-globe"></i>
                </button>
            </li>
            <li class="nav-item">
                <button class="nav-btn">
                  <i class="fas fa-envelope"></i>
                </button>
            </li>
            <li class="nav-item">
                <button class="heart nav-btn">
                  <i class="fas fa-heart"></i>
                </button>
            </li>
            <li class="nav-item">
                <button class="nav-btn" @click='myProfile'>
                  <i class="fas fa-user"></i>
                </button>
            </li>
        </ul>
        <!-- <form class="form-inline my-2 my-lg-0">
              <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
              <button class="btn btn-outline-secondary my-2 my-sm-0" type="submit">Search</button>
        </form> -->
        
    </div>
    </nav>
  </div>
</template>


<script>
export default {
  name: 'Navbar',
  components: {
  },
  methods:{
    myProfile:function(){
      //TODO
      window.location.href = "/";
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
