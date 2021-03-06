export const BACKEND_PATH ='http://localhost:4000';

export const USER_PATH = BACKEND_PATH + '/user';
export const REGISTRATION_PATH = USER_PATH + '/register';
export const LOGIN_PATH = USER_PATH + '/login';
export const INFO_PATH = USER_PATH + '/info';

export const ITEM_PATH = BACKEND_PATH + '/item';
export const SHOW_PATH = ITEM_PATH + '/show';
export const UPDATE_PATH = ITEM_PATH + '/update';
export const CREATE_PATH = ITEM_PATH + '/create';

export const SHOPPING_CART_PATH = BACKEND_PATH + '/shopping-cart';
export const ADD_ITEM_PATH = SHOPPING_CART_PATH + '/add';
export const DELETE_ITEM_PATH = SHOPPING_CART_PATH + '/delete';

export const ORDER_PATH = BACKEND_PATH + '/order';

export const CAMPAIGN_PATH = BACKEND_PATH + '/campaign'
export const UPLOAD_IMAGE_PATH = CAMPAIGN_PATH + '/upload_img'