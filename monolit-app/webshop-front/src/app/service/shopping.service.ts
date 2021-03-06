import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ADD_ITEM_PATH, DELETE_ITEM_PATH, ORDER_PATH, SHOPPING_CART_PATH } from '../util/paths';

@Injectable({
  providedIn: 'root'
})
export class ShoppingService {

  constructor(private httpClient: HttpClient) { }

  getShoppingCart(username: any){
    return this.httpClient.get(SHOPPING_CART_PATH + '/' + username);
  }

  addItemInShoppingCart(username: any, itemInCart: any){
    return this.httpClient.post(ADD_ITEM_PATH + '/' + username, itemInCart);
  }

  deleteItemFromShoppingCart(username: any, itemInCart: any){
    return this.httpClient.delete(DELETE_ITEM_PATH + '/' + username + '/' + itemInCart);
  }

  createOrder(order: any){
    return this.httpClient.post(ORDER_PATH, order);
  }

}
