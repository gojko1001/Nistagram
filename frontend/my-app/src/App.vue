<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-light" style="background-color: #e3f2fd;">
    <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <a class="navbar-brand" href="#">
            <img src="" width="40" height="40" alt="" />
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
                <a class="nav-link" routerLinkActive="active">👤</a>
            </li>
        </ul>
    </div>
    </nav>
    <router-view/>
  </div>
</template>


<script>
export default {
  name: 'App',
  components: {
  },
}
</script>

<style>
.nav-item{
  font-size: 20px;
}

</style>
