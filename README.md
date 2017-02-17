# vertx-weather-demo-app

Small app connecting to external service Open Weather and fetching data about:

*today’s date
*the city name
*overall description of the weather
*temperature in Fahrenheit and Celsius
*sunrise and sunset times in 12 hour format

App is based on reactive Java framework Vertx.

Prerequisites:
Maven >=3.0.5
Java 1.8 SE/JDK


Steps to build:
1. Run:  mvn clean package.
2. Run: java -jar target/demo-weather-app-1.0-SNAPSHOT-fat.jar
3. Application will be started under url: http://localhost:8081/


ToDo:

*Integration tests  for Vertx
*Check coverage and presence of Sonar issues
*Add missing JavaDocs
*Add some styles
*Add support for more than two cities dropdown with autocomplete functionality
*Switch from Maven to Gradle
*Release app