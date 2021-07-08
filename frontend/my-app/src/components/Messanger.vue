<template>
		<div class="container-fluid h-100">
			<div class="row justify-content-center h-100">
				<div class="col-md-4 col-xl-3 chat"><div class="card mb-sm-3 mb-md-0 contacts_card">
					<div class="card-header">
						<div class="input-group">
							<input v-model="searchInput" type="text" placeholder="Search..." name="" class="form-control search">
							<div class="input-group-prepend" @click="search()">
								<span class="input-group-text search_btn"><i class="fas fa-search"></i></span>
							</div>
						</div>
					</div>
					<div class="card-body contacts_body">
						<ui class="contacts">
						<li v-bind:class="{'active': receiver == user.username}" v-for="user in users" :key="user.users">
							<div class="d-flex bd-highlight" @click="selectReceiver(user)">
								<div class="img_cont">
									<img src="../assets/user-no-picture.png" class="rounded-circle user_img">
								</div>
								<div class="user_info">
									<span>{{user.username}}</span>
								</div>
							</div>
						</li>
						</ui>
					</div>
					<div class="card-footer"></div>
				</div></div>
				<div class="col-md-8 col-xl-6 chat">
					<div class="card">
						<div class="card-header msg_head">
							<div class="d-flex bd-highlight">
								<div class="img_cont">
									<img src="../assets/user-no-picture.png" class="rounded-circle user_img">
								</div>
								<div class="user_info">
									<span>Chat with {{receiver}}</span>
								</div>
							</div>
						</div>
						<div class="card-body msg_card_body">
							<div v-for="message in messages" :key="message.id">
								<div v-if="(message.sender == receiver) && (message.receiver == username)" class="d-flex justify-content-start mb-4">
									<div class="img_cont_msg">
										<img src="../assets/user-no-picture.png" class="rounded-circle user_img_msg">
									</div>
									<div class="msg_cotainer">
										<span v-if="!message.link">{{message.content}}</span><br>
										<b-button v-if="message.hasMedia && !message.viewed" @click="viewed(message)">Open</b-button>
										<span class="span-info" v-if="message.hasMedia && message.viewed">Opened</span><br/>
										<span class="span-info" v-if="message.privateLink">Link is private</span>
										<span v-if="!message.privateLink">{{message.link}}</span>
										<span class="msg_time">{{message.date}}</span>
									</div>
								</div>
								<div v-if="(message.sender == username) && (message.receiver == receiver) " class="d-flex justify-content-end mb-4">
									<div class="msg_cotainer_send">
										{{message.content}}
										<br><span class="span-info" v-if="message.hasMedia">Sent media</span>
										<span class="msg_time_send">{{message.date}}</span>
									</div>
									<div class="img_cont_msg">
										<img src="../assets/user-no-picture.png" class="rounded-circle user_img_msg">
									</div>
								</div>
								<div v-if="message.needRequest">
									<b-button @click="accept(message.id)">Accept</b-button>
									<b-button @click="denied(message.id)">Denied</b-button>
									<b-button @click="deleteMessage(message.id)">Delete</b-button>
								</div>
							</div>
						</div>
						<div class="card-footer">
							<div class="input-group">
								<div v-b-modal.modal-2 class="input-group-append">
									<span class="input-group-text attach_btn"><i class="fas fa-paperclip"></i></span>
								</div>
								<textarea v-model="message.content" name="" class="form-control type_msg" placeholder="Type your message..."></textarea>
								<div class="input-group-append" @click="createMessage">
									<span class="input-group-text send_btn"><i class="fas fa-location-arrow"></i></span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<b-modal ref="modal-1" id="modal-1" title="Media">
				<div>
					<div v-for="(img, q) in mediaMessage.imageBytes" :key="'D'+q">
						<img v-if="img.image" v-bind:src="img.imageByte" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
						<video autoplay controls v-if="!img.image" v-bind:src="img.imageByte" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
							The video is not supported by your browser.
						</video>
					</div>
				</div>
            </b-modal>
			<b-modal id="modal-2" title="Upload media">
				<div>
					<input type="file" ref="uploadImage" @change="onImageUpload()" class="form-control" required multiple>
					<input type="button" @click="startupload()" name="Upload" value="Upload" />             
				</div>
            </b-modal>
		</div>
</template>

<script>
import { getUsernameFromToken} from './../util/token';

export default {
  name: 'Chat',
  data(){
        return{
			searchInput: '',
            username:'',
			receiver: '',
			users: [
			],
			messages: [],
			formData:null,
			message: {
				content: '',
				sender: '',
				receiver: '',
				fileNames: [],
			},
			mediaMessage: {},
			connected: false,
        }
  },
  mounted: function(){
	this.username = getUsernameFromToken();
	console.log(this.username);
    if(this.username != null){
		this.axios.get('http://localhost:8762/user-api/user/chat_users/' + this.username).then(response =>{
			console.log(response.data);
			this.users = response.data;
		})
		window.setInterval(() => {
			this.getMessages()}, 1000);
	}else{
		window.location.href = "/login";
	}
  },
  methods:{

	getMessages(){
		this.axios.get('http://localhost:8762/messenger-api/' + this.username).then(response => {
			this.messages = response.data;
			console.log(response.data);
			for(let i=0; i< response.data.length; i++){
				for(let j=0; j<this.messages[i].imageBytes.length; j++){
					if(this.messages[i].imageBytes[j].isImage){
						this.messages[i].imageBytes[j].imageByte = 'data:image/jpeg;base64,' + this.messages[i].imageBytes[j].imageByte; 
					}else{
						this.messages[i].imageBytes[j].imageByte = 'data:video/mp4;base64,' + this.messages[i].imageBytes[j].imageByte;
					}
				}
			}
		})
	},

	onImageUpload(){
        this.formData = new FormData();
        for(var i=0; i<this.$refs.uploadImage.files.length; i++){
          let file = this.$refs.uploadImage.files[i];
          this.formData.append("file", file);
        }
    },

	startupload(){
        this.axios.post('/messenger-api/image', this.formData, {
          headers:{
            Accept: 'application/json',
            'Content-Type':'multipart/form-data'
          }
        }).then(response => {
          this.message.fileNames = response.data;
          this.makeToast("Image has been uploaded.", "success");
        })
    },

	createMessage(){
		this.message.sender = this.username;
		this.message.receiver = this.receiver;
		this.axios.post('/messenger-api', this.message).then(response => {
			console.log(response);
			this.message =  {
				content: '',
				sender: '',
				receiver: '',
				fileNames: [],
			};
		})
	},

	viewed: function(message){
		this.mediaMessage = message;
		console.log(this.mediaMessage);
		this.$refs['modal-1'].show();
		this.axios.put('/messenger-api/' + message.id).then(response => {
			console.log(response);
		})
	},

	hideModal() {
        this.$refs['modal-1'].hide();
    },

	selectReceiver: function(receiverUser){
		this.receiver = receiverUser.username;
	},

	search: function(){
		this.axios.get('http://localhost:8762/user-api/user/chatable_users/' + this.username + '/' + this.searchInput).then(response =>{
			console.log(response.data);
			this.users = response.data;
		})
	},

	accept: function(id){
		this.axios.put('/messenger-api/request/' + id).then(response => {
			console.log(response);
		})
	},

	denied: function(id){
		this.axios.put('/messenger-api/request/' + id).then(response => {
			console.log(response);
		})
	},

	deleteMessage: function(id){
		this.axios.delete('/messenger-api/' + id).then(response => {
			console.log(response);
		})
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
	},
}
</script>


<style scoped>
	.chat{
		margin-top: 50px;
		margin-bottom: auto;
	}
	.card{
		height: 500px;
		border-radius: 15px !important;
		background-color: #54b0f3 !important;
	}
	.contacts_body{
		padding:  0.75rem 0 !important;
		overflow-y: auto;
		white-space: nowrap;
	}
	.msg_card_body{
		overflow-y: auto;
	}
	.card-header{
		border-radius: 15px 15px 0 0 !important;
		border-bottom: 0 !important;
	}
	.card-footer{
		border-radius: 0 0 15px 15px !important;
		border-top: 0 !important;
	}
	.container{
		align-content: center;
	}
	.search{
		border-radius: 15px 0 0 15px !important;
		background-color: #54b0f3 !important;
		border:0 !important;
		color:white !important;
	}
	.search:focus{
		box-shadow:none !important;
        outline:0px !important;
	}
	.type_msg{
		background-color: #54b0f3 !important;
		border:0 !important;
		color:white !important;
		height: 60px !important;
		overflow-y: auto;
	}
	.type_msg:focus{
		box-shadow:none !important;
        outline:0px !important;
	}
	.attach_btn{
	border-radius: 15px 0 0 15px !important;
	background-color: rgba(0,0,0,0.3) !important;
	border:0 !important;
	color: white !important;
	cursor: pointer;
	}
	.send_btn{
	border-radius: 0 15px 15px 0 !important;
	background-color: rgba(0,0,0,0.3) !important;
	border:0 !important;
	color: white !important;
	cursor: pointer;
	}
	.search_btn{
		border-radius: 0 15px 15px 0 !important;
		background-color: rgba(0,0,0,0.3) !important;
		border:0 !important;
		color: white !important;
		cursor: pointer;
	}
	.contacts{
		list-style: none;
		padding: 0;
	}
	.contacts li{
		width: 100% !important;
		padding: 5px 10px;
		margin-bottom: 15px !important;
	}
	.active{
		background-color: rgba(0,0,0,0.3);
	}
	.user_img{
		height: 70px;
		width: 70px;
		border:1.5px solid #f5f6fa;
	}
	.user_img_msg{
		height: 40px;
		width: 40px;
		border:1.5px solid #f5f6fa;
	}
	.img_cont{
		position: relative;
		height: 70px;
		width: 70px;
	}
	.img_cont_msg{
		height: 40px;
		width: 40px;
	}
	.online_icon{
		position: absolute;
		height: 15px;
		width:15px;
		background-color: #4cd137;
		border-radius: 50%;
		bottom: 0.2em;
		right: 0.4em;
		border:1.5px solid white;
	}
	.user_info{
		margin-top: auto;
		margin-bottom: auto;
		margin-left: 15px;
	}
	.user_info span{
		font-size: 20px;
		color: white;
	}
	.user_info p{
	font-size: 10px;
	color: rgba(255,255,255,0.6);
	}
	.msg_cotainer{
		margin-top: auto;
		margin-bottom: auto;
		margin-left: 10px;
		border-radius: 25px;
		background-color: #82ccdd;
		padding: 10px;
		position: relative;
	}
	.msg_cotainer_send{
		margin-top: auto;
		margin-bottom: auto;
		margin-right: 10px;
		border-radius: 25px;
		background-color: #78e08f;
		padding: 10px;
		position: relative;
	}
	.msg_time{
		position: absolute;
		left: 0;
		bottom: -15px;
		color: rgba(255,255,255,0.5);
		font-size: 10px;
	}
	.msg_time_send{
		position: absolute;
		right:0;
		bottom: -15px;
		color: rgba(255,255,255,0.5);
		font-size: 10px;
	}
	.msg_head{
		position: relative;
	}
	.span-info{
		background-color: gray;
		color: white;
		border-radius: 15px;
	}
	@media(max-width: 576px){
        .contacts_card{
            margin-bottom: 15px !important;
        }
    }
</style>
