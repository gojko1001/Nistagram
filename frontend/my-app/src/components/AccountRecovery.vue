<template>
    <div class="customForm">
      <h1 class="brandName">Recover your account</h1>
      <br><br>
      <p>Please enter your email address so we can send you link to reset your password:</p>
      <b-form @submit="onSubmit">
        <b-form-group id="input-group-1">
          <b-form-input id="email" v-model="email"
            type="email"
            placeholder="Email"
            required
          ></b-form-input>
        </b-form-group>

        <b-button variant="primary" style="width: 100%"  type="submit">Email me link</b-button>
        <b-button variant="secondary" style="width: 100%; margin-top:5px"  href="/login">Cancel</b-button>
        <br>
      </b-form>
  </div>
</template>

<script>
import { EMAIL_RESET_LINK_PATH, SERVER_NOT_RESPONDING, USER_PATH } from '../util/constants';
import { getUsernameFromToken } from '../util/token';
export default {
    name: 'AccountRecovery',
    data() {
      return {
        email: '',
      }
    },
    mounted(){
      let username = getUsernameFromToken();
      if(username != null)
        this.axios.get(USER_PATH + "/" + username)
            .then(response => {
                        this.email = response.data.email;
            });
    },
    methods:{
        onSubmit(event){
            event.preventDefault();
            this.axios.get(EMAIL_RESET_LINK_PATH + "/" + this.email)
            .then(response => { 
                                console.log(response);
                                this.makeToast("Check your email.", "success");
                                window.location.href = "/login";
            })
             .catch(error => { 
                                console.log(error);
                                if(!error.response)
                                    this.makeToast(SERVER_NOT_RESPONDING, "danger");
                                else
                                    this.makeToast("Error occurred.", "danger");
            });    
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
    }
}
</script>
