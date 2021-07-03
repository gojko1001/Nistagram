import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ItemService } from 'src/app/service/item.service';
import { getUsernameFromToken } from 'src/app/util/tokenUtil';


@Component({
  selector: 'app-create-item',
  templateUrl: './create-item.component.html',
  styleUrls: ['./create-item.component.css']
})
export class CreateItemComponent implements OnInit {

  item: any = {username: this.getUsername()};
  message: any;
  formData: any;
  fileName: any;


  constructor(private itemService: ItemService, private router: Router,
    private toastrService: ToastrService, private httpClient: HttpClient) {}

  ngOnInit(): void {
  }

  back(){
    this.router.navigate(['/discover']);
  }

  getUsername(){
    return getUsernameFromToken();
  }

  createItem(){
    if(this.fileName != null){
      this.item.fileName = this.fileName;
      this.itemService.createItem(this.item).subscribe(data => {
            this.toastrService.success("Item has been created.");
            this.router.navigate(['/discover']);
          }, error => {
              this.toastrService.error("Error occured");
          });
    }else{
      this.toastrService.info('Please upload image.');
    }
    
  }

  onImageUpload(event:any){
    this.formData = new FormData();
    this.formData.append("file", event.target.files[0]);
  }

  startupload(){
    this.httpClient.post('http://localhost:4000/item', this.formData).subscribe((response: any) => {
      this.fileName = response[0];
      console.log(this.fileName)
      this.toastrService.success('Image has been uploaded.');
    });
  }



}
