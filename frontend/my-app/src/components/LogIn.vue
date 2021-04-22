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
        <b-button type="submit" variant="primary" style="width:200px;">Log in</b-button>
        <br>
      </b-form>

      <hr>
      <b-link style="font-size:15px" href="/registration">Don't have an account? Sign up</b-link>
      <br>
      <b-link style="font-size:15px" href="/accRecovery">Forgot password?</b-link>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
      return {
        form: {
          username: '',
          password: '',
        },
        show: true
      }
  },
  methods:{
    onSubmit() {
        console.log(this.form);
        this.axios.post('/userCredentials', this.form)
          .then(response => {alert("User has logged in.");
                              console.log(response);})
          .catch(error => {alert("Username or password is not correct.");
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
</style>
