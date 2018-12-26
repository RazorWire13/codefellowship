# Labs 16-19: Code Fellowship Website
CodeFellowship allows people learning to code to connect with each other and support each other on their coding journeys. This Java app is a full stack effort that is bootstrapped using Spring and includes authentication capability.

# Features
* Homepage provides site information and has links to allow users to login or signup for an account
* Users can login using a self-provided username and bCrypt hashed password
* Users create a profile page using their information
* Users can post comments to their profiles and to the profiles of other users.

## How to Setup Project
In order to run, you must have a file called: ```application.properties``` inside the directory: ```/src/main/resources```.

File contains:

```
spring.datasource.platform=postgres
spring.datasource.url=jdbc:postgresql://localhost:5432/codefellowship_app
spring.datasource.username=[your-postgres-username]
spring.datasource.password=[your-postgres-password]

spring.jpa.generate-ddl=true

# Disable the below line to stop resetting the databases each time the app is run
spring.jpq.hibernate.ddl-auto=create
```

## How to Run with Gradle
From the terminal, use command:
`./gradlew bootRun`

Site map:
\
`http://localhost:8080/` homepage

`http://localhost:8080/login` login page

`http://localhost:8080/signup` signup page

`http://localhost:8080/users/{userId}` user page by id

`http://localhost:8080/allposts` all user posts page

`http://localhost:8080/users/{userId}/posts` user posts page by id

`http://localhost:8080/profile/{userId}` user profile page by id

`http://localhost:8080/myprofile` user's own profile page by id

`http://localhost:8080/myposts` user's own posts page

## Credits

* Code bracket image from Code Fellows: codefellows.org
* Setup code base for login/authentication: https://www.baeldung.com/spring-security-login
* Special credit to Zahra Mohamed and Jeff Borda for their guidance on successfully building a controller
