# Java Servlet Experiment

A simple Java Servlet web application built with Maven and packaged as a WAR file. This project is intended to demonstrate server-side programming with Java Servlets.

## Concepts Covered

- Java Servlet and HTTP request handling
- Servlet API
- `HttpServlet` and `doGet()` / `doPost()` concepts
- Request and response objects
- Servlet URL mapping using `@WebServlet`
- Maven WAR packaging
- Deployment on Apache Tomcat

> Note: The exact features depend on the Java classes and web pages present in this project.

## Requirements

- JDK 17 or newer
- Apache Maven
- Apache Tomcat compatible with the Jakarta Servlet API used by the project (Tomcat 11 for Jakarta Servlet 6.1)

## Build the Project

Open Command Prompt in the project folder containing `pom.xml` and run:

```cmd
mvn clean package
```

If the build succeeds, Maven creates the WAR file in the `target` folder. In this project, the generated file is:

```text
target/servlet-1.0-SNAPSHOT.war
```

## Run on Windows

1. Build the project using `mvn clean package`.
2. Copy `target/servlet-1.0-SNAPSHOT.war` into the `webapps` folder of your Tomcat installation.
3. Start Tomcat by running `bin/startup.bat`.
4. Open the application in your browser using:

   http://localhost:8080/servlet-1.0-SNAPSHOT/

5. If the root URL shows a 404 error, open the URL matching the servlet mapping in the Java code. For example, if the servlet uses `@WebServlet("/hello")`, try:

   http://localhost:8080/servlet-1.0-SNAPSHOT/hello

The correct endpoint depends on the `@WebServlet` mapping and whether the project contains a welcome page.

## Stop Tomcat

Run `bin/shutdown.bat` from the Tomcat `bin` folder. Closing the browser tab does not stop the server.

## Common Issues

- **Build fails:** Check the Maven error output and verify the project structure.
- **404 Not Found:** Confirm the WAR file is in Tomcat's `webapps` folder, Tomcat is running, and the URL matches the servlet mapping.
- **Port 8080 is unavailable:** Check whether Tomcat is configured to use a different port.
- **Tomcat fails to start:** Check the terminal output and Tomcat logs.

## Author

Kovid Gupta
