<template>
  <div id="registrationAgent">
      <b-form @submit.prevent="onSubmit" @reset="onReset" v-if="show">
        <b-form-group id="input-group-1">
          <b-form-input
            id="email"
            v-model="email"
            type="email"
            placeholder="Enter email"
            required
          ></b-form-input>
          <span v-if="msg.email" style="color:red;">{{msg.email}}</span>
        </b-form-group>

        <b-form-group>
          <b-form-input
            id="fullName"
            v-model="fullName"
            placeholder="Enter full name"
            required
          ></b-form-input>
          <span v-if="msg.fullName" style="color:red;">{{msg.fullName}}</span>
        </b-form-group>

        <b-form-group>
          <b-form-input
            id="username"
            v-model="username"
            placeholder="Enter username"
            required
          ></b-form-input>
          <span v-if="msg.username" style="color:red;">{{msg.username}}</span>
        </b-form-group>

        <b-form-group>
          <b-form-input
            id="webSite"
            v-model="webSite"
            placeholder="Enter web site"
            required
          ></b-form-input>
          <span v-if="msg.username" style="color:red;">{{msg.username}}</span>
        </b-form-group>

        <b-form-group>
          <b-form-input v-model="password" @input="checkPassword" type="password" id="password" placeholder="Enter password" required></b-form-input>
        </b-form-group>
        <ul>
          <li v-bind:class="{ is_valid: contains_eight_characters }">8-20 characters long</li>
          <li v-bind:class="{ is_valid: contains_number }">Contains Number</li>
          <li v-bind:class="{ is_valid: contains_uppercase_lowercase }">Contains Uppercase and Lowercase</li>
          <li v-bind:class="{ is_valid: contains_special_character }">Contains Special Character</li>
        </ul>
        <br>
        <b-button type="submit" variant="primary" style="width:200px;" aria-describedby="signup-block">Register</b-button>
      </b-form>
      <hr>
  </div>
</template>

<script>
import { REGISTER_USER_PATH } from '../util/constants';
export default {
  name: 'Registration',
  data() {
      return {
        form: {
          email: '',
          password:'',
          fullName:'',
          username: '',
          webSite:'',
        },
        email: '',
        password:'',
        username:'',
        fullName:'',
        webSite:'',
        msg:[],
        show: true,
        reg: /^(([^<>()\]\\.,;:\s@"]+(\.[^<>()\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,24}))$/,
        regUsername: /^(?!.*\.\.)(?!.*\.$)[^\W][\w.]{0,29}$/,
        regFullName: /^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$/,
        password_length: 0,
        contains_eight_characters: false,
        contains_number: false,
        contains_uppercase_lowercase: false,
        contains_special_character: false,
        valid_password: false,
        allowSignUp: false,
      }
  },
  watch: {
    email(value){
      this.form.email = value;
      this.validateEmail(value);
    },
    username(value){
      this.form.username = value;
      this.validateUsername(value);
    },
    fullName(value){
      this.form.fullName = value;
      this.validateFullName(value);
    }
  },
  methods:{
    onSubmit() {
        console.log(this.form);
        this.axios.post(REGISTER_USER_PATH, this.form)
                  .then(response => { console.log(response);
                                      this.makeToast("User has been registered successfully. Check your email.", "success");
                                      window.location.href = "/login";
                                      
                })
                  .catch(error => { console.log(error);
                                    this.makeToast("Error occurred. User has not been registered successfully.", "danger");
                                  });                  
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
        this.$root.$bvToast.toast(message, {
                              title: `Nistagram`,
                              autoHideDelay: 5000,
                              variant: variant,
                              toaster: 'b-toaster-bottom-right',
                              solid: true,
                              appendToast: false
                            })
      },
    validateEmail(value){
      if (this.reg.test(value))
      {
        this.msg['email'] = '';
      } else{
        this.msg['email'] = 'Invalid Email Address. Email format is: mymail@mail.com';
      } 
    },
    validateFullName(value){
      if (this.regFullName.test(value))
      {
        this.msg['fullName'] = '';
      } else{
        this.msg['fullName'] = 'Invalid full name format.';
      } 
    },
    validateUsername(value){
      if (this.regUsername.test(value))
      {
        this.msg['username'] = '';
      } else{
        this.msg['username'] = 'Invalid username format.';
      } 
    },
    checkPassword() {
      this.password_length = this.password.length;
			const format = /[!@#%&*_+\-;:\\,.]/;
			
      if (this.password_length > 7 && this.password_length <= 20) {
        this.contains_eight_characters = true;
      } else {
        this.contains_eight_characters = false;
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
#registrationAgent {
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
