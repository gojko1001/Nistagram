  
export function getToken() {
    return localStorage.getItem("JWT");
}

export function saveToken(token) {    
    localStorage.setItem("JWT", token);
}

export function getParsedToken() {
    let token = localStorage.getItem("JWT");
    if(token == null){
        return null;
    }
    var base64Payload = token.split(".")[1];
    var payload = Buffer.from(base64Payload, "base64");
    return JSON.parse(payload.toString());
}

export function removeToken() {
    localStorage.removeItem("JWT");
}
export function getEmailFromToken() {
    let parsedToken=getParsedToken();
    if(parsedToken == null){
        return null;
    }
    return parsedToken.sub;
}