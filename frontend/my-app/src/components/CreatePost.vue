<template>
    <div id="postImagePage">
      <b-form-radio-group
            v-model="selected"
            :options="options"
            class="mb-3"
            value-field="item"
            text-field="name"
            disabled-field="notEnabled"
            @change="showStoryOptions()"
          ></b-form-radio-group>
      <br><br>
        <form ref="uploadForm" @submit.prevent="submit">
          <input type="file" ref="uploadImage" @change="onImageUpload()" class="form-control" required multiple>
          <input type="button" @click="startupload" name="Upload" value="Upload" />
          <br><br>
          <b-form-input
                id="description"
                v-model="form.description"
                placeholder="Write description"
          ></b-form-input>
          <br>
          <b-form-tags
                v-model="form.tags"
                tag-variant="primary"
                tag-pills
                size="lg"
                separator=" "
                placeholder="Enter new tags separated by space"
            ></b-form-tags>
          <br>
          <select multiple="false" v-model="form.location">
            <option v-for="(loc,i) in locations" :key="i">{{loc.name}}</option>
          </select>
          <br>
          <select multiple="false" v-model="form.userTagsObj">
            <option v-for="(tag,i) in userTags" :key="'T' + i">{{tag}}</option>
          </select>
          <br>
          <b-form-checkbox
            v-if="form.forCloseFriends"
            id="checkbox-1"
            v-model="form.forCloseFriends"
            name="checkbox-1"
            value="true"
            unchecked-value="false"
          >
            For close friends
          </b-form-checkbox>
          <br>
          <br>
          <b-button variant="danger" style="width:200px;margin-right:20px" @click='back'>Back</b-button>
          <b-button type="submit" variant="primary" style="width:200px;" aria-describedby="signup-block">Post</b-button>
        </form>
        
    </div>
</template>

<script>
import { getUsernameFromToken } from '../util/token';
export default {
  name: 'CreatePost',
  data() {
      return {
        form: {
          username: '',
          fileNames: [],
          description:'',
          tags:[],
          locationName:'',
          forCloseFriends:false,
          userTags: [],
          location:[{
            name:''
          }],
          userTagsObj:[]
        },
        selected: 'post',
        options: [
          { item: 'post', name: 'Post' },
          { item: 'story', name: 'Story' },
        ],
        imageHasBeenUploaded:false,
        locations:[],
        file1: null,
        file2: null,
        description:'',
        value: [],formData
        :null,
        filesForm: [{}],
        userTags:[]
      }
    },
  mounted: function(){
    if(!localStorage.getItem('JWT')){
       window.location.href = "/login";
    }
    this.axios.get('/media-api/location')
      .then(response => {this.locations = response.data;})
      .catch(error => { console.error(error)
                        this.makeToast("Error occurred.", "danger");
      })
    var username = getUsernameFromToken();
    this.axios.get('/media-api/usertag/' + username)
      .then(response => { this.userTags = response.data;})
      .catch(error => { console.error(error)
                        this.makeToast("Error occurred.", "danger");
      })
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
        back:function(){
            window.location.href = "/profile/";
        },
        onSubmit() {
            console.log(this.form);
            this.axios.post('/media-api/image', this.form)
            .then(response => { console.log(response);
                                })
            .catch(error => { console.log(error);
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
        this.axios.post('/media-api/image', this.formData, {
          headers:{
            Accept: 'application/json',
            'Content-Type':'multipart/form-data'
          }
        }).then(response => {
          this.form.fileNames = response.data;
          this.makeToast("Image has been uploaded.", "success");
          this.imageHasBeenUploaded = true;
        })
      },
      submit(){
        if(this.imageHasBeenUploaded){
          this.form.username = getUsernameFromToken();
          this.form.locationName = this.form.location.toString();
          for(var i of this.form.userTagsObj){
            this.form.userTags.push(i.toString());
          }
          if(this.selected == 'post'){
            this.axios.post('/media-api/image/info', this.form)
                                .then(response => { console.log(response);
                                                    this.makeToast("Posted!", "success"); 
                                                    window.location.href = "/postimage";                             
                              }).catch(error => { console.log(error);
                                                  this.makeToast("Error occurred.", "danger");
                                                }); 
          }else{
            this.axios.post('/media-api/story/info', this.form)
                                .then(response => { console.log(response);
                                                    this.makeToast("Posted!", "success"); 
                                                    window.location.href = "/postimage";                             
                              }).catch(error => { console.log(error);
                                                  this.makeToast("Error occurred.", "danger");
                                                }); 
          }
        }else{
          this.makeToast("Please upload image.", "danger");
        }
      },
      async showStoryOptions(){
        console.log(this.selected);
        if(this.selected == 'story'){
          this.form.forCloseFriends = true;
        }else{
          this.form.forCloseFriends = false;
        }
      }
    },
}
</script>


<style scoped>
#postImagePage {
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
