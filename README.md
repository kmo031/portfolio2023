[프로젝트] 
======================

> 스프링부트 공부 하면서 작성해봅니다.

기능구현:

- 게시판
- 스프링 시큐리티
- 이미지 업로드
- OAuth2 소셜로그인

Site:

- [Github Repository](https://github.com/kmo031/portfolio2023)
- [AWS Web Site](http://sangmin-portfolio.p-e.kr/)


### Project layout

```
|- sql/                                  // SQL migration scripts for SQLite database
|- src/
|   |
|   |- controller/                       // Folder containing REST Controllers (UserController)
|   |- db/                               // Folder containing the database client
|   |- dto/                              // DTOs are declared here
|   |- service/                          // Service business logic classes (UserService)
|   |- AppComponent.hpp                  // Service config
|   |- DatabaseComponent.hpp             // Database config
|   |- SwaggerComponent.hpp              // Swagger-UI config
|   |- App.cpp                           // main() is here
|

#### Board

|HTTP Method|URL|Description|
|---|---|---|
|`POST`|http://localhost:8080/boards | Create new User |
|`PUT`|http://localhost:8080/boards/{uniqueid} | Update User by ID |
|`GET`|http://localhost:8080/boards/{uniqueid} | Get User by ID |
|`DELETE`|http://localhost:8080/boards/{uniqueid} | Delete User by ID |
|`GET`|http://localhost:8080/boards/list/{page} | Get All Users with Paging |

## image Board

|HTTP Method|URL|Description|
|---|---|---|
|`POST`|http://localhost:8080/pboards | Create new User |
|`PUT`|http://localhost:8080/pboards/{userId} | Update User by ID |
|`GET`|http://localhost:8080/pboards/{userId} | Get User by ID |
|`DELETE`|http://localhost:8080/pboards/{userId} | Delete User by ID |
|`GET`|http://localhost:8080/pboards/list | Get All imgage board |

## login





### 코드블럭


```
<pre>
<code>

</code>
</pre>
```

<pre>
<code>

</code>
</pre>



<pre>
<code>
```

```
</code>
</pre>

```

```


# 4. 정리

> 
