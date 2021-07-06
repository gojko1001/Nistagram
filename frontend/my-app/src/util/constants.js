// Paths
// Authentication microservice
export const USER_CREDENTIALS_PATH = "/authentication-api/userCredentials"
export const USER_ADMIN_REQUEST = "/authentication-api/agentRequest/add"
export const REGISTER_USER_PATH = USER_CREDENTIALS_PATH + "/add"
export const REGISTER_AGENT_PATH = USER_CREDENTIALS_PATH + "/addAgent"
export const LOGIN_PATH = USER_CREDENTIALS_PATH + "/login"
export const LOGIN_GOOGLE_PATH = USER_CREDENTIALS_PATH + "/login_google"
export const CHANGE_PASSWORD_PATH = USER_CREDENTIALS_PATH + "/change_password"
export const EMAIL_RESET_LINK_PATH = USER_CREDENTIALS_PATH + "/send_email"
export const RESET_PASS_PATH = USER_CREDENTIALS_PATH + "/reset_password"
export const VERIFY_ACC_PATH = USER_CREDENTIALS_PATH + "/verify"


// User microservice
export const USER_PATH = "/user-api/user"
export const SEARCH_USER_PATH = USER_PATH + "/search"
export const VERIFY_USER_PATH = "/user-api/verify"
export const GET_CATEGORIES_PATH = VERIFY_USER_PATH + "/categories"

export const USER_RELATION_PATH = "/user-api/user_relation"
export const GET_FOLLOWERS_PATH = USER_RELATION_PATH + "/followers"
export const GET_FOLLOWINGS_PATH = USER_RELATION_PATH + "/followings"
export const GET_REQUESTS_PATH = USER_RELATION_PATH + "/requests"
export const GET_BLOCKED_USERS_PATH = USER_RELATION_PATH + "/blocked"
export const GET_CLOSE_FRIENDS_PATH = USER_RELATION_PATH + "/close_friends"

export const FOLLOW_PATH = USER_RELATION_PATH + "/follow"
export const ACCEPT_FOLLOWER_PATH = USER_RELATION_PATH + "/accept"
export const DELETE_REQUEST_PATH = USER_RELATION_PATH + "/removeRequest"
export const RELATION_STATUS_UPDATE_PATH = USER_RELATION_PATH + "/update"
export const BLOCK_USER_PATH = USER_RELATION_PATH + '/block'
export const DELETE_RELATION_PATH = USER_RELATION_PATH + "/remove"

export const MUTE_POST_PATH = USER_RELATION_PATH + "/mutePost"
export const MUTE_STORY_PATH = USER_RELATION_PATH + "/muteStory"
export const NOTIFY_POST_PATH = USER_RELATION_PATH + "/notifyPost"
export const NOTIFY_STORY_PATH = USER_RELATION_PATH + "/notifyStory"


// Media microservice
export const TAG_PATH = "/media-api/tag"

export const LOCATION_PATH = "/media-api/location"

export const IMAGE_PATH = "/media-api/image"
export const SEARCH_TAG_PATH = IMAGE_PATH + "/search_tag"
export const SEARCH_LOCATION_PATH = IMAGE_PATH + "/search_location"

// Campaign microservice
export const CAMPAIGN_PATH = "/campaign-api/campaign"


// Messages
export const SERVER_NOT_RESPONDING = "An error has occured while trying to connect to server."

// Roles
export const ROLE_USER = "ROLE_USER"
export const ROLE_ADMIN = "ROLE_ADMIN"
export const ROLE_AGENT = 'ROLE_AGENT'