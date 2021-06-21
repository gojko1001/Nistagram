<template>
    <div id="InappropriateContent">
      <br>
        <div v-for="(img,i) in pending" :key="i">
          <b-card
              tag="article"
              style="max-width: 30rem; background:transparent; box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);display:block; margin-left:auto; margin-right:auto"
              class="mb-2">
                <h4 style="margin-top:0px; margin-left: 190px">{{img.requestedBy}}</h4>
                <h4 style="margin-top:0px; margin-left: 210px">{{img.mediaId}}</h4>
                <br/>
                <br/>
                <button class="heart inter" @click="confirmation(img.id, 'confirmed')">
              <i class="fas fa-check"></i>
            </button>
            <span>{{img.numLikes}}</span>
            <button class="heart inter"  style="margin-top:0px; margin-left: 350px" @click="confirmation(img.id, 'unconfirmed')">
              <i class="fa fa-ban fa-fw"></i>
            </button>
                
          </b-card>              
        </div>
    </div>
</template>



<script>
import { SERVER_NOT_RESPONDING } from '../util/constants';
import { getUsernameFromToken } from '../util/token';
export default {
  name: 'InappropriateContent',
  data(){
        return{
            pending: [{
              id:0,
              requestedBy:'',
              mediaId:0
            }],
            dto: {
              id:0,
              confirmedBy:'',
              confirmed:''
            },
            message:'',
        }
    },
  mounted: function(){

      this.axios.get('/media-api/inappropriate/pending')
                  .then(response => { this.pending = response.data;
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
        confirmation(id, message){

            this.dto.confirmedBy = getUsernameFromToken();
            this.dto.confirmed = message;
            this.dto.id = id;
            if(this.dto.confirmedBy != null){
          this.axios.post('/media-api/inappropriate/confirmation', this.dto)
          .then(response => { console.log(response.data);
                              this.makeToast("Success!!!", "success");
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
