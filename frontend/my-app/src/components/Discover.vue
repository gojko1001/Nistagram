<template>
    <div id="discoverPage">
        <div v-for="(img,i) in info" :key="i">
                            <b-card
                                tag="article"
                                style="max-width: 30rem; background:transparent; box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);display:block; margin-left:auto; margin-right:auto"
                                class="mb-2"
                            >
                            <h4>@{{img.username}}</h4>
                            <p style="color:blue">{{img.location.name}}</p>
                            <keep-alive>
                                <img v-bind:src="img.imageBytes" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
                            </keep-alive>
                            <b-card-text>{{img.description}}<br>
                            <span v-for="(tag,i) in img.tags" :key="i">
                                #{{tag.name}}
                            </span>
                            </b-card-text>
                         </b-card>
                            
                        </div>
        <a href="/postimage" class="float">
            <i class="fas fa-plus my-float"></i>
        </a>
    </div>
</template>



<script>
import { SERVER_NOT_RESPONDING, USER_PATH } from '../util/constants';
import { getEmailFromToken } from '../util/token';
export default {
  name: 'Discover',
  data(){
        return{
            user: '',
            username:'',
            info: [],
        }
    },
  mounted: function(){
    this.username = getEmailFromToken();
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
                                window.location.href = '/home'
            })
        this.$nextTick(function () {
        this.axios.get('/image/discover/' + this.username)
                    .then(response => { this.info = response.data;
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
</style>
