Google Drive Uploader APP
============================

## Requirements
1.	Allow users to log into portal with a user and password
2.	If a “CHANGEPWD” flag is active in the DB it should demand the user changes the pwd.
3.	Once the user successfully logs in using user and pwd it sends a PIN in a text message via clickatell gateway.
4.	Login validates the code is correct and allows authentication.
5.	Any incorrect password or incorrect PIN will be challenged. 
6.	After 3 login attempts it should warn one more attempt and it will lock
7.	After 4 login attemps the user should be locked.

## Setup

* Download and install [MAVEN](https://maven.apache.org/download.cgi)
* Download and install [JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

## Configure

open file in text editor `\src\main\resources\application.yml`
and configure database settings
    
## Maven
### Running locally

`mvn spring-boot:run`

To use visit: http://localhost:8080/

### Getting jar

`mvn install`

copy jar from `\target\login-dual-auth-0.0.1-SNAPSHOT.jar`

run command `java -jar login-dual-auth-0.0.1-SNAPSHOT.jar`


