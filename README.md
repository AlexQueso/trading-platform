# Trading platform code challenge by Alex Quesada
This is a web application simulating a trading platform. It was build using Spring boot 3.2.4 and Java 21.

The persistence layer underneath the application consists on a MongoDb database.
### Set up
In order to run the web app locally, the following software must be installed in your machine:
* Java 21
* Docker
* Apache Maven 3.9.6

### How to run the application
First, the database needs to be up and running. In order to do so, you have to go to the go to the 
[/infra](./infra) folder, open a terminal and run the following command:
```bash
docker compose up
```
A new MongoDb database will start in a few seconds.

After the database initialization, you can start the application by running the following command (in the 
main folder, where the pom.xml file is):
```bash
mvn spring-boot:run
```
The application should start right away.

### How To Use the application
The application exposes a public API. In order to make the manual testing a bit easier, I've provided a 
postman collection containing the full API. You can find it [here](./trading_platform.postman_collection.json).

Once the collection is imported in postman, calling the endpoints should be easy.