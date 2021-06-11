<template>
    <div id="discoverPage">
      <br>
        <div>
          <b-card
              tag="article"
              style="max-width: 30rem; background:transparent; box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);display:block; margin-left:auto; margin-right:auto"
              class="mb-2">
            <h4>@{{img.username}}</h4>
            <p style="color:blue">{{img.location.name}}</p>
            <img v-if="img.image" v-bind:src="img.imageBytes" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
            <video autoplay controls v-if="!img.image" v-bind:src="img.imageBytes" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto">
              The video is not supported by your browser.
            </video>
            <br>
            <button class="heart inter" v-bind:class="{'black': !img.liked, 'red': img.liked}" @click="likePost(img.id, true)">
              <i class="fas fa-thumbs-up"></i>
            </button>
            <span>{{img.numLikes}}</span>
            <button class="heart inter" v-bind:class="{'black': !img.disliked, 'red': img.disliked}" @click="likePost(img.id, false)" style="margin-left:20px">
              <i class="fas fa-thumbs-down"></i>
            </button>
            <span>{{img.numDislikes}}</span>
            <button class="inter" style="margin-left:280px" @click="showAllCollections()">
              <i class="fas fa-bookmark"></i>
            </button>
            <b-form-radio-group
              v-model="selected"
              :options="collections"
              class="mb-3"
              value-field="name"
              text-field="name"
              disabled-field="notEnabled"
              style="margin-left:280px;"
              v-if="showCollections"
            ></b-form-radio-group>
            <b-button pill v-if="showCollections" style="margin-left:330px; font-size: 15px" @click="addToCollection()">Save</b-button>
            <br>
            <b-button variant="link" v-b-modal.modal-1 class="inter" style="margin-left:280px; font-size:15px">Add new collection</b-button>
                <b-modal
                id="modal-1"
                ref="modal">
                  <b-form-input v-model="newCollection" placeholder="Add collection"></b-form-input>
                  <b-input-group-append>
                  <b-button variant="outline-dark" @click="createCollection(newCollection)">Add</b-button>
                </b-input-group-append>
              </b-modal>
            <b-card-text>
                  <span><b>{{img.username}}:  </b></span>{{img.description}}
                  <br>
                  <span v-for="(tag,t) in img.tags" :key="t">#{{tag.name}}  </span>
              </b-card-text>
              <hr>
              <span v-for="(comm,c) in img.comments" :key="c">
                  <span><b>{{comm.username}}:  </b></span>{{comm.text}}<br>
              </span>
              <br>
              <b-input-group>
                  <b-form-input v-model="form.text" placeholder="Add comment"></b-form-input>
                  <b-input-group-append>
                      <b-button variant="outline-dark" type="submit" @click="onSubmit">Post</b-button>
                  </b-input-group-append>
              </b-input-group>
          </b-card>              
        </div>
    </div>
</template>



<script>
import { getEmailFromToken } from '../util/token';
export default {
  name: 'AddComment',
  data(){
        return{
            username:'',
            img: {
              location:{},
              numLikes:'',
              numDislikes:'',
              liked: false,
              disliked: false
            },
            postId:'',
            form: {
                postId: 0,
                username: '',
                text: '',
            },
            formLike:{
              postId: 0,
              username:''
            },
            collections:[],
            selected:'',
            collection:{},
            newCollection:'',
            favourite:{},
            showCollections: false
        }
    },
  mounted: function(){
    this.postId = this.$route.params.id;
    this.username = getEmailFromToken();
    this.axios.get('/media-api/image/' + this.postId)
        .then(response => { this.img = response.data;
                            if(this.img.image){
                              this.img.imageBytes = 'data:image/jpeg;base64,' + this.img.imageBytes; 
                            }else{
                              this.img.imageBytes = 'data:video/mp4;base64,' + this.img.imageBytes;
                            }
                            this.img.numLikes = 0;
                            this.img.numDislikes = 0;
                            if(this.img.likes.length > 0){
                                for(var like of this.img.likes){
                                    if(like.liked){
                                      this.img.numLikes += 1;
                                    }else if(!like.liked){
                                      this.img.numDislikes += 1;
                                    }
                                    if(like.username == this.username){
                                        if(like.liked){
                                            this.img.liked = true;
                                        }else{
                                            this.img.disliked = true;
                                        } 
                                    }
                                }
                            }
        })
        .catch(error => { console.log(error);
                            this.makeToast("Error occured. Try again later.", "danger");
        })
        this.getCollections();
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
      onSubmit() {
        console.log(this.form);
        this.form.postId = this.postId;
        this.form.username = getEmailFromToken();
        this.axios.post('/media-api/comment', this.form)
          .then(response => { console.log(response.data);
                              this.makeToast("Comment has been posted.", "success");
                              window.location.href = '/profile/' + this.form.username;
                            })
          .catch(error => { console.log(error);
                            this.makeToast("Error occured.", "danger");
                          })
      },
      likePost(id, liked) {
        console.log(this.form);
        this.formLike.postId = id;
        this.formLike.username = getEmailFromToken();
        this.formLike.liked = liked;
        this.axios.post('/like', this.formLike)
          .then(response => { console.log(response.data);
                              this.makeToast("Liked !!!", "success");
                            })
          .catch(error => { console.log(error);
                            this.makeToast("Error occured.", "danger");
                          })
      },
      getCollections() {
        var user = getEmailFromToken();
        this.axios.get('/media-api/collection/' + user)
          .then(response => { this.collections = response.data;
                              if(this.collections,length > 0){
                                this.selected = this.collections[0].name;
                              }
                            })
          .catch(error => { console.log(error);
                            this.makeToast("Error occured.", "danger");
                          })
      },
      createCollection(name){
        this.collection.username = getEmailFromToken();
        this.collection.name = name;
        this.axios.post('/media-api/collection', this.collection)
          .then(response => { console.log(response.data)
                              this.makeToast("New collection.", "success");
                            })
          .catch(error => { console.log(error);
                            this.makeToast("Error occured.", "danger");
                          })
      },
      addToCollection() {
        this.favourite.postId = this.img.id;
        console.log(this.selected)
        this.favourite.collectionName = this.selected;
        this.axios.post('/media-api/favourite', this.favourite)
          .then(response => { console.log(response.data)
                              this.makeToast("Post has been added to collection.", "success");
                            })
          .catch(error => { console.log(error);
                            this.makeToast("Error occured.", "danger");
                          })
      },
      async showAllCollections(){
        if(this.showCollections){
          this.showCollections = false;
        }else{
          this.showCollections = true;
        }
        
      }
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
