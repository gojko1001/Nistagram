<template>
    <div class="customForm">
      <h1 class="brandName">Recover your account</h1>
      <br><br>
      <p>Please enter your email address so we can send you link to reset your password:</p>
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group id="input-group-1">
          <b-form-input
            id="email"
            v-model="form.email"
            type="email"
            placeholder="Email"
            required
          ></b-form-input>
        </b-form-group>

        <b-button variant="secondary" style="width:100px" class="mr-3" href="/">Cancel</b-button>
        <b-button type="submit" variant="primary" style="width:200px">Email me link</b-button>
        <br>
      </b-form>
  </div>
</template>

<script>
export default {
    name: 'AccountRecovery',
    data() {
      return {
        form: {
          email: '',
        },
        show: true
      }
  },
    methods:{
        onSubmit(event){
            event.preventDefault();
            this.axios.get('https://localhost:3030/userCredentials/send_email/'+ this.form.email)
            .then(response => { console.log(response);
                this.makeToast("Check your email.", "success");
                window.location.href = "/";
            })
             .catch(error => { console.log(error);
                                    this.makeToast("Error occurred.", "danger");
                                  });    
        },
        onReset(event){
            event.preventDefault();
        }
    }
}
</script>
