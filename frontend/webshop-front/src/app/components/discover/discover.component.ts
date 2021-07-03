import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ItemService } from 'src/app/service/item.service';
import { getUsernameFromToken } from 'src/app/util/tokenUtil';

@Component({
  selector: 'app-discover',
  templateUrl: './discover.component.html',
  styleUrls: ['./discover.component.css']
})
export class DiscoverComponent implements OnInit {

  items: any[]=[];
  message: any;

  constructor(private itemService: ItemService,
    private toastrService: ToastrService,
    private router: Router) { }

  ngOnInit(): void {
    this.itemService.getItems().subscribe((data: any) => {
      this.items = data;
      
      for(let i=0; i<this.items.length; i++){
        for(let j=0; j<this.items[i].imageBytes.length; j++){
          if(this.items[i].imageBytes[j].image){
            this.items[i].imageBytes[j].imageByte = 'data:image/jpeg;base64,' + this.items[i].imageBytes[j].imageByte; 
          }else{
            this.items[i].imageBytes[j].imageByte = 'data:video/mp4;base64,' + this.items[i].imageBytes[j].imageByte;
          }
        }
      }
      console.log(this.items);
    }, error => {
      this.toastrService.error("Something went wrong. Please check your input!");
    });
  }

  addNewItem(){
    //todo
  }

  deleteItem(itemId: any){
    this.itemService.deleteItem(getUsernameFromToken(), itemId).subscribe(data => {
      this.message = data;
      this.toastrService.success(this.message.error.text);
    }, error => {
      if(error.status == 200){
        this.toastrService.success(error.error.text);
      }else{
        this.toastrService.error(error.error.text);
      }
    });
  }

  getUsername(){
    return getUsernameFromToken();
  }

}
