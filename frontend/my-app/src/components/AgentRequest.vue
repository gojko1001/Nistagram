<template>
    <div id="AgentRequest">
      <form>

        <b-form-group id="input-group-1">
          <b-form-input
            id="webSite"
            v-model="AgentRequest.webSite"
            placeholder="Enter webSite"
            required
          ></b-form-input>
        </b-form-group>
        <b-button type="submit" variant="primary" style="width:200px;" @click="send()" aria-describedby="signup-block">Send</b-button>
      </form>
        
    </div>
</template>

<script>
import { USER_ADMIN_REQUEST } from '../util/constants';
import { getUsernameFromToken } from '../util/token';
export default {
  name: 'AgentRequest',
  data() {
      return {
          AgentRequest: {username:'', email:'', webSite:''},
           
      }
      
    },
  methods:{
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
    send(){
      this.AgentRequest.username = getUsernameFromToken();
      this.axios.post(USER_ADMIN_REQUEST, this.AgentRequest)
                  .then(response => { console.log(response);
                                      this.makeToast("Request successfully sent.", "success");
                                      
                })
                  .catch(error => { console.log(error);
                                    this.makeToast("Error occurred.", "danger");
                                  });             
    }   
  }
}
</script>

<style scoped>
#AgentRequest {
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
.uploadBtn{
    width:100px;
    color: #80A1D4;
    border-radius: 7px
}

</style>
