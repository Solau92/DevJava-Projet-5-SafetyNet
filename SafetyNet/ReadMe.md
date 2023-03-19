#**ReadMe** 


##- **SafetyNetAlerts** 

An app for emergency services. </br>
This app uses Java to run.


##- **Getting Started**

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 


##- **Prerequisites**

You need to install : 
* Java 17
* Maven 3.8.7


##- **Installing** 

* [Install Java](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)
* [Install Maven](https://maven.apache.org/install.html)


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.2/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.2/reference/htmlsingle/#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)


##- **Running App** 

Import the code into an IDE of your choice and run the SafetyNetApplication.java class to launch the application.


##- **Documentation**

You can access information in a webbrowser or in Postman. </br>
Refer to the document named « *SafetyNetAlerts Documentation.pdf* » in the same folder as this ReadMe.


##- **Testing**

The app has unit tests and integration tests written. </br>
To run the tests from maven, go to the folder that contains the pom.xml file and execute the following command : *mvn test*. </br>
To get both Surefire Report and Jacoco Report in Maven Site, you can execute the command mvn site and open the file « *index.html* » in the folder ./target/site. </br>
JaCoCo and Surefire reports are in the section « *Project Reports* ».
