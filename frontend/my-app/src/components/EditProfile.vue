<template>
<div id="editProfilePage">
  <b-card no-body>
    <b-tabs pills card vertical>
      <b-tab title="Profile Info" active><b-card-text><h1>Edit Profile</h1></b-card-text>
        <!-- User info -->
        <b-form @submit.prevent="onSubmit" @reset="onReset">
          <table class="w-100 py-2">
            <tr><td>Username:</td>
            <td><b-form-input v-model="form.username" type="text" id="username">{{form.username}}</b-form-input></td></tr>
            <tr><td>Full name:</td>
            <td><b-form-input v-model="form.fullName" type="text" id="fullName" required>{{form.fullName}}</b-form-input></td></tr>
            <tr><td>Bio:</td>
            <td><b-form-input v-model="form.bio" type="text" id="bio">{{form.bio}}</b-form-input></td><br></tr>
            <tr><td>Web site:</td>
            <td><b-form-input v-model="form.webSite" type="phone" id="webSite">{{form.webSite}}</b-form-input></td></tr>
            <tr>
            <td>Gender:</td>
              <td><b-form-radio-group
                v-model="form.userGender"
                :options="genderOpts"
                class="mb-3"
                value-field="value"
                text-field="text"
                disabled-field="notEnabled"
              ></b-form-radio-group></td>
            </tr>
            <tr><td>Birth date:</td>
            <td><b-form-input v-model="form.birthDate" value="form.birthDate" type="date" id="birthDate"></b-form-input></td></tr>
            <tr><td>Email:</td>
            <td><b-form-input v-model="form.email" type="email" id="email">{{form.email}}</b-form-input></td></tr>
            <tr><td>Phone:</td>
            <td><b-form-input v-model="form.phone" type="phone" id="phone">{{form.phone}}</b-form-input></td></tr>
          </table>
        
          <b-button type="submit" variant="primary" style="width:200px;">Save changes</b-button><br>
        </b-form>
      </b-tab>
      <!-- Change password -->
      <b-tab title="Change Password"><b-card-text><h1>Change Password</h1></b-card-text>
        <b-form @submit.prevent="resetPass" @reset="onReset">
            <table class="w-100 py-2">
              <tr><td>Current password:</td>
              <b-form-input v-model="resetPassword.oldPassword" type="password" id="pass" required>{{resetPassword.oldPassword}}</b-form-input></tr>
              <tr><td>New password:</td>
              <b-form-input v-model="resetPassword.password" type="password" id="newPass">{{resetPassword.password}}</b-form-input></tr>
              <tr><td>Repeat new password:</td>
              <b-form-input v-model="resetPassword.repeatPassword" type="password" id="newPassRepeat">{{resetPassword.repeatPassword}}</b-form-input></tr>
            </table>
          
          <br><b-button type="submit" variant="primary" style="width:200px;">Change</b-button><br>
        </b-form>
      </b-tab>
       <!-- Privacy  -->
      <b-tab title="Privacy"><b-card-text><h1>Privacy</h1></b-card-text>
        <div style="text-align: left">
          <div>
            <h3>Account privacy</h3>
            <b-form-checkbox
              id="publicAcc"
              v-model="privacy.publicProfile"
              name="publicAcc"
              value=true
              unchecked-value=false>
              Public profile
            </b-form-checkbox>
            <br>
            <small class="optDesc">
              By choosing this option, your photos and videos will be visible to anyone on Internet.<br>
              If you disable it, your content will be shared only with your followers
            </small>
          </div>
          <hr>
          <div>
            <h3>Messaging</h3>
            <b-form-checkbox
              id="publicMessage"
              v-model="privacy.publicDM"
              name="publicMessage"
              value=true
              unchecked-value=false>
              Public Messaging
            </b-form-checkbox>
            <br>
            <small class="optDesc">
              By choosing this option, you allow any user to send you a message (even if they aren't follwing you)<br>
              If you disable it, only your followers will be able to message you.
            </small>
          </div>
          <hr>
          <div>
            <h3>Tagging</h3>
            <b-form-checkbox
              id="tagging"
              v-model="privacy.taggable"
              name="tagging"
              value=true
              unchecked-value=false>
              Taggable profile
            </b-form-checkbox>
            <br>
            <small class="optDesc">
              By choosing this option, you allow any user to reference you in posts, comments and tag you on posts and stories (even if they aren't follwing you)<br>
              If you disable it, no one could reference or tag you.
            </small>
          </div>
        </div>
        <br>
        <b-button type="submit" variant="primary" style="width:200px;" v-on:click="updatePrivacySettings()">Save changes</b-button><br>
      </b-tab>
      <!-- Block list -->
      <b-tab title="Block list"><b-card-text><h1>Blocked users</h1></b-card-text>
        <div v-for="(user,p) in blockedUsers" :key="p">
            <span class="clickable" v-on:click="goToProfile(user.username)">@{{user.username}} </span>
            <b-btn size="sm" variant="outline-info" class="float-right" @click="unBlock(user.username)">Unblock</b-btn><hr>
        </div>
      </b-tab>
    </b-tabs>
  </b-card>
</div>
</template>

<script>
import { USER_PATH, SERVER_NOT_RESPONDING, CHANGE_PASSWORD_PATH, GET_BLOCKED_USERS_PATH, DELETE_RELATION_PATH } from "./../util/constants"
import { getToken, getUsernameFromToken, removeToken } from '../util/token';

export default {
  name: 'EditProfile',
  data() {
      return {
        username: getUsernameFromToken(),
        form: '',
        privacy:{ username:getUsernameFromToken(), publicProfile:false, publicDM:false, taggable: false},
        blockedUsers: [],
        resetPassword: {oldPassword:'', password:'', repeatPassword:''},
        genderOpts:[
          { text: 'Male', value: 'MALE' },
          { text: 'Female', value: 'FEMALE' },
          { text: 'Other', value: 'OTHER' }
        ],
        show: true
      }
  },
  mounted: function(){
    if(getUsernameFromToken() == null){
      removeToken();
      window.location.href = "/login";
    }
      
    this.axios.get(USER_PATH + '/' + this.username, {   
                                                      headers:{ Authorization: "Bearer " + getToken()
                                                    }                                          
            }).then(response => {
                                this.form = response.data;
                                this.privacy = response.data;
            }).catch(error => { if(!error.response) {
                                    this.makeToast(SERVER_NOT_RESPONDING, "warning");
                                    return
                                }
                                window.location.href = '/home'
            });
    this.axios.get(GET_BLOCKED_USERS_PATH + "/" + this.username, {   
                                                                  headers:{ Authorization: "Bearer " + getToken()
                                                                 }
            }).then(response => {
                                this.blockedUsers = response.data;
            }).catch(err => {
                        if(!err.response)
                              this.makeToast(SERVER_NOT_RESPONDING, "danger");
                        else
                            this.makeToast(err.message, "danger");
                    })
    
    },
    methods: {
        onSubmit() {
        this.axios.put(USER_PATH, this.form, {headers:{Authorization: "Bearer " + getToken()}})
          .then(response => { console.log(response);
                              this.makeToast("User has been updated successfully.", "success");
                              if(this.username != this.form.username){
                                removeToken();
                                this.makeToast("Please login.", "info");
                                window.location.href = "";
                              }
                              window.location.href = "/user/" + this.username;
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
          this.form.pastUsername = getUsernameFromToken(),
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      },

      resetPass(){
        this.axios.put(CHANGE_PASSWORD_PATH, this.resetPassword, {
                                 headers:{ Authorization: "Bearer " + getToken()}})
                .then(() => {
                    this.makeToast("Password updated!", "success");
                    window.location.href = "/user/" + this.username;
                }).catch(error => {
                    if(!error.response)
                      this.makeToast(SERVER_NOT_RESPONDING, "danger");
                    else
                      this.makeToast("Something went wrong, try retyping passwords!", "warning");    
                })
      },
      updatePrivacySettings(){
        this.axios.put(USER_PATH, this.privacy, {headers:{Authorization: "Bearer " + getToken()}})
          .then(response => { console.log(response);
                              this.makeToast("Privacy settings updated!", "success");
                              window.location.href = "/user/" + this.username;
                            })
          .catch(error => { console.log(error);
                            if(!error.response)
                              this.makeToast(SERVER_NOT_RESPONDING, "danger");
                            else
                              this.makeToast("Error while updating.", "danger");
                            })
      },
      goToProfile(username){
        window.location.href="/user/" + username;
      },
      unBlock(toUnblock){
        this.axios.delete(DELETE_RELATION_PATH + "/" + toUnblock,{   headers:{
                                                                Authorization: "Bearer " + getToken(),
                                                            }
                    }).then(() => {
                        this.makeToast(toUnblock + " unblocked!", "success");
                        window.location.reload();
                    }).catch(err => {
                        if(!err.response)
                              this.makeToast(SERVER_NOT_RESPONDING, "danger");
                        else
                            this.makeToast(err.message, "danger");
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
  width: 80%;
  border: 3px solid lightblue;
  padding: 20px;
}

.optDesc{
  font-family: cursive;
  color: grey;
}

td{
  padding-top: 10px;
  padding-bottom: 10px
}

</style>
