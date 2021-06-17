<template>
    <div id="allVerificationRequests">
      <br>
        <div v-for="(request,i) in info" :key="i">
          <b-card
              tag="article"
              style="max-width: 30rem; background:transparent; box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);display:block; margin-left:auto; margin-right:auto"
              class="mb-2">
            <h2 style="text-align: center;">Verification request {{request.id}}</h2>
            <hr/>
            <h4>Full name: {{request.fullName}}</h4><br/>
            <h5>Category: {{request.category.name}}</h5>

            <div>
              <img v-bind:src="request.imageBytes" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
            </div>
            <hr/>

            
          </b-card>              
        </div>
    </div>
</template>



<script>
import { SERVER_NOT_RESPONDING, USER_PATH } from '../util/constants';
import { getEmailFromToken } from '../util/token';
export default {
  name: 'AllVerificationRequest',
  data(){
        return{
            user: '',
            username:'',
            info: [{
              imageBytes: '',
              fullName:'',
              category:'',
            }],
        }
    },
  mounted: function(){
    this.username = getEmailFromToken();
    if(this.username != null){
        this.axios.get(USER_PATH + '/' + this.username, {   headers:{
                                                                Authorization: "Bearer " + localStorage.getItem('JWT'),
                                                            }                                          
            }).then(response => {
                                this.user = response.data;
                                console.log(response.data);
            }).catch(error => { if(!error.response) {
                                    this.makeToast(SERVER_NOT_RESPONDING, "warning");
                                    return
                                }
            })
    }
    this.$nextTick(function () {
      this.axios.get('/user-api/verify')
                  .then(response => { console.log(response.data)
                                      this.info = response.data;
                                      for(let i=0; i< response.data.length; i++){
                                        this.info[i].imageBytes = 'data:image/jpeg;base64,' + this.info[i].imageBytes; 
                                      }  
                  }).catch(error => { console.log(error);
                                      this.makeToast("Error occurred.", "danger");
          });
    })
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
</style>
