Task 1. Full power of Hibernate(40 points)

Implement DAO layer for your pet app or domain model defined with your mentor

You application should cover next features:

1. (4 points) Inheritance: select appropriate strategy for you model.
2. (2 points) Some entities should have One-to-Many relationship.
3. (2 points) Some entities should have Many-to-One relationship.
4. (2 points) Try to involve lazy and eager initialization.
5. (2 points) Map embedded object and enum field.
6. (4 points) DAO layer should support CRUD operation
7. (3 points) DAO should have several methods which select data with help of:
a. NamedQuery
b. Criteria API
c. Native Query
8. (1 points) Enable logging of SQL queries with parameters
9. (4 points) Provide solution for n+1 problem 
10. (4 points) Support composite key
11. (2 points) Properly override equals/hashcode 
12. (2 points) Generate meta-model and use it with Criteria 
13. (2 points) Enable 2nd level cache and use it
14. (2 points) Try to map value objects: List, Map<String, SomeNotEntityObject>


Task 2. (30 points) Real project with Hibernate
Create Spring application with Hibernate or JPA.
List of models:
· Employee
· EmployeeStatus
· Address
· EmployeePersonalInfo
· Project
· Unit

Requirements:
1. EmployeeStatus should be Enum type
2. Employee has attribute ‘external’
3. Address should be embedded to Employee object
4. Relationship between Employee and EmployeePersonalInfo should be one-to-one
5. Relationship between Employee and Project should be many-to-many
6. Relationship between Unit and Employee should be one-to-many

Implement Service API which provides:
1. Take into account the following:
2. Create Employee / Unit / Project
3. Find employee / Unit / Project by id
4. Delete employee / Unit / Project by id
5. Update Employee / Unit / Project by id
6. Add Employee to Unit by id’s

7. Assign Employee for Project by id’s
8. Get Projects with Employees which are not external