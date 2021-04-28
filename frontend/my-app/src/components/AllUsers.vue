<template>
    <div id="allUsersPage">
    <h1>Users</h1>
    <table border="1px solid black">
      <thead>
        <tr>
          <td>Username</td>
          <td>Full Name</td>
          <td>Email</td>
          <td>Phone</td>
          <td>Gender</td>
          <td>Public Profile</td>
          <td>Public DM</td>
          <td>Taggable</td>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(user,i) in users" :key="i">
          <td>{{user.username}}</td>
          <td>{{user.fullName}}</td>
          <td>{{user.email}}</td>
          <td>{{user.phone}}</td>
          <td>{{user.userGender}}</td>
          <td>{{user.publicProfile}}</td>
          <td>{{user.publicDM}}</td>
          <td>{{user.taggable}}</td>
        </tr>
      </tbody>
    </table>
    </div>
</template>

<script>
export default {
  name: 'AllUsers',
  data() {
    return{
      users: []
    }
  },
  mounted: function(){
    this.axios.get('/user', {
      headers:{
        Authorization: "Bearer " + localStorage.getItem('JWT'),
      }
    })
    .then(response => {
                        console.log(response);
                        this.users = response.data;
                      })
    .catch(error => {
                        console.error(error)
                        window.location.href = "/home";
                        this.makeToast("You don't have permission to access this page!", "warning");
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
  }
}
</script>


<style scoped>

</style>
