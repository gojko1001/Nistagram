<template>
    <div id="loginPage">
      <h1>Nistagram</h1>
      <br><br>
      <!--<b-form @submit="onSubmit" @reset="onReset" v-if="show">
        <b-form-group id="input-group-1">
          <b-form-input
            id="email"
            v-model="form.email"
            type="email"
            placeholder="Enter email"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-input type="password" id="password" aria-describedby="password-help-block" placeholder="Enter password"></b-form-input>
        <b-form-text id="password-help-block">
          Your password must be 8-20 characters long, contain letters and numbers, and must not
          contain spaces or special characters.
        </b-form-text>

        <br>
        <b-button type="submit" variant="primary" style="width:200px;">Log in</b-button>
        <br>
      </b-form>-->

      <hr>
      <b-link style="font-size:15px" @click="signIn">Don't have an account? Sign up</b-link>
      <br>
      <b-link style="font-size:15px">Forgot password?</b-link>
  </div>
</template>

<script>
export default {
  name: 'Login',
  methods:{
    signIn:function(){
      console.log("evo gaaa");
      window.location.href = "/registration";
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
  margin-top: 60px;
  width: 25%;
  border: 3px solid lightblue;
  padding: 10px;
}
</style>
