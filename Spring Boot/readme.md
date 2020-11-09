Task 1. (15 points) Move to Spring Boot

Take project from one of the solved tasks in Spring Core or your small pet project 
on Spring with the domain model and update it with the classic Spring Boot.

The application should contain:
1.	Logger
2.	Two profiles (“release” and “debug”)

Task 5. (40 points) Real estate agency with Spring Boot

Create Spring Boot application with CommandLineRunner and ApplicationRunner. 
This application should implement next business logic:

•	Keep list of real estate objects (CRUD operations)
•	Increment counter of views for each real estate object
•	Attach agent to each object
•	Track all sold properties for each agent
•	Provide report of top-5 agents by sum of sold properties for each month in time – range defined by the user
•	Emulate price changing for each real estate object
•	After running of application a few real estate transactions and price changes should happened in real-time 
with pre-defined schedule and logged on the console or in file

The application should contain:
1.	Logger
2.	DataSource bean
3.	Order between two runners

Task 6. (10 points) Move real estate agency to RESTful application

Move application from the previous task to the REST service to get report of top-5 … in JSON format. 
It should be run on Jetty. Don’t forget to package in JAR.

Task 7. (15 points) Custom Spring Boot Starter

Create a custom Spring Boot autoconfiguration starter to count objects in database 
(from task 3 or from other task where you have a database connection) and print message 
“It’s BigData project” [and shutdown the app, muhahaha] if the amount of objects more than 1000. 
