<template>
    <div id="searchPage">
      <h1>This is the search page</h1>
    </div>
</template>

<script>
export default {
  name: 'Search',
  data(){
        return{
            searching: ''
        }
    },
  mounted: function(){
    /*if(!localStorage.getItem('JWT'))
      window.location.href = "/";*/
        this.searching = this.$route.params.name
        console.log("ovo " + this.searching);
    }
}
</script>


<style scoped>

</style>
