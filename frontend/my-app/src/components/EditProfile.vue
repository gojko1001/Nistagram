<template>
    <div id="editProfilePage">
      <h1>Edit Profile</h1>
       <b-form @submit.prevent="onSubmit" @reset="onReset">
       <span>Full name:</span>
        <b-form-input v-model="form.fullName" type="text" id="fullName" required>{{form.fullName}}</b-form-input>
        <span>Email:</span>
        <b-form-input v-model="form.email" type="email" id="email">{{form.email}}</b-form-input>
        <span>Phone:</span>
        <b-form-input v-model="form.phone" type="phone" id="phone">{{form.phone}}</b-form-input>
        <span>Gender:</span>
        <b-form-input v-model="form.userGender" type="text" id="userGender">{{form.userGender}}</b-form-input>
        <span>Birth date:</span>
        <b-form-input v-model="form.birthDate" value="form.birthDate" type="date" id="birthDate"></b-form-input>
        <span>Username:</span>
        <b-form-input v-model="form.username" type="text" id="username">{{form.username}}</b-form-input>
        <span>Web site:</span>
        <b-form-input v-model="form.webSite" type="phone" id="webSite">{{form.webSite}}</b-form-input>
        <span>Bio:</span>
        <b-form-input v-model="form.bio" type="text" id="bio"></b-form-input><br>
        <b-button type="submit" variant="primary" style="width:200px;">Edit</b-button><br>
      </b-form>
    </div>
</template>

<script>
import { USER_PATH, SERVER_NOT_RESPONDING } from "./../util/constants"
import { getEmailFromToken } from '../util/token';

export default {
  name: 'EditProfile',
  data() {
      return {
        username: getEmailFromToken(),
        form: '',
        show: true
      }
  },
  mounted: function(){
    if(!localStorage.getItem('JWT'))
      window.location.href = "/";
    this.axios.get(USER_PATH + '/' + this.username, {   headers:{
                                                                Authorization: "Bearer " + localStorage.getItem('JWT'),
                                                            }                                          
            }).then(response => {
                                this.form = response.data;
            }).catch(error => { if(!error.response) {
                                    this.makeToast(SERVER_NOT_RESPONDING, "warning");
                                    return
                                }
                                window.location.href = '/home'
            })
    
    },
    methods: {
        onSubmit() {
        this.form.pastUsername = this.username;
        this.axios.put(USER_PATH, this.form, {headers:{Authorization: "Bearer " + localStorage.getItem('JWT'),}})
          .then(response => { console.log(response);
                              this.makeToast("User has been updated successfully.", "success");
                              window.location.href = "/home";
                            })
          .catch(error => { console.log(error);
                            if(!error.response)
                              this.makeToast(SERVER_NOT_RESPONDING, "danger");
                            else
                              this.makeToast("Error while updating.", "danger");
                            })
        },
        onReset(event) {
        event.preventDefault()
        // Reset our form values
        this.axios.get(USER_PATH +'/'+ this.username)
            .then(response => {
            this.form = response;
        })
          this.form.pastUsername = getEmailFromToken(),
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      },
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

#editProfilePage {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: auto;
  margin-top: 40px;
  margin-bottom: 40px;
  width: 25%;
  border: 3px solid lightblue;
  padding: 50px;
}

</style>
