import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CREATE_PATH, ITEM_PATH, SHOW_PATH, UPDATE_PATH } from '../util/paths';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(private httpClient: HttpClient) { }

  getItems(){
    return this.httpClient.get(ITEM_PATH);
  }

  getItemById(id: any){
    return this.httpClient.get(SHOW_PATH + '/' + id);
  }

  deleteItem(username: any, itemId: any){
    return this.httpClient.delete(ITEM_PATH + '/' + username + '/' + itemId);
  }

  updateItem(username: any, item: any){
    return this.httpClient.put(UPDATE_PATH + '/' + username, item);
  }

  createItem(item: any){
    return this.httpClient.post(CREATE_PATH, item);
  }

  /*saveImage(){

  }*/

}
