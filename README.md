# tantalum

 build application use 
 <b><location/ mvn clean install>
 to run application use the following command
 <java -jar target/myapplication-0.0.1-SNAPSHOT.jar>

first micro service: message-service is used to read/delete(only after 2 mins)/edit(only with in 10 secs)/get specific number of messages/consume uuid service. There are tests written to make sure the services are built as per the requirements.
second micro service :generate uuid is used to generate a uuid with a prefix and suffix. these values are configurable in app.properties.
service uses h2:mem DB, hence data will be lost once services are stopped.
a data loader component is defined which loads sample data during application start.


tried using Eureka for service discovey, but was not able to run due to limitations on office laptop. Happy to explain in detail.
