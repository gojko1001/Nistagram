<template>
		<div class="container-fluid h-100">
			<div class="row justify-content-center h-100">
				<div class="col-md-4 col-xl-3 chat"><div class="card mb-sm-3 mb-md-0 contacts_card">
					<div class="card-header">
						<div class="input-group">
							<input type="text" placeholder="Search..." name="" class="form-control search">
							<div class="input-group-prepend">
								<span class="input-group-text search_btn"><i class="fas fa-search"></i></span>
							</div>
						</div>
					</div>
					<div class="card-body contacts_body">
						<ui class="contacts">
						<li class="active"  v-for="user in users" :key="user.users">
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
				<div v-if="receiver != ''" class="col-md-8 col-xl-6 chat">
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
										{{message.content}}
										<span class="msg_time">{{message.date}}</span>
									</div>
								</div>
								<div v-if="(message.sender == username) && (message.receiver == receiver) " class="d-flex justify-content-end mb-4">
									<div class="msg_cotainer_send">
										{{message.content}}
										<span class="msg_time_send">{{message.date}}</span>
									</div>
									<div class="img_cont_msg">
								<img src="../assets/user-no-picture.png" class="rounded-circle user_img_msg">
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer">
							<div class="input-group">
								<div class="input-group-append">
									<span class="input-group-text attach_btn"><i class="fas fa-paperclip"></i></span>
								</div>
								<textarea name="" class="form-control type_msg" placeholder="Type your message..."></textarea>
								<div class="input-group-append">
									<span class="input-group-text send_btn"><i class="fas fa-location-arrow"></i></span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</template>

<script>
import { getUsernameFromToken} from './../util/token';
export default {
  name: 'Chat',
  data(){
        return{
            username:'',
			receiver: '',
			users: [
			],
			messages: [],
        }
  },
  mounted: function(){
	this.username = getUsernameFromToken();
	console.log(this.username);
    if(this.username != null)
		this.axios.get('http://localhost:8762/user-api/user/chatable_users/' + this.username).then(response => {
		this.users = response.data;
		console.log(response.data);
		})
    this.axios.get('http://localhost:8762/messenger-api/' + this.username).then(response => {
		this.messages = response.data;
		console.log(response.data);
	})
  },
  methods:{

	selectReceiver: function(receiverUser){
		this.receiver = receiverUser.username;
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
	.offline{
		background-color: #c23616 !important;
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
	.video_cam{
		margin-left: 50px;
		margin-top: 5px;
	}
	.video_cam span{
		color: white;
		font-size: 20px;
		cursor: pointer;
		margin-right: 20px;
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
	#action_menu_btn{
		position: absolute;
		right: 10px;
		top: 10px;
		color: white;
		cursor: pointer;
		font-size: 20px;
	}
	.action_menu{
		z-index: 1;
		position: absolute;
		padding: 15px 0;
		background-color: rgba(0,0,0,0.5);
		color: white;
		border-radius: 15px;
		top: 30px;
		right: 15px;
		display: none;
	}
	.action_menu ul{
		list-style: none;
		padding: 0;
	margin: 0;
	}
	.action_menu ul li{
		width: 100%;
		padding: 10px 15px;
		margin-bottom: 5px;
	}
	.action_menu ul li i{
		padding-right: 10px;
	}
	.action_menu ul li:hover{
		cursor: pointer;
		background-color: rgba(0,0,0,0.2);
	}
	@media(max-width: 576px){
        .contacts_card{
            margin-bottom: 15px !important;
        }
    }
</style>
