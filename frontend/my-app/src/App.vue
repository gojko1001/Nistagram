<template>
  <div>
    <link rel="stylesheet" 
        href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" 
        integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" 
        crossorigin="anonymous">
    <Navbar />
    <router-view/>
  </div>
</template>


<script>
import Navbar from './components/Navbar'
export default {
  name: 'App',
  components: {
    Navbar,
  },
}
</script>

<style>
.brandName{
  font-family:Apple Chancery, cursive;
  font-weight: 800;
}
.customForm{
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: auto;
  margin-top: 60px;
  width: 25%;
  border: 3px solid lightblue;
  padding: 50px;
}
body{
    background-image: linear-gradient(180deg, #ffffff, #e3f2fd, #47b0fa);
    color: white;
    font-family: "Poppins", sans-serif;
    min-height: 100vh;
}
</style>
