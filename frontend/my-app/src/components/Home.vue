<template>
    <div id="homePage">
      <h1>This is the home page</h1>
    </div>
</template>

<script>
export default {
  name: 'HomePage',
  mounted: function(){
    if(!localStorage.getItem('JWT'))
      window.location.href = "/login";
    }
}
</script>


<style scoped>

</style>
