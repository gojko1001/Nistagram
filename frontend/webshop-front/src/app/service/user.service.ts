import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LOGIN_PATH, REGISTRATION_PATH } from '../util/paths';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  register(registerDto : any){
    return this.httpClient.post('http://localhost:4000/user/register', registerDto);
  }

  login(loginDto : any){
    return this.httpClient.post(LOGIN_PATH, loginDto);
  }

}
