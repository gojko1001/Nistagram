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
            <tr><td>Start date:</td>
            <td><b-form-datepicker id="startDate" v-model="campaign.startDate" :min="minDate" class="mb-2"></b-form-datepicker></td></tr>
            <tr v-if="campaign.termType == 'SHORT_TERM'"><td>Choose a time:</td>
            <td>        
                <b-form-timepicker v-model="value" locale="rs" placeholder="Choose a time"></b-form-timepicker>
            </td></tr>
            <tr v-if="campaign.termType == 'LONG_TERM'"><td>Days:</td>
            <td><b-form-input v-model="days" type="number" id="days">{{days}}</b-form-input></td></tr>
            <tr v-if="campaign.termType == 'LONG_TERM'"><td>Per day:</td>
            <td><b-form-input v-model="campaign.perDay" type="number" id="perDay">{{campaign.perDay}}</b-form-input></td></tr>
          </table>
          <br>
          <b-button type="submit" variant="primary" style="width:200px;" @click="createCampaign()">Save changes</b-button><br>
    </div>
</template>

<script>
import { ROLE_AGENT } from '../util/constants'
import { getRoleFromToken, getUsernameFromToken } from '../util/token'
export default {
    data(){
        return{
            campaign:{
                username: getUsernameFromToken(),
                type: 'POST',
                termType:'SHORT_TERM',
                startDate: new Date(),
                endDate: null,
                perDay: 1
            },
            days: 1,
            campType:[
                { text: 'Post', value: 'POST' },
                { text: 'Story', value: 'STORY' }
            ],
            minDate: new Date()
        }
    },
    beforeMount(){
        if(getRoleFromToken() != ROLE_AGENT)
            window.location.href = "/"
        let date = new Date()
        this.minDate.setDate(date.getDate() + 1);
        this.campaign.startDate.setDate(date.getDate() + 1);
    },
    methods: {
        createCampaign(){
            if(this.campaign.termType == "LONG_TERM"){
                this.campaign.endDate = new Date();
                this.campaign.endDate.setDate(this.campaign.startDate.getDate() + this.days);
            }
            
            // TODO: Senda data to backend
        }
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