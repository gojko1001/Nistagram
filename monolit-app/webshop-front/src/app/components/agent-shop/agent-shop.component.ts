import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ItemService } from 'src/app/service/item.service';
import { ShoppingService } from 'src/app/service/shopping.service';
import { getRoleFromToken, getUsernameFromToken } from 'src/app/util/tokenUtil';

@Component({
  selector: 'app-agent-shop',
  templateUrl: './agent-shop.component.html',
  styleUrls: ['./agent-shop.component.css']
})
export class AgentShopComponent implements OnInit {

  logedUsername = getUsernameFromToken();
  role = getRoleFromToken();
  
  username: any;
  items: any[] = [];
  itemInCart: any = {item: [], quantity:''};

  constructor(private itemService: ItemService,
              private shoppingService: ShoppingService,
              private _activatedRoute: ActivatedRoute,
              private toastrService: ToastrService,
              private router: Router) { }

  ngOnInit(): void {
    this._activatedRoute.paramMap.subscribe(params => { 
      this.username = params.get('username');
      this.itemService.getItemsByUsername(this.username).subscribe((data: any) => {
        this.items = data;
        for(let item of this.items){
          for(let imageData of item.imageBytes){
            if(imageData.image){
              imageData.imageByte = 'data:image/jpeg;base64,' + imageData.imageByte; 
            }else{
              imageData.imageByte = 'data:video/mp4;base64,' + imageData.imageByte;
            }
          }
        }
      }, err => {
        this.toastrService.error("Something went wrong wihle loading items from Db");
      })
    });
  }

  deleteItem(id: number){
    let currentUrl = this.router.url;
    this.itemService.deleteItem(this.logedUsername, id).subscribe((data: any) => {
      this.toastrService.success(data.error.text);
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
