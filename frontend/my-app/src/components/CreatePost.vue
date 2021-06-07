<template>
    <div id="postImagePage">
      <b-form-radio-group
            v-model="selected"
            :options="options"
            class="mb-3"
            value-field="item"
            text-field="name"
            disabled-field="notEnabled"
          ></b-form-radio-group>
      <br><br>
        <form ref="uploadForm" @submit.prevent="submit">
          <input type="file" ref="uploadImage" @change="onImageUpload()" class="form-control" required>
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
          <br>
          <b-button variant="danger" style="width:200px;margin-right:20px" @click='back'>Back</b-button>
          <b-button type="submit" variant="primary" style="width:200px;" aria-describedby="signup-block">Post</b-button>
        </form>
        
    </div>
</template>

<script>
import { getEmailFromToken } from '../util/token';
export default {
  name: 'CreatePost',
  data() {
      return {
        form: {
          username: '',
          fileName: '',
          description:'',
          tags:[],
          locationName:'',
          location:[{
            name:''
          }],
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
        value: [],
        formData:null,
      }
    },
  mounted: function(){
    if(!localStorage.getItem('JWT'))
      window.location.href = "/";
    this.axios.get('/location')
    .then(response => {console.log(response);
                        this.locations = response.data;
                      })
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
        let file = this.$refs.uploadImage.files[0];
        this.formData = new FormData();
        this.formData.append("file", file);
      },
      startupload(){
        this.axios.post('/media-api/image', this.formData, {
          headers:{
            Accept: 'application/json',
            'Content-Type':'multipart/form-data'
          }
        }).then(response => {
          this.form.fileName = response.data;
          this.makeToast("Image has been uploaded.", "success");
          this.imageHasBeenUploaded = true;
        })
      },
      submit(){
        if(this.imageHasBeenUploaded){
          this.form.username = getEmailFromToken();
          this.form.locationName = this.form.location.toString();
          console.log(this.selected)
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
