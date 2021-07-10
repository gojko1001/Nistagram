import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CAMPAIGN_PATH, UPLOAD_IMAGE_PATH } from '../util/paths';
import { getToken } from '../util/tokenUtil';

@Injectable({
  providedIn: 'root'
})
export class CampaignService {

  constructor(private httpClient: HttpClient) { }

  createAuthorizationHeader() {
    return 'Bearer ' + getToken(); 
  }

  createCampaign(campaign: any){
    return this.httpClient.post(CAMPAIGN_PATH, campaign, {headers: {Authorization: this.createAuthorizationHeader()}})
  }

  uploadImages(files: any){
    return this.httpClient.post(UPLOAD_IMAGE_PATH, files, {headers: {Authorization: this.createAuthorizationHeader()}})
  }
}
