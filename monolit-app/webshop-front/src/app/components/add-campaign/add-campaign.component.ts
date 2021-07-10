import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { CampaignService } from 'src/app/service/campaign.service';
import { getRoleFromToken, getUsernameFromToken } from 'src/app/util/tokenUtil';

@Component({
  selector: 'app-add-campaign',
  templateUrl: './add-campaign.component.html',
  styleUrls: ['./add-campaign.component.css'],
})
export class AddCampaignComponent implements OnInit {

  campaign: any = {
    username: getUsernameFromToken(),
    campaignType: 'POST',
    termType:'SHORT_TERM',
    startDate: '',
    endDate: '',
    perDay: 1,
    audiences: new Array(),
    ads: new Array()
  };
  files = new Array();
  file: any = null;
  imagesToUpload: any = null;
  ad = {
      url: '',
      description: '',
      fileName: ''
  };
  targetedAudience = {
      fromAge: 15,
      toAge: 70,
      hashtags: new Array(),
      genders: new Array()
  };

  isLongTerm: boolean = false;
  time: any = {hours: '', minutes: ''};
  duration: number = 1;
  hashtags: string = '';
  gender = {
       male: false,
       female: false,
       other: false   
  };

  constructor(private toastrService: ToastrService,
              private datePipe: DatePipe,
              private modalService: NgbModal,
              private router: Router,
              private campaignService: CampaignService) { }

  ngOnInit(): void {
    if(getRoleFromToken() != 'ROLE_AGENT')
      this.router.navigate(["/discover"]);
  }

  createCampaign(){
    if(!this.campaign.startDate){
      this.toastrService.warning("Please select start date")
      return
    }
    if(this.isLongTerm)
      this.campaign.termType = "LONG_TERM"
    else
      this.campaign.termType = "SHORT_TERM"

    if(this.campaign.termType == "LONG_TERM"){
        if(this.duration < 1 || !this.duration){
          this.toastrService.warning("Invalid duration field format")
          return;
        }
        if(this.campaign.perDay < 1 || this.campaign.perday > 24 || !this.campaign.perDay){
          this.toastrService.warning("Invalid per day field format (min=0, max=24)")
        }
        let date = new Date(this.campaign.startDate);
        date.setDate(date.getDate() + Number(this.duration));
        this.campaign.endDate = this.datePipe.transform(date, 'yyyy-MM-dd')?.toString();
    }else if(this.campaign.termType == "SHORT_TERM"){
      if(this.time.hours < 0 || this.time.hours > 23 || !this.time.hours){
        this.toastrService.error("Invalid hours field format")
        return;
      }
      if(this.time.minutes < 0 || this.time.minutes > 59 || !this.time.minutes){
        this.toastrService.error("Invalid minutes field format")
        return;
      }
        this.campaign.startDate = this.campaign.startDate + " " + this.formatTime();
    }

    this.imagesToUpload = new FormData();
    for(let file of this.files)
        this.imagesToUpload.append("file", file);

    // TODO: Add images to server
    this.campaignService.uploadImages(this.imagesToUpload).subscribe(data => {
      this.toastrService.success("Files added successfully")    
    }, err => {
      this.toastrService.error("Something went wrong while creating campaign");
    })

    this.campaignService.createCampaign(this.campaign).subscribe(() => {
      this.toastrService.success("Campaign made successfully");
    }, err => {
      this.toastrService.error("Something went wrong while creating campaign");
    })
    
  }

  addAd(){
    if(this.file == null){
        this.toastrService.warning("Media must be specified!")
        return;
    }
    this.files.push(this.file);
    this.ad.fileName = this.file.name;
    this.file = null;
    this.campaign.ads.push(this.ad);
    this.ad = {
        url: '',
        description: '',
        fileName: ''
    }

    this.modalService.activeInstances.closed = true;
  }

  onImageUpload(event:any){
    this.file = event.target.files[0];
  }


  removeAd(i: number){
    this.campaign.ads.splice(i, 1);
    this.files.splice(i, 1);
  }

  addAudience(){
    if(this.targetedAudience.fromAge > this.targetedAudience.toAge){
        this.toastrService.warning("Invalid age range!");
        return;
    }
    if(this.gender.male)
      this.targetedAudience.genders.push("MALE");
    if(this.gender.female)
      this.targetedAudience.genders.push("FEMALE");
    if(this.gender.other)
      this.targetedAudience.genders.push("OTHER");
    for(let tag of this.hashtags.split(' '))
      this.targetedAudience.hashtags.push(tag);
    this.campaign.audiences.push(this.targetedAudience);
    this.targetedAudience = {
        fromAge: 15,
        toAge: 70,
        hashtags: new Array(),
        genders: new Array()
    }
    this.gender = {male: false, female: false, other: false}
    this.hashtags = '';
}

  removeAudience(i: number){
    this.campaign.audience.splice(i, 1);
  }


  open(selectedModal: any) {
    this.modalService.open(selectedModal).result
  }

  formatTime(){
    let time = '';
    if(this.time.hours < 10){
      time += '0'
    }
    time += this.time.hours;
    time += ':';
    if(this.time.minutes < 10)
      time += '0'
    time += this.time.minutes;
    return time;
  }

}
