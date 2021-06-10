<template>
  <div id="registrationPage">
    <h1 class="brandName">Nistagram</h1>
    <p style="font-size:15px">Reset password</p>
      <br>
      <b-form @submit.prevent="onSubmit" @reset="onReset" v-if="show">
        <b-form-group>
          <b-form-input v-model="password" @input="checkPassword" type="password" id="password" placeholder="Enter new password" required></b-form-input>
        </b-form-group>
        <b-form-group>
          <b-form-input v-model="form.repeatPassword" type="password" id="repeatPassword" placeholder="Enter new password again" required></b-form-input>
        </b-form-group>
        <ul>
          <li v-bind:class="{ is_valid: contains_eight_characters }">8-20 characters long</li>
          <li v-bind:class="{ is_valid: contains_number }">Contains Number</li>
          <li v-bind:class="{ is_valid: contains_uppercase_lowercase }">Contains Uppercase and Lowercase</li>
          <li v-bind:class="{ is_valid: contains_special_character }">Contains Special Character</li>
        </ul>
        <b-button type="submit" variant="primary" style="width:200px;" aria-describedby="signup-block">Reset password</b-button>
      </b-form>
      <hr>
  </div>
</template>

<script>
export default {
  name: 'ResetPassword',
  data() {
      return {
        form: {
          password:'',
          repeatPassword:'',
        },
        jwt: this.$route.params.jwt,
        password:'',
        msg:[],
        show: true,
        password_length: 0,
        contains_eight_characters: false,
        contains_number: false,
        contains_uppercase_lowercase: false,
        contains_special_character: false,
        valid_password: false,
        allowSignUp: false,
      }
  },
  methods:{
    onSubmit() {
        console.log(this.form);
        this.axios.put('https://localhost:8762/authentication-api/userCredentials/reset_password/' + this.jwt, this.form)
                  .then(response => { console.log(response);
                                      this.makeToast("Password changed successfully.", "success");
                                      window.location.href = "/";
                                      
                })
                  .catch(error => { console.log(error);
                                    this.makeToast("Error occurred.", "danger");
                                  });                  
      },
      onReset(event) {
        event.preventDefault()
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      },
    login:function(){
      window.location.href = "/";
    },
    makeToast(message, variant) {
        this.$root.$bvToast.toast(message, {
                              title: `Nistagram`,
                              autoHideDelay: 5000,
                              variant: variant,
                              toaster: 'b-toaster-bottom-right',
                              solid: true,
                              appendToast: false
                            })
      },
    checkPassword() {
      this.password_length = this.password.length;
			const format = /[!@#%&*_+\-;:\\,.]/;
			
      if (this.password_length > 7 && this.password_length <= 20) {
        this.contains_eight_characters = true;
      } else {
        this.contains_eight_characters = false;
			}
      if(this.password =="Nastasja@98"){
        alert("You entered black list password");
        this.valid_password = false;
      }
			
      this.contains_number = /\d/.test(this.password);
      this.contains_uppercase_lowercase = /[A-Z]/.test(this.password) && /[a-z]/.test(this.password);
			this.contains_special_character = format.test(this.password);
      
      if (this.contains_eight_characters === true &&
					this.contains_special_character === true &&
					this.contains_uppercase_lowercase === true &&
					this.contains_number === true) {
						this.valid_password = true;	
            this.form.password = 	this.password;	
      } else {
        this.valid_password = false;
      }
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
ul {
	padding-left: 20px;
	display: flex;
	flex-direction: column;
	align-items: flex-start;
}

li { 
	margin-bottom: 8px;
	color: #525f7f;
	position: relative;
}

li:before {
  content: "";
	width: 0%; height: 2px;
	background: #2ecc71;
	position: absolute;
	left: 0; top: 50%;
	display: block;
	transition: all .6s cubic-bezier(0.175, 0.885, 0.32, 1.275);
}
.is_valid { color: rgba(136, 152, 170, 0.8); }
.is_valid:before { width: 100%; }
</style>
