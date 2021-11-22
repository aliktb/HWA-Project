Coverage: 71%

# HWA-Project

This is a full stack Spring boot application for a fictional library.

## Get started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

<br/>

## Prerequisites

---

What things you need to install the software and how to install them

<br/>

If you do not have git bash, install it from <a href=https://git-scm.com/downloads>here</a>

Install MySQL by <a href=https://dev.mysql.com/downloads/windows/installer/8.0.html>clicking here</a>

Install Java by <a href=https://www.oracle.com/java/technologies/downloads/#java8>clicking here</a>

Follow the steps <a href=https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html>here</a> to add JAVA to the system PATH

If you would like to run the application from the source code you will also need to install eclipse from <a href=https://www.eclipse.org/>here</a>

You will also need to install Spring boot from the Eclipse marketplace.

### Running from executable

---

For the simplest way to run the project, download the repository and run the pre-compiled jar file. This is done by navigating to the folder you downloaded the repository to in a terminal window and entering the following command

```
java -jar HWAProject-0.0.1-SNAPSHOT.jar
```

### Installing

---

A step by step series of examples that tell you how to get a development env running:

- Create a new folder in your machine's local directory and open that folder in the file explorer.

- Right-click and select

```
git bash here
```

Type

```
git clone https://github.com/aliktb/HWA-Project.git
```

- After hitting enter, the repository should be available in your your machine

- Open Eclipse and select

```
File > Open Projects from File System...
```

- Right-click on the project in the package explorer and click

```
Run as > Spring boot App
```

- The application should now be running in the console. To check, simply visit http://localhost:9000.

<br/>

### Troubleshooting :warning:

---

<br/>

#### Ports

This application runs on port 9000. If there is another service on your machine running on port 9000, you can modify the Spring boot application by navigating to

```
src > main > resources > application-prod.properties
```

Here you can specify what port number you want the application to use. Note if you change this, you will need to change the port numbers for the fetch request API in the javascript files.

<br/>
<br/>

#### Front end files

All front end files are found within the projects static folder:

```
src > main > resources > static
```

When running the application, the home page is accessible from http://localhost:9000/.

If building or running from the .jar executable, all front end files will be hosted when starting the application.

<br/>
<br/>

#### Database

This file also has the following property set:

```
spring.jpa.hibernate.ddl-auto=create-drop
```

This allows spring to create a new instance of the database and drop it after running. In this configuration, no data will persist if you run the application a second time. If you are having issues with the database not being created, you can always manually create the database in MySQL as follows:

```
CREATE DATABASE hwadb;
```

The create-drop option is used in the .jar executable but you are free to change the ddl-auto setting to create or update within the source code.

<br/>
<br/>
<br/>

## Testing

---

Running the tests is as simple as running the project as a JUnit test in Eclipse. The pom.xml contains all the dependencies neccessary to run the tests including JUnit and Mockito. The tests should appear next to the project explorer and break down the test classes and show if any tests have failed.

<br/>
<br/>

### Unit Tests

Unit testing through JUnit allows the application's source code to be tested in their smallest units possible, which are methods. Each method's output is verified against a predetermined result that is to be expected given any intial parameters or inputs have been selected. Mockito allows mocking objects in a test to check functionality of a method which relies on an instantiated object.

To run the unit tests, simply run the project as a JUnit test. JUnit should be available if the dependencies in the pom.xml are set up correctly. To see the test coverage, simply right click on the project in the project tree and select Coverage As > JUnit tests.

<br/>

### Selenium

Selenium tests are placed inside

```
src > test > java > com.qa > uat
```

Selenium requires the front-end to be hosted and the easiest way is to run the project as a spring boot app. Then leave the application running and run the folder as a JUnit test

<br/>

## Deployment

---

Deployment can be acheived using the precompiled Fat JAR file and a MySQL instance installed. The jar file uses localhost:9000 as api URL and localhost:3306 as the database URL so these might need to be changed to FQDN and connected through a reverse-proxy or similar if it is to be accessible through the wider internet. A simple way to host a java application is through docker.

<br/>
<br/>

## Built With

---

[Spring boot](https://spring.io/projects/spring-boot)

<br/>

## Versioning

---

We use [Git](https://git-scm.com/) for versioning.

<br/>

## Authors

---

- **Ali Khattab** - [aliktb](https://github.com/aliktb)

<br/>

## Acknowledgments

---

Many thanks go to the [QA Academy](https://www.qa.com/) and their trainers for their expertise and support
