import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ITEM_PATH } from '../util/paths';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(private httpClient: HttpClient) { }

  getItems(){
    return this.httpClient.get(ITEM_PATH);
  }

  deleteItem(username: any, itemId: any){
    return this.httpClient.delete(ITEM_PATH + '/' + username + '/' + itemId);
  }
}
