<template>
    <div id="notificationPage">
      <table style="text-align: center;margin: auto;margin-top: 40px;margin-bottom: 40px;width: 50%; ">
      <tr v-for="(notifi, i) in notifications" :key="i">
        <td style="font-size:20px">{{notifi.message}} <br>{{notifi.timestamp | formatDate}}</td>
        <td>
          <br>
          <img v-if="notifi.imageByte.image" v-bind:src="notifi.imageByte.imageByte" width="100" height="100" style="display:block; margin-left:auto; margin-right:auto">
          <video autoplay controls v-if="!notifi.imageByte.image" v-bind:src="notifi.imageByte.imageByte" width="100" height="100" style="display:block; margin-left:auto; margin-right:auto">
              The video is not supported by your browser.
          </video>
          <br>
        </td>
      </tr>
    </table>

    </div>
</template>



<script>
import { getUsernameFromToken } from '../util/token';
export default {
    name: 'Notification',
    data(){
        return{
            username:'',
            notifications:[],
        }
    },
    mounted: function(){
        this.username = getUsernameFromToken();
        this.axios.get('/notification-api/media/' + this.username)
                .then(response => { this.notifications = response.data;
                                    for(var i=0; i<this.notifications.length; i++){
                                      if(this.notifications[i].imageByte.image){
                                        this.notifications[i].imageByte.imageByte = 'data:image/jpeg;base64,' + this.notifications[i].imageByte.imageByte; 
                                      }else{
                                        this.notifications[i].imageByte.imageByte = 'data:video/mp4;base64,' + this.notifications[i].imageByte.imageByte;
                                      }
                                    }
                                    
                }).catch(error => { console.log(error.message);
                                    this.makeToast("Error occurred.", "danger");
            });
            
    }
}
</script>



<style scoped>
table, td {
  text-align: left;
  border-bottom: 1px solid black;
}
</style>
