<template>
    <div id="form">
        <h2>New Campaign</h2>
        <table class="w-100 py-2">
            <tr>
            <td>Campaign type:</td>
              <td><b-form-radio-group
                v-model="campaign.type"
                :options="campType"
                class="mb-3"
                value-field="value"
                text-field="text"
                disabled-field="notEnabled"
              ></b-form-radio-group></td>
            </tr>
            <tr><td></td>
            <td><b-form-checkbox
              id="termType"
              v-model="campaign.termType"
              name="termType"
              value="LONG_TERM"
              unchecked-value="SHORT_TERM">
              Long term
            </b-form-checkbox>
            </td></tr>
            <tr><td>Choose images:</td><td>
                <!-- <input type="file" ref="uploadImage" @change="onImageUpload()" class="form-control" required multiple> -->
            <b-form-file
                v-model="images"
                multiple
                :state="Boolean(images.length != 0)"
                placeholder="Choose file(s) or drop 'em here..."
                drop-placeholder="Drop file here..."
                @change="onImageUpload()"
            ></b-form-file>
            </td></tr>
            <tr><td>Start date:</td>
            <td><b-form-datepicker id="startDate" v-model="startDate" :min="minDate" class="mb-2"></b-form-datepicker></td></tr>
            <tr v-if="campaign.termType == 'SHORT_TERM'"><td>Choose a time:</td>
            <td>        
                <b-form-timepicker v-model="time" locale="rs" placeholder="Choose a time"></b-form-timepicker>
            </td></tr>
            <tr v-if="campaign.termType == 'LONG_TERM'"><td>Duration:</td>
            <td><b-form-input v-model="durattion" type="number" id="duration">{{duration}}</b-form-input></td></tr>
            <tr v-if="campaign.termType == 'LONG_TERM'"><td>Display per day:</td>
            <td><b-form-input v-model="campaign.perDay" type="number" id="perDay">{{campaign.perDay}}</b-form-input></td></tr>
          </table>
          <br>
          <span style="font-size:20px; font-weight:bold">Target Audience</span><b-button variant="outline-primary" style="float:right" v-b-modal.modal-audience>+</b-button>
          <hr>
          <div v-for="(audience,i) in allAudieces" :key="i">
              <span>{{audience.fromAge}} - {{audience.toAge}} yo</span>
              <span v-for="(gender,k) in audience.genders" :key="k"> | {{gender}} </span>
              <b-btn size="sm" variant="outline-danger" class="float-right" @click="removeAudience(i)">Remove</b-btn>
              <hr>
          </div>
          <br>
          <b-button type="submit" variant="primary" style="width:200px;" @click="createCampaign()">Save changes</b-button><br>

          <b-modal id="modal-audience" title="Audience" @ok="add">
                <div>
                    <b>Age</b>
                    <!-- <b-form-input id="ageRange" v-model="age" type="range" min="15" max="75"></b-form-input> -->
                    <table class="w-100">
                        <tr><td>From:</td>
                        <td><b-form-input v-model="targetedAudience.fromAge" type="number" id="fromAge" min="15" max="70" placeholder="From"></b-form-input></td></tr>
                        <tr><td>To:</td>
                        <td><b-form-input v-model="targetedAudience.toAge" type="number" id="toAge" min="15" max="70" placeholder="To"></b-form-input></td></tr>
                    </table>
                    <br>
                    <b>Gender</b>
                    <b-form-checkbox-group
                        id="genderM"
                        v-model="targetedAudience.genders"
                        name="genderM"
                        :options="genderOpts">
                    </b-form-checkbox-group>
                    <br>
                    <b>HashTags</b>
                    <b-form-tags
                        v-model="targetedAudience.hashtags"
                        tag-variant="primary"
                        tag-pills
                        separator=" "
                        placeholder="Enter new tags separated by space"
                    ></b-form-tags>
                </div>
                <template #modal-footer="{ ok, cancel }">
                    <b-button variant="success" @click="ok()">
                        Add
                    </b-button>
                    <b-button variant="secondary" @click="cancel()">
                        Cancel
                    </b-button>
                </template>
            </b-modal>
        </div>
</template>

<script>
import moment from 'moment'
import { IMAGE_PATH, ROLE_AGENT } from '../util/constants'
import { getRoleFromToken, getUsernameFromToken } from '../util/token'
export default {
    data(){
        return{
            campaign:{
                username: getUsernameFromToken(),
                type: 'POST',
                termType:'SHORT_TERM',
                startDate: '',
                endDate: null,
                perDay: 1
            },
            images: [],
            imagesToUpload: null,
            targetedAudience: {
                campaignId: -1,
                fromAge: 15,
                toAge: 70,
                hashtags: [],
                genders: []
            },
            allAudieces:[],

            minDate: new Date(),
            startDate: new Date(),
            time: '12:00',
            duration: 1,
            campType:[
                { text: 'Post', value: 'POST' },
                { text: 'Story', value: 'STORY' }
            ],
            genderOpts:[
                { text: 'Male', value: 'MALE'},
                { text: 'Female', value: 'FEMALE'},
                { text: 'Other', value: 'OTHER'}
            ],
            
        }
    },
    beforeMount(){
        if(getRoleFromToken() != ROLE_AGENT)
            window.location.href = "/"
        let date = new Date()
        this.minDate.setDate(date.getDate() + 1);
        this.startDate.setDate(date.getDate() + 1);
    },
    methods: {
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
        createCampaign(){
            this.campaign.startDate = moment(String(this.startDate)).format('YYYY-MM-DD');
            if(this.campaign.termType == "LONG_TERM"){
                this.campaign.endDate = new Date(this.startDate);
                this.campaign.endDate.setDate(this.campaign.endDate.getDate() + Number(this.duration));
                this.campaign.endDate = moment(String(this.campaign.endDate)).format('YYYY-MM-DD');
            }else if(this.campaign.termType == "SHORT_TERM"){
                this.campaign.startDate = this.campaign.startDate + " " + this.time;
            }

            // TODO: Check this method
            this.axios.post(IMAGE_PATH, this.imagesToUpload, {
                headers:{
                    Accept: 'application/json',
                    'Content-Type':'multipart/form-data'
                }
            }).then(response => {
                response;
                // this.form.fileNames = response.data; TODO: Put images to Ad
                this.makeToast("Image has been uploaded.", "success");
            })

            // TODO: Send data to backend
        },
        add(){
            this.allAudieces.push(this.targetedAudience);
            this.targetedAudience = {
                campaignId: -1,
                fromAge: 15,
                toAge: 70,
                hashtags: [],
                genders: []
            }
        },

        removeAudience(i){
            this.allAudieces.splice(i, 1);
        },

        onImageUpload(){
            this.imagesToUpload = new FormData();
            for(var i=0; i<this.images.files.length; i++){
                let file = this.images.files[i];
                this.imagesToUpload.append("file", file);
            }               // TODO: Make this for already posted media
        },
        
    }
}
</script>
<style scoped>
#form {
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