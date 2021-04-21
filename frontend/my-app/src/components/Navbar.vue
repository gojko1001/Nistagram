<template>
  <div id="navbarPage">
    <nav class="navbar navbar-expand navbar-light" style="background-color: #e3f2fd;">
    <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <a class="navbar-brand" href="/">
            <img src="../assets/logo.png" height="40" alt="" />
        </a>
        <form class="form-inline my-2 my-lg-0">
            <!-- <button class="btn btn-outline-secondary my-2 my-sm-0" type="submit">🔎</button> -->
            <img src="../assets/loupeIcon.png" class="clickable ml-4 mr-1" height="30"/>
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">    
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
                <a class="nav-link" routerLinkActive="active">🌐</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" routerLinkActive="active">✉</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" routerLinkActive="active">❤</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" @click="myProfile" routerLinkActive="active">👤</a>
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
      console.log("evo gaaa");
      window.location.href = "/";
    }
  },
}
</script>

<style>
.nav-item{
  font-size: 20px;
}
.clickable:hover{
  cursor: pointer;
}
</style>
