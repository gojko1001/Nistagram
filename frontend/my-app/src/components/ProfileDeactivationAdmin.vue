<template>
    <div id="ProfileDeactivation">
      <br>
        <div v-for="(img,i) in users" :key="i">
          <b-card
              tag="article"
              style="max-width: 30rem; background:transparent; box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);display:block; margin-left:auto; margin-right:auto"
              class="mb-2">
                <h4 style="margin-top:0px; margin-left: 190px">{{img}}</h4>
                <br/>
                <br/>
                <b-button type="submit" variant="primary"  style="width:200px ; margin-top:0px; margin-left: 170px" aria-describedby="signup-block" @click="deactivate(img)">Deactivate</b-button>
            
                
          </b-card>              
        </div>
    </div>
</template>

<script>
import { SERVER_NOT_RESPONDING } from '../util/constants';
import { getUsernameFromToken } from '../util/token';
export default {
  name: 'ProfileDeactivation',
  data(){
        return{
            users:[],
            username:'',
        }
    },
  mounted: function(){

      this.axios.get('/authentication-api/userCredentials/getUsers')
                  .then(response => { this.users = response.data;
                  }).catch(error => { console.log(error);
                                      this.makeToast(SERVER_NOT_RESPONDING, "warning");
          });

  },
   methods:{
      makeToast(message, variant) {
            this.$bvToast.toast(message, {
                                title: `Nistagram`,
                                autoHideDelay: 5000,
                                variant: variant,
                                toaster: 'b-toaster-bottom-right',
                                solid: true,
                                appendToast: false
                            })
        },
        deactivate(user){

            this.username = getUsernameFromToken();
            if(this.username != null){
          this.axios.put('http://localhost:8762/authentication-api/userCredentials/deactivate/' + user)
          .then(response => { console.log(response.data);
                              this.makeToast("Success!!!", "success");
                              window.location.reload();
                            })
          .catch(error => { console.log(error);
                            this.makeToast("Error occured.", "danger");
                          })
        }else{
          this.makeToast("Please log in.", "info");
        }
        },

   }
}
</script>



<style scoped>
.float{
	position:fixed;
	width:60px;
	height:60px;
	bottom:40px;
	right:40px;
	background-color:#155CA2;
	color:white;
	border-radius:50px;
	text-align:center;
	box-shadow: 2px 2px 3px #999;
}
.my-float{
	margin-top:22px;
}
.inter{
  color:black;
  font-size: 25px;
  background: transparent;
  border: none;
}
.link{
  margin-left: 20px;
}
.black {
  color: black;
}
.red {
  color: red;
}
</style>
