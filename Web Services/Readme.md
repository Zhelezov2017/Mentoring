Task 1. (20 points) Simple Rest for User catalogue 

Create REST web-service for User domain that provide CRUD operations on catalogue of users. 
Acceptance criteria: 

User information should contain at least: first name, last name, login and email. 
Use XML to create user, use JSON to update. 
Create Rest service to upload and download user logo as an image. 
The application should be implemented and deployed on the application server. 
You can use any Rest implementation (Jersey, Restlet, Spring REST etc.). 
To test your application, use jersey Rest client that will cover all operations or Rest-assured. 
User data can be saved anywhere, database is not required. 

The task should be demonstrated on the local machine. 

Task 2. (20 points) SOAP File Share 

Develop CRUD web-services for File Share application using SOAP specification. 
Acceptance criteria: 
It should support a few file formats like pdf, txt, csv etc. 
CRUD via SOAP implemented. 
The task should be demonstrated on the local machine. 

Task 5. (30 points) REST vs SOAP battle 
Create REST and SOAP web-application using Spring 
REST: 
Implement backend for User service and develop REST architecture. 
Create next entities: User (id, name, surname, mail, tasks), Task (id, name, description, creationDate, deadLine). 
App should support CRUD operations for mentioned entities. 
Operation should be defined by HTTP methods. 
You should follow resources approaches like: user/%userId/task/%taskId 
Test your app with any REST client: java client, IDEA, soapUI, curl, Postman 
SOAP: 
Create corresponding web services for mentioned CRUD operations for your entities. 
Generate WSDL and publish. 
Generate client and verify your web services. 
Introduce transaction support 