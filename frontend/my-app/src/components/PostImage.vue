<template>
    <div id="postImagePage">
      <h1>Post image</h1>
      <br><br>
        <b-form-file
            v-model="file1"
            accept="image/jpeg, image/png"
            :state="Boolean(file1)"
            placeholder="Choose a file or drop it here..."
            drop-placeholder="Drop file here..."
        ></b-form-file>
        <div class="mt-3">Selected file: {{ file1 ? file1.name : '' }}</div>
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
        <!-- LOCATION fali -->
        <br>
        <b-form-input
            id="description"
            v-model="description"
            placeholder="Write description"
            required
          ></b-form-input>
      <br><br>
      <b-button variant="danger" style="width:200px;margin-right:20px" @click='back'>Back</b-button>
      <b-button type="submit" variant="primary" style="width:200px;">Post</b-button>
    </div>
</template>

<script>
export default {
  name: 'PostImage',
  data() {
      return {
        file1: null,
        file2: null,
        description:'',
        value: ['love', 'life', 'smile'],
      }
    },
  mounted: function(){
    /*if(!localStorage.getItem('JWT'))
      window.location.href = "/";*/
    },
    methods:{
        back:function(){
            //TODO
            window.location.href = "/profile/";
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
</style>
