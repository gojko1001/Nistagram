# Nistagram
Social network for sharing photos and videos

### Contributors (Team 10):
|  | Student |
| ------ | ------ |
| Student 1 | Maja Stamenić RA 65/2017| 
| Student 2 | Gojko Novčić RA 208/2017| 
| Student 3 | Nastasja Damjanac RA 38/2017|
| Student 4 | Marija Milanović RA 197/2017|


### Microservices:
| Name | Port | About
| ------ | ------ | ------ |
| Eureka-server | 8761 | Eureka for service discovery in the microservice environment. |
| Proxy-server | 8762 | Zuul handles all the requests and performs the dynamic routing of microservice applications. |
| Front service | 3000 | Frontend for Nistagram |
| Authentication-microservice | 3031 | http://localhost:8762/authentication-api/ |
| User-microservice | 3032 | http://localhost:8762/user-api/ |
| Media-microservice | 3033 | http://localhost:8762/media-api/ |
| Campaign-microservice | 3034 | http://localhost:8762/campaign-api/ |
| Messenger-microservice | 3035 | |
| Notification-microservice | 3036 | http://localhost:8762/notification-api/ |
| Webshop application | 4000 |  http://localhost:4000/ |
| Webshop frontend | 4200 | Frontend for Webshop |
