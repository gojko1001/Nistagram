// Paths
export const USER_CREDENTIALS_PATH = "/authentication-api/userCredentials"
export const LOGIN_PATH = USER_CREDENTIALS_PATH + "/login"
export const LOGIN_GOOGLE_PATH = USER_CREDENTIALS_PATH + "/login_google"
export const RESET_PASS_PATH = USER_CREDENTIALS_PATH + "/reset_password"
export const VERIFY_ACC_PATH = USER_CREDENTIALS_PATH + "/verify"

export const USER_PATH = "/user-api/user"
export const ADD_USER_PATH = USER_PATH + "/user-api/add"
export const SEARCH_USER_PATH = USER_PATH + "/search"

export const TAG_PATH = "/media-api/tag"

export const LOCATION_PATH = "/media-api/location"

export const IMAGE_PATH = "/media-api/image"
export const SEARCH_TAG_PATH = IMAGE_PATH + "/search_tag";
export const SEARCH_LOCATION_PATH = IMAGE_PATH + "/search_location";


// Messages
export const SERVER_NOT_RESPONDING = "An error has occured while trying to connect to server."