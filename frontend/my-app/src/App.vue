<template>
  <div>
    <link rel="stylesheet" 
        href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" 
        integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" 
        crossorigin="anonymous">
    <Navbar />
    <router-view/>
  </div>
</template>


<script>
import Navbar from './components/Navbar'
import SockJS from 'sockjs-client'
import Stomp from 'webstomp-client'
import moment from 'moment'
import { getUsernameFromToken} from './util/token';
export default {
  name: 'App',
  components: {
    Navbar,
  },
  data(){
        return{
            received_messages: [],
            connected: false,
            notify:[],
            username:''
        }
  },
  mounted: function(){
    this.connect();
  },
  methods:{
    connect () {
        this.username = getUsernameFromToken();
        if(this.username != null){
          this.socket = new SockJS('http://localhost:8762/notification-api/socket')
                  this.stompClient = Stomp.over(this.socket)
                  this.stompClient.connect({}, (frame) => {
                    this.connected = true
                    console.log(frame);
                    this.stompClient.subscribe('/topic/server-broadcaster', (tick) => {
                      this.notify = JSON.parse(tick.body);
                      if(this.notify.receiver == this.username){
                        this.makeToast(this.notify.message + ' at ' + moment(String(this.notify.timestamp)).format('DD-MM-YYYY hh:mm') , "info");
                      }
                      
                    })
                  }, (error) => {
                    console.log(error)
                    this.connected = false
                  })
          }
    },
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

<style>
.brandName{
  font-family:Apple Chancery, cursive;
  font-weight: 800;
}
.customForm{
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: auto;
  margin-top: 60px;
  width: 25%;
  border: 3px solid lightblue;
  padding: 50px;
}
.clickable {
    cursor:pointer;
}
body{
    background-image: linear-gradient(180deg, #ffffff, #e3f2fd, #47b0fa);
    color: white;
    font-family: "Poppins", sans-serif;
    min-height: 100vh;
}
</style>
