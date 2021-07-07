import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ShoppingService } from 'src/app/service/shopping.service';
import { UserService } from 'src/app/service/user.service';
import { getUsernameFromToken } from 'src/app/util/tokenUtil';

@Component({
  selector: 'app-shipping-info',
  templateUrl: './shipping-info.component.html',
  styleUrls: ['./shipping-info.component.css']
})
export class ShippingInfoComponent implements OnInit {

  info: any;

  constructor(private router: Router, private toastrService: ToastrService, private userService: UserService,
    private shoppingService: ShoppingService) { }

  ngOnInit(): void {
    this.userService.findByUsername(this.getUsername()).subscribe( data => {
      this.info = data;
      console.log(this.info)
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

  order(){
    this.shoppingService.createOrder(this.info).subscribe( data => {
      this.toastrService.success("Successfully ordered.");
      console.log(data)
      this.router.navigate(['/discover']);
    }, error => {
      this.toastrService.error("Error occurred.");
    });
  }

}
