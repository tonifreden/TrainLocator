# Train Locator

This simple [Spring Boot](https://spring.io/guides/gs/spring-boot) application was built using [Maven](https://spring.io/guides/gs/maven/). Train Locator lets users view currently running trains and their locations in Finland.

Live train data is provided by [Digitraffic](https://www.digitraffic.fi/rautatieliikenne/):
- Location, speed and train number data is authentic
- Names and destinations are randomly generated
    - Names are a combination of a randomly generated name + an actual train number

## Running Train Locator

Clone the repository on the command line
```
git clone https://github.com/tonifreden/TrainLocator.git
```
Fire up Eclipse and import project
```
File -> Import -> Maven -> Existing Maven project
```
Run the application main method
```
Run as -> Java Application
```
You can then access Train Locator backend at http://localhost:8080/

## Login
- Test **admin**, password "admin"
- Test **user**, password "user"
- Users can sign up and create their own private account with which to use the application

## Features
- Train data is stored in an in-memory database (H2) which gets populated at startup
- Train locations are updated every 10 seconds without users’ interaction (e.g. refreshing the web page)
- Users can view their personal information
- Admin(s) can view all users in a list and delete them, if they misbehave

## Train Locator rudimentary frontent

Frontend was built using [React](https://reactjs.org/), although it is as yet accessible without user authentication. On the frontend side users can see trains plotted to a map of Finland and updated every 10 seconds.

To view the map, navigate to the `frontend` directory of the project and run the frontend from the command line
```
cd frontend
npm start
```

**Before navigating to http://localhost:3000/, make sure the Spring Boot application is running!**