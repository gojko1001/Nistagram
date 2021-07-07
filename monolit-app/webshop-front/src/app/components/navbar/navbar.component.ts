import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { getRoleFromToken, getUsernameFromToken, removeToken } from 'src/app/util/tokenUtil';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  getUsername(){
    return getUsernameFromToken();
  }

  getRole(){
    return getRoleFromToken();
  }

  logout(){
    removeToken();
    this.router.navigate(['/login'], {skipLocationChange:true});
  }

  discover(){
    this.router.navigate(['/discover']);
  }

  shoppingCart(){
    this.router.navigate(['/shopping-cart']);
  }

}
