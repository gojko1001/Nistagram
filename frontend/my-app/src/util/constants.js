// Paths
export const USER_CREDENTIALS_PATH = "/authentication-api/userCredentials"
export const REGISTER_USER_PATH = USER_CREDENTIALS_PATH + "/add"
export const LOGIN_PATH = USER_CREDENTIALS_PATH + "/login"
export const LOGIN_GOOGLE_PATH = USER_CREDENTIALS_PATH + "/login_google"
export const CHANGE_PASSWORD_PATH = USER_CREDENTIALS_PATH + "/change_password"
export const RESET_PASS_PATH = USER_CREDENTIALS_PATH + "/reset_password"
export const VERIFY_ACC_PATH = USER_CREDENTIALS_PATH + "/verify"


export const USER_PATH = "/user-api/user"
export const SEARCH_USER_PATH = USER_PATH + "/search"
export const VERIFY_USER_PATH = "/user-api/verify"
export const GET_CATEGORIES_PATH = VERIFY_USER_PATH + "/categories"

export const USER_RELATION_PATH = "/user-api/user_relation"
export const GET_FOLLOWERS_PATH = USER_RELATION_PATH + "/followers"
export const GET_FOLLOWINGS_PATH = USER_RELATION_PATH + "/followings"
export const GET_BLOCKED_USERS_PATH = USER_RELATION_PATH + "/blocked"
export const GET_CLOSE_FRIENDS_PATH = USER_RELATION_PATH + "/close_friends"
export const FOLLOW_PATH = USER_RELATION_PATH + "/follow"
export const RELATION_STATUS_UPDATE = USER_RELATION_PATH + "/update"
export const DELETE_RELATION_PATH = USER_RELATION_PATH + "/remove"


export const TAG_PATH = "/media-api/tag"

export const LOCATION_PATH = "/media-api/location"

export const IMAGE_PATH = "/media-api/image"
export const SEARCH_TAG_PATH = IMAGE_PATH + "/search_tag";
export const SEARCH_LOCATION_PATH = IMAGE_PATH + "/search_location";


// Messages
export const SERVER_NOT_RESPONDING = "An error has occured while trying to connect to server."