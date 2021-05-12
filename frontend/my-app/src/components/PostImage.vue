<template>
    <div id="postImagePage">
      <h1>Post image</h1>
      <br><br>
        <!--<form ref="uploadForm" @submit.prevent="onSubmit">
            <b-form-file
                ref="uploadImage" @change="onImageUpload()" class="form-control"
                accept="image/jpeg, image/png"
                placeholder="Choose a file or drop it here..."
                drop-placeholder="Drop file here..."
            ></b-form-file>
            <b-button type="submit"  @click="startupload" name="Upload" value="Upload"  variant="primary" style="width:200px;">Post</b-button>
        </form>
            <br>
            <b-form-tags
                v-model="value"
                tag-variant="primary"
                tag-pills
                size="lg"
                separator=" "
                placeholder="Enter new tags separated by space"
            ></b-form-tags>
            <p class="mt-2">Value: {{ value }}</p>
            <br>
            <br>
            <b-form-input
                id="description"
                v-model="description"
                placeholder="Write description"
            ></b-form-input>
            <br><br>
            <b-button variant="danger" style="width:200px;margin-right:20px" @click='back'>Back</b-button>
      -->
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
                v-model="value"
                tag-variant="primary"
                tag-pills
                size="lg"
                separator=" "
                placeholder="Enter new tags separated by space"
            ></b-form-tags>
            <p class="mt-2">Value: {{ value }}</p>
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
  name: 'PostImage',
  data() {
      return {
        form: {
          username: '',
          fileName: '',
          description:'',
        },
        file1: null,
        file2: null,
        description:'',
        value: ['love', 'life', 'smile'],
        formData:null,
      }
    },
  mounted: function(){
    if(!localStorage.getItem('JWT'))
      window.location.href = "/";
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
            this.axios.post('/image', this.form)
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
        this.axios.post('/image', this.formData, {
          headers:{
            Accept: 'application/json',
            'Content-Type':'multipart/form-data'
          }
        }).then(response => {
          this.form.fileName = response.data;
        })
      },
      submit(){
        this.form.username = getEmailFromToken();
        this.axios.post('/image/info', this.form)
                  .then(response => { console.log(response);
                                      this.makeToast("Image has been uploaded.", "success"); 
                                      window.location.href = "/postimage";  
                                                      
                }).catch(error => { console.log(error);
                                    this.makeToast("Error occurred..", "danger");
                                  }); 
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
