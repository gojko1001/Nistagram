<template>
    <div id="form">
        <h1 class="brandName">New Campaign</h1>
        <br>
        <!-- Basic campaign info -->
        <span style="font-size:20px; font-weight:bold">Basic info</span> <hr>
        <table class="w-100 py-2">
            <tr>
            <td>Campaign type:</td>
              <td><b-form-radio-group
                v-model="campaign.campaignType"
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
            <tr><td>Start date:</td>
            <td><b-form-datepicker id="startDate" v-model="startDate" :min="minDate" class="mb-2"></b-form-datepicker></td></tr>
            <tr v-if="campaign.termType == 'SHORT_TERM'"><td>Choose a time:</td>
            <td>        
                <b-form-timepicker v-model="time" locale="rs" placeholder="Choose a time"></b-form-timepicker>
            </td></tr>
            <tr v-if="campaign.termType == 'LONG_TERM'"><td>Duration:</td>
            <td><b-form-input v-model="duration" type="number" id="duration" min="1">{{duration}}</b-form-input></td></tr>
            <tr v-if="campaign.termType == 'LONG_TERM'"><td>Display per day:</td>
            <td><b-form-input v-model="campaign.perDay" type="number" id="perDay" min="1" max="24">{{campaign.perDay}}</b-form-input></td></tr>
          </table>
          <br>

          <!-- Ads -->
          <div id="ads">
            <span style="font-size:20px; font-weight:bold">Advertisments</span>
            <b-button variant="outline-success" style="float:right" v-b-modal.modal-ads><i class="fa fa-plus"></i></b-button>
            <hr>
            <div v-for="(ad,i) in campaign.ads" :key="i" class="left">
                <b-btn size="sm" variant="outline-danger" class="float-right" @click="removeAd(i)"><i class="fa fa-trash"></i></b-btn>
                <span><i>File:</i> {{ad.fileName}}</span>
                <span v-if="ad.description != ''"><br><i>Description:</i> {{ad.description}} </span>
                <span v-if="ad.url != ''"><br><i>Link:</i> {{ad.url}}</span>
                <hr>
            </div>
          </div>
          <br>

          <!-- Targeted audience -->
          <div id="audiences">
                <span style="font-size:20px; font-weight:bold">Target Audience</span>
                <b-button variant="outline-success" style="float:right" v-b-modal.modal-audience><i class="fa fa-plus"></i></b-button>
                <hr>
                <div v-for="(audience,i) in campaign.audiences" :key="i" class="left">
                    <b-btn size="sm" variant="outline-danger" class="float-right" @click="removeAudience(i)"><i class="fa fa-trash"></i></b-btn>
                    <span>{{audience.fromAge}} - {{audience.toAge}} yo</span>
                    <span v-for="(gender,k) in audience.genders" :key="k"> | {{gender.toLowerCase()}} </span> <br>
                    <span v-for="(tag, l) in audience.hashtags" :key="l"> #{{tag}} </span>
                    <hr>
                </div>
          </div>
          
          <br>
          <b-button type="submit" variant="primary" style="width:200px;" @click="createCampaign()">Save changes</b-button><br>
          
          <!-- Ad modal -->
          <b-modal id="modal-ads" title="Advertisment" @ok="addAd">
                <div>
                    <table class="w-100">
                        <tr><td>Choose images:</td><td>
                        <!-- <input type="file" ref="image" class="form-control" required> -->
                        <b-form-file
                            v-model="file"
                            :state="Boolean(file)"
                            placeholder="Choose file or drop it here..."
                            drop-placeholder="Drop file here..."
                        ></b-form-file>
                        </td></tr>
                        <tr><td>Description:</td>
                        <td><b-form-textarea v-model="ad.description" type="text" id="description" placeholder="Description" rows="3"></b-form-textarea></td></tr>
                        <tr><td>Ad URL:</td>
                        <td><b-form-input v-model="ad.url" type="url" id="link" placeholder="Link"></b-form-input></td></tr>
                    </table>
                    <br>
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

          <!-- Targeted audience modal -->
          <b-modal id="modal-audience" title="Audience" @ok="addAudience">
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
import { CAMPAIGN_PATH, IMAGE_PATH, ROLE_AGENT, SERVER_NOT_RESPONDING } from '../util/constants'
import { getRoleFromToken, getUsernameFromToken } from '../util/token'
export default {
    data(){
        return{
            campaign:{
                username: getUsernameFromToken(),
                campaignType: 'POST',
                termType:'SHORT_TERM',
                startDate: '',
                endDate: null,
                perDay: 1,
                audiences: [],
                ads: []
            },
            files: [],
            file: null,
            imagesToUpload: null,
            ad: {
                url: '',
                description: '',
                fileName: ''
            },
            targetedAudience: {
                fromAge: 15,
                toAge: 70,
                hashtags: [],
                genders: []
            },

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
            if(this.campaign.termType == "LONG_TERM"){
                this.campaign.startDate = moment(String(this.startDate)).format('YYYY-MM-DD HH:mm');
                this.campaign.endDate = new Date(this.startDate);
                this.campaign.endDate.setDate(this.campaign.endDate.getDate() + Number(this.duration));
                this.campaign.endDate = moment(String(this.campaign.endDate)).format('YYYY-MM-DD HH:mm');
            }else if(this.campaign.termType == "SHORT_TERM"){
                this.campaign.startDate = moment(String(this.startDate)).format('YYYY-MM-DD');
                this.campaign.startDate = this.campaign.startDate + " " + this.time;
            }
            this.onImageUpload();

            this.axios.post(IMAGE_PATH, this.imagesToUpload, {
                headers:{
                    Accept: 'application/json',
                    'Content-Type':'multipart/form-data'
                }
            }).then(response => {
                console.log(response.data);
                this.makeToast("Medias have been uploaded.", "success");
                this.axios.post(CAMPAIGN_PATH, this.campaign)
                    .then(() => {
                        this.makeToast("Campaign made successfully", "success");
                        window.location.href = "/user/" + this.campaign.username;
                        // TODO: Redirect to all campaign page when added
                    }).catch(err => {
                        if(!err.response)
                              this.makeToast(SERVER_NOT_RESPONDING, "danger");
                        else
                            this.makeToast("Something went wrong while creating campaign", "danger");
                    })
            })
        },
        addAudience(){
            if(this.targetedAudience.fromAge > this.targetedAudience.toAge){ // TODO: Prevent modal close, prettier validation
                this.makeToast("Invalid age range!", "danger");
                return;
            }
            this.campaign.audiences.push(this.targetedAudience);
            this.targetedAudience = {
                fromAge: 15,
                toAge: 70,
                hashtags: [],
                genders: []
            }
        },

        removeAudience(i){
            this.campaign.audiences.splice(i, 1);
        },

        addAd(){
            if(this.file == null){      // TODO: Prevent modal close, prettier validation
                this.makeToast("Media must be specified!", "danger")
                return;
            }
            // this.files.push(this.$refs.image.files[0]);
            // this.ad.fileName = this.$refs.image.files[0].name;
            this.files.push(this.file);
            this.ad.fileName = this.file.name;
            this.file = null;
            this.campaign.ads.push(this.ad);
            this.ad = {
                url: '',
                description: '',
                fileNames: []
            }
        },

        removeAd(i){
            this.campaign.ads.splice(i, 1);
            this.files.splice(i, 1);
        },

        onImageUpload(){
            this.imagesToUpload = new FormData();
            for(let file of this.files){
                this.imagesToUpload.append("file", file);
            }
        },
        
    }
}
</script>
<style scoped>
td{
  padding-top: 5px;
  padding-bottom: 5px
}

#form {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  background: white;
  text-align: center;
  color: #2c3e50;
  margin: auto;
  margin-top: 40px;
  margin-bottom: 40px;
  width: 50%;
  border: 3px solid lightblue;
  padding: 50px;
}

.left {
    text-align: left;
}
</style>