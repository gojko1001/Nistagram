<template>
  <div id="verifyAccountPage">
    <h1 class="brandName">Nistagram</h1>
    <p style="font-size:15px">Verification request</p>
      <br>
      <b-form @submit.prevent="onSubmit" @reset="onReset">
        <b-form-group>
          <b-form-input
            id="fullName"
            v-model="form.fullName"
            placeholder="Enter full name"
            required
          ></b-form-input>
          <br>
          <b-form-select v-model="form.category" id="categories">
            <option v-for="c in categories" :key="c.id" :value="c">{{ c.name }}</option>
          </b-form-select>
          <br>
          <span>ID card: </span>
          <input type="file" ref="uploadImage" @change="onImageUpload()" class="form-control" required>
          <input type="button" @click="startupload" name="Upload" value="Upload" />
          <br><br>
        </b-form-group>
        <br>
        <b-button type="submit" variant="primary" style="width:200px;" aria-describedby="signup-block">Send request</b-button>
      </b-form>
  </div>
</template>

<script>
import { VERIFY_USER_PATH, GET_CATEGORIES_PATH, USER_PATH, SERVER_NOT_RESPONDING } from '../util/constants';
import { getUsernameFromToken } from '../util/token';
export default {
  name: 'VerificationRequest',
  data() {
      return {
        form: {
          fullName:'',
          category: '',
          fileName: '',
        },
        username: '',
        categories: [],
      }
  },
  mounted: function(){
        this.username = getUsernameFromToken();
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

        this.axios.get(GET_CATEGORIES_PATH)
                        .then(response => {
                            this.categories = response.data;
                        })
    },
  methods:{
    onSubmit() {
        console.log(this.form);
        this.axios.post(VERIFY_USER_PATH + '/' + this.username, this.form)
                  .then(response => { console.log(response);
                                      this.makeToast("Your verification request has been sent successfully.", "success");
                                      window.location.href = "/";
                                      
                })
                  .catch(error => { console.log(error);
                                    this.makeToast("Error occurred. Verification request has not been sent successfully.", "danger");
                                  });                  
      },
      onReset(event) {
        event.preventDefault()
        this.form.fullName = ''
        this.form.category = ''
      },
      onImageUpload(){
        let file = this.$refs.uploadImage.files[0];
        this.formData = new FormData();
        this.formData.append("file", file);
      },
      startupload(){
        this.axios.post('/user-api/verify/save_id', this.formData, {
          headers:{
            Accept: 'application/json',
            'Content-Type':'multipart/form-data'
          }
        }).then(response => {
          this.form.fileName = response.data[0];
          this.makeToast("Image has been uploaded.", "success");
          this.imageHasBeenUploaded = true;
        })
      },
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
  },
}
</script>


<style scoped>
#verifyAccountPage {
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
</style>
