import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UserService } from 'src/app/service/user.service';
import { saveToken } from 'src/app/util/tokenUtil';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  loginDto: any = {username: '', password: ''}

  constructor(private userService: UserService,
    private toastrService: ToastrService,
    private router: Router) { }

  ngOnInit(): void {
  }
  
  login(){
    this.userService.login(this.loginDto).subscribe((data : any) => {
      saveToken(data.toString());
      this.toastrService.success("Successfully loged in!");
      this.router.navigate(['/discover']);
    }, ( error : any) => {
      if(error.status == 200){      // TODO: Unexpected token e in JSON at position 0 - error message
        saveToken(error.error.text.toString());
        this.toastrService.success("Successfully loged in!");
        this.router.navigate(['/discover']);
      }else{ 
        this.toastrService.warning("Username or password is incorrect!");
      }
    })
  }

}
