# REST API - Twitter clone.

A twitter clone implementation rest api, if not working with docker and compose please
fill in your db details within application.properties

# Details

<!--ts-->
* [Prerequisites](#Prerequisites)
* [Running](#Running)
* [Summary](#Summary)
* [Tests](#Tests)
* [Technologies/Tools](#Technologies/Tools)
<!--te-->

# Prerequisites

To test/run the code in a development environment, you will need (some are optional):

<a href="https://git-scm.com/">Git</a>,
<a href="https://www.docker.com/"> Docker</a>,
<a href="https://spring.io/projects/spring-boot"> Spring Boot</a>,
<a href="https://www.oracle.com/java/technologies/javase/javase-jdk11-downloads.html"> Java 11</a>,
<a href="https://www.postgresql.org/">PostgreSQL</a>,

If it is just to run the application:

<a href="https://git-scm.com/">Git</a>,
<a href="https://www.docker.com/"> Docker</a>,
<a href="https://docs.docker.com/compose/"> Docker Compose </a>,

# Summary

The following endpoints were implemented.

#### Auth
    1. Signup [POST] http://localhost:8080/v1/api/auth/signup
    {
      "email": "string",
      "name": "string",
      "password": "string",
      "username": "string"
    }

    2. Signin [POST]  http://localhost:8080/v1/api/auth/signin
    {
      "password": "string",
      "usernameOrEmail": "string"
    }

##### PROTECTED ROUTES (use jwt from login above)
    1. Get Tweets [GET]  http://localhost:8080/v1/api/tweet
    {
      "password": "string",
      "usernameOrEmail": "string"
    }

    2. POST Tweets [GET]  http://localhost:8080/v1/api/tweet
    {
      "content": "string"
    }

    3. Search Tweets [GET]  http://localhost:8080/v1/api/tweet/{search}
    {
      "search": "string"
    }

    4. Retweet [POST]  http://localhost:8080/v1/api/tweet/{tweetId}/retweet

    5. FOLLOW USER [PUT] http://localhost:8080/v1/api/user/{userId}/follow
  
    6. Get User details and tweets
       [GET]  http://localhost:8080/v1/api/user/{userName}



# Running

### - Clone the project using (HTTPS):

    git clone https://github.com/EnsleyEC/luizalabs_api.git

### - Access the project folder: luizalabs-server-rest

    cd twitter_clone_rest_api/

### - Generate the project image (API + PostgreSQL) with Docker Compose, in the same directory as the docker-compose.yml file

    docker-compose up

### - Local access URL:

    Base URL: http://localhost:8080/

    DOC: http://localhost:8080/v1/swagger-ui.html/

### - API Roles

    To use, you must have an account or create an account first. And then, log in, taking the token and sending the other endpoints to be able to use them.

    Free endpoints to use, without having to send a token:

    - Create account: /api/auth/signup
    - Auth: /api/auth/signIn

    Mandatory header example:

    {key: Authorization, value: Bearer flkjkds.......}

# Tests

- Not factored in yet

# Technologies/Tools

The following tools were use in the construction of the project:

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Java 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
- [PostgreSQL](https://www.postgresql.org//)
- [Docker](https://www.docker.com/)
