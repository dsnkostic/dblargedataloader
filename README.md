# dblargedataloader
Small rest API web app that generates large chunk of test data in the database

### Background
Recently, I was tasked to measure the performance of the certain queries, so I decided to create a helper application that will populate database with some data in the fastest way possible. This was an opportunity to test some features of the play framework, like connecting using the JPA and to optimize concurrent access to the database. For practical reasons, changes in the model properties were made from the actual ones. 

### Technologies used
* Play framework/Java
* JPA/Hibernate
* Guice

### Application purpose
Application accepts the json payload (2 ways) and based on that, it generates data in the database. App is meant to generate large amount of data (1M) in the short time. Response that you get is just an overview of how much data will be created. Be aware that server response does not indicate that data is finished with the creation! Data, will be asynchronously created in the background. Check app logs to determine when generation is completed and the time needed for the creation.

* Yes, I could have created some polling mechanism, but I do not see the reason for that at all.
* Please note that I did not have time to do the JSON payload validation, so you will get exceptions if you enter unrecognized payload. 
* Feel free to play with concurrency/database tweaks to measure the loads.
* Do not expect this to be real REST API example :)

### Domain Model
App stores 'requests' that are requested by the department of the company. Syntax and semantic on what 'request' is, is not important. For the sake of clarity, it is a simple string (detail). As data row, we store, the company id, the department id, and the 'request' detail. Besides, we have a mandatory id (not really used) and the creation datetime flag.

### Running the application
Application should be run through sbt launcher or through Intellij Run configuration as Play 2.x
You also need a working postgres database. You can tweak db connection parameters in the `application.conf`. You can also use docker compose to setup an empty configured database. See file `docker-compose.yml`.

### Additional files
Check folder `misc` for postman collection and OpenAPI/swagger documentation