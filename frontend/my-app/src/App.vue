<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-light" style="background-color: #e3f2fd;">
    <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <a class="navbar-brand" href="#">
            <img src="" width="40" height="40" alt="" />
        </a>
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item">
                <a class="nav-link" routerLinkActive="active">Feed</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" routerLinkActive="active">✉</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" routerLinkActive="active">❤</a>
            </li>
        </ul>
    </div>
</nav>
    <LogIn title="Nistagram"/>
  </div>
</template>

<script>
import LogIn from './components/LogIn'

export default {
  name: 'App',
  components: {
    LogIn,
  },
}

</script>

<style>
.nav-item{
  font-size: 20px;
}

</style>
