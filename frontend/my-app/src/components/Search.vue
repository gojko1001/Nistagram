<template>
    <div id="searchPage">
      <h1>This is the search page {{searchingType}}, and I want {{searchingInput}}</h1>
      <br>
      <table >
      <thead>
        <tr>
          <td>Name</td>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(res,i) in results" :key="i">
          <td> #{{res.name}}</td>
        </tr>
      </tbody>
    </table>
    </div>
</template>

<script>
export default {
  name: 'Search',
  data(){
        return{
            searchingType: '',
            searchingInput: '',
            results: []
        }
    },
  mounted: function(){
        this.searchingType = this.$route.params.name;
        this.searchingInput = this.$route.params.find;
        this.axios.get('/' + this.searchingType)
          .then(response => { 
                              this.results = response.data;
                              console.log(this.results);
                            })
          .catch(error => { console.log(error);
                            this.makeToast("Error occured.", "danger");
                            window.location.href = "/home";
                          })
    },
methods:{
    makeToast(message, variant) {
      this.$bvToast.toast(message, {
                            title: `Nistagram`,
                            autoHideDelay: 5000,
                            variant: variant,
                            toaster: 'b-toaster-bottom-right',
                            solid: true,
                            appendToast: false
                          })
    }
  },
}
</script>


<style scoped>

</style>
