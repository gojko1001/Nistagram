<template>
    <div id="loginPage">
      <h1 class="brandName">Nistagram</h1>
      <br><br>
      <b-form @submit.prevent="onSubmit" @reset="onReset" v-if="show">
        <b-form-group>
          <b-form-input
            id="username"
            v-model="form.username"
            placeholder="Username"
            required
          ></b-form-input>
        </b-form-group>
        <b-form-input v-model="form.password" type="password" id="password" placeholder="Password"></b-form-input>
        <br>
        <b-button type="submit" variant="primary" style="width:100%;">Log in</b-button>
        <br>
      </b-form>

      <hr>
      <GoogleLogin class="google-login" :params="params" :renderParams="renderParams" :onSuccess="onSuccess" :onFailure="onFailure"></GoogleLogin>
      <hr>
      <b-link style="font-size:15px" href="/registration">Don't have an account? Sign up</b-link>
      <br>
      <b-link style="font-size:15px" href="/accRecovery">Forgot password?</b-link>
  </div>
</template>

<script>
import GoogleLogin from 'vue-google-login';
import { getRoleFromToken, getUsernameFromToken, saveToken } from "./../util/token"
import { LOGIN_PATH, SERVER_NOT_RESPONDING, LOGIN_GOOGLE_PATH } from "./../util/constants"

export default {
  name: 'Login',
  components: {
      GoogleLogin
  },
  data() {
      return {
        params: {
            client_id: "1070339341419-s21kkmq518ufq3evcjmt7sgq8mh5kph1.apps.googleusercontent.com"
        },
        renderParams: {
                    width: 250,
                    height: 50,
                    longtitle: true
        },
        form: {
          username: '',
          password: '',
        },
        user: {
          name: '',
          email: '',
        },
        userGoogle: '',
        show: true
      }
  },
  beforeMount(){
    if(getUsernameFromToken() != null)
      window.location.href = "/";
  },
  methods:{
    onSuccess(googleUser) {
      // This only gets the user information: id, name, imageUrl and email
      this.userGoogle = googleUser.getBasicProfile();
      this.user.name = this.userGoogle.Ue;
      this.user.email = this.userGoogle.ou;
      this.axios.post(LOGIN_GOOGLE_PATH, this.user)
        .then(response => {
          this.makeToast("User has been logged in successfully.", "success");
          saveToken(response.data);
        
          window.location.href = "/";
        })
        .catch(error => { console.log(error);
                            if(!error.response)
                              this.makeToast(SERVER_NOT_RESPONDING, "danger");
                            else
                              this.makeToast("Can not sign in with google.", "danger");
                          })

    },
    onFailure(){},
    onSubmit() {
        console.log(this.form);
        this.axios.post(LOGIN_PATH, this.form)
          .then(response => { console.log(response);
                              this.makeToast("User has been logged in successfully.", "success");
                              saveToken(response.data);
                              if(getRoleFromToken() == 'ROLE_ADMIN')
                                window.location.href = "/inappropriate_content";
                              else
                                window.location.href = "/";
                            })
          .catch(error => { console.log(error);
                            if(!error.response)
                              this.makeToast(SERVER_NOT_RESPONDING, "danger");
                            else
                              this.makeToast("Username or password is not correct.", "danger");
                          })
      },
    onReset(event) {
        event.preventDefault()
        // Reset our form values
        this.form.email = ''
        this.form.name = ''
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
  },
}
</script>


<style scoped>
#loginPage {
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
.google-login{
  width:100%;
  margin-left: 15px;
}
</style>
