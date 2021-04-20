<template>
  <div id="navbarPage">
    <nav class="navbar navbar-expand navbar-light" style="background-color: #e3f2fd;">
    <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <a class="navbar-brand" href="/">
            <img src="../assets/logo.jpg"  height="40" alt="" />
            <span class="brandName">Nistagram</span>
        </a>
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
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
        <form class="form-inline my-2 my-lg-0">
              <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
              <button class="btn btn-outline-secondary my-2 my-sm-0" type="submit">Search</button>
        </form>
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
.brandName{
  font-family:Apple Chancery, cursive;
  font-weight: 800;
  font-size: 25px;
  color:rgb(66, 87, 204);
  margin-left: 10px;
}
</style>
