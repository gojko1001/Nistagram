import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ShoppingService } from 'src/app/service/shopping.service';
import { getUsernameFromToken } from 'src/app/util/tokenUtil';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  shoppingCart:any;

  constructor(private router: Router, private toastrService: ToastrService, private shoppgingService: ShoppingService) { }

  ngOnInit(): void {
    this.shoppgingService.getShoppingCart(this.getUsername()).subscribe( data => {
      this.shoppingCart = data;
      console.log(this.shoppingCart)
    }, error => {
      this.toastrService.error("Error occurred.");
    });
  }

  back(){
    this.router.navigate(['/discover']);
  }

  getUsername(){
    return getUsernameFromToken();
  }

  deleteItem(itemInCartId: any){
    let currentUrl = this.router.url;
    this.shoppgingService.deleteItemFromShoppingCart(this.getUsername(), itemInCartId).subscribe( data => {
      this.toastrService.success("Item has been deleted from shopping cart.");
      this.redirectTo(currentUrl);
    }, error => {
      this.toastrService.error("Error occurred.");
    });
  }

  redirectTo(uri:string){
    this.router.navigateByUrl('/registration', {skipLocationChange: true}).then(()=>
    this.router.navigate([uri]));
  }

  checkout(){
    if(this.shoppingCart == null){
      this.toastrService.info("Shopping cart is empty.");
    }else{
      this.router.navigate(['shipping-info']);
    }
      
  }

}
