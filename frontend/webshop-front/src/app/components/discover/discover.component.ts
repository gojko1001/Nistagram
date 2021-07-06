import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ItemService } from 'src/app/service/item.service';
import { ShoppingService } from 'src/app/service/shopping.service';
import { getUsernameFromToken } from 'src/app/util/tokenUtil';

@Component({
  selector: 'app-discover',
  templateUrl: './discover.component.html',
  styleUrls: ['./discover.component.css']
})
export class DiscoverComponent implements OnInit {

  items: any[]=[];
  message: any;
  itemInCart: any = {item: [], quantity:''};

  constructor(private itemService: ItemService, private toastrService: ToastrService,
    private router: Router, private shoppingService: ShoppingService) { }

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
      this.toastrService.error("Error occurred.");
    });
  }

  createItem(){
    this.router.navigate(['/create-item']);
  }

  deleteItem(itemId: any): void{
    let currentUrl = this.router.url;
    this.itemService.deleteItem(getUsernameFromToken(), itemId).subscribe(data => {
      this.message = data;
      this.toastrService.success(this.message.error.text);
      this.redirectTo(currentUrl);
    }, error => {
      if(error.status == 200){
        this.toastrService.success(error.error.text);
        this.redirectTo(currentUrl);
      }else{
        this.toastrService.error(error.error.text);
      }
    });
  }

  getUsername(){
    return getUsernameFromToken();
  }

  redirectTo(uri:string){
    this.router.navigateByUrl('/registration', {skipLocationChange: true}).then(()=>
    this.router.navigate([uri]));
  }

  addItemInCart(item: any){
    console.log(item);
    this.itemInCart.item = item;
    this.shoppingService.addItemInShoppingCart(getUsernameFromToken(), this.itemInCart).subscribe(data => {
      this.toastrService.success("Item has been added to shopping cart.");
      console.log(data);
    }, error => {
        this.toastrService.error("Error occurred.");
    });
  }

}
