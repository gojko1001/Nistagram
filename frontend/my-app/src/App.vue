<template>
  <div>
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
</style>
