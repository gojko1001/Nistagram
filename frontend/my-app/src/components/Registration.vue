<template>
  <div id="registrationPage">
    <h1 class="brandName">Nistagram</h1>
    <p style="font-size:15px">Sign up to see photos and videos from your friends.</p>
      <br>
      <b-form @submit.prevent="onSubmit" @reset="onReset" v-if="show">
        <b-form-group id="input-group-1">
          <b-form-input
            id="email"
            v-model="form.email"
            type="email"
            placeholder="Enter email"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group>
          <b-form-input
            id="fullName"
            v-model="form.fullName"
            placeholder="Enter full name"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group>
          <b-form-input
            id="username"
            v-model="form.username"
            placeholder="Enter username"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-input v-model="form.password" type="password" id="password" aria-describedby="password-help-block" placeholder="Enter password"></b-form-input>
        <b-form-text id="password-help-block">
          Your password must be 8-20 characters long, contain letters and numbers, and must not
          contain spaces or special characters.
        </b-form-text>

        <br><br>
        <b-button type="submit" variant="primary" style="width:200px;" aria-describedby="signup-block">Sign up</b-button>
        <b-form-text id="signup-block">
          By signing up, you agree to our Terms , Data Policy and Cookies Policy .
        </b-form-text>
      </b-form>
      <hr>
      <b-link style="font-size:15px" @click="login">Have an account? Log in</b-link>
  </div>
</template>

<script>
export default {
  name: 'Registration',
  data() {
      return {
        form: {
          email: '',
          fullName:'',
          username: '',
          password:''
        },
        show: true
      }
  },
  methods:{
    onSubmit() {
        console.log(this.form);
        this.axios.post('/user', this.form)
          .then(response => {alert("User has been registered successfully");
                              console.log(response);})
          .catch(error => {alert("Error");
                            console.log(error)})
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
    login:function(){
      window.location.href = "/";
    }
  },
}
</script>


<style scoped>
#registrationPage {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: auto;
  margin-top: 40px;
  margin-bottom: 40px;
  width: 40%;
  border: 3px solid lightblue;
  padding: 50px;
}
</style>
