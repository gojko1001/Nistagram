import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CAMPAIGN_PATH } from '../util/paths';
import { getUsernameFromToken } from '../util/tokenUtil';

@Injectable({
  providedIn: 'root'
})
export class CampaignService {

  constructor(private httpClient: HttpClient) { }

  createCampaign(campaign: any){
    return this.httpClient.post(CAMPAIGN_PATH, campaign, {headers: { "x-api-key": getUsernameFromToken()}})
  }
}
