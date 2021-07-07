import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { INFO_PATH, LOGIN_PATH, REGISTRATION_PATH } from '../util/paths';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  register(registerDto : any){
    return this.httpClient.post(REGISTRATION_PATH, registerDto);
  }

  login(loginDto : any){
    return this.httpClient.post(LOGIN_PATH, loginDto);
  }

  findByUsername(username: any){
    return this.httpClient.get(INFO_PATH + '/' + username);
  }

}
