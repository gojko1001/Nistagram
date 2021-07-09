import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UserService } from 'src/app/service/user.service';
import { getUsernameFromToken } from 'src/app/util/tokenUtil';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registrationDto : any = {username: '', email: '', password: '', passwordRepeat: '', webSite:'', role:'ROLE_USER'};
  isAgent: boolean = false;

  constructor(private userService: UserService,
              private toastrService: ToastrService,
              private router: Router) { }

  ngOnInit(): void {
    if(getUsernameFromToken() != null)
      this.router.navigate(['/discover']);
  }

  register(){
    if(this.isAgent){
      this.registrationDto.role = 'ROLE_AGENT';
    }
    this.userService.register(this.registrationDto).subscribe(data => {
      this.toastrService.success("You have been registered successfully!");
      this.router.navigate(['/login']);
    }, ( error : any) => {
      if(error.status == 200){
        this.toastrService.success("You have been registered successfully!");
        this.router.navigate(['/login']);
      }else{
        this.toastrService.error("Something went wrong. Please check your input!");
      }
      
    });
  }

}
