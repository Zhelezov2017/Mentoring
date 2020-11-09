Task 1.(20 points) SpringJDBC-based Social Network

1. Create simple database with tables Users (id, name, surname, birthdate), Friendships (userid1, userid2, timestamp), 
Posts (id, userId, text, timestamp), Likes (postid, userid, timestamp).

2. Populate tables with data which are make sense (> 1 000 users, > 70 000 friendship, > 300 000 like in 2025)*

3. Program should print out all names (only distinct) of users who has more when 100 friends and 100 like in March 2025.

Implement all actions (table creation, insert data and reading) with SpringJDBC.

* - you could prepare dictionaries (maps) in memory (with usernames for example) or data in files to generate data for the populating process

Task 4. (10 points) Play the game with the DBUnit

Add five DBUnit tests to appropriate project (pet project) or another. Prepare test datasets if it is required

Task 5. (20 points) Stored Procedure

1. Take the existing (or write from zero) JDBC solution with a few CRUD operations and more complex SQL 
(for simple report generation) and migrate it to stored procedures.
2. Write SQL – script to create those stored procedures with Java style parameters and specific external names. 
3. Write a test which drops all stored procedures and creates a few of them via Java code
4. Also, provide the script to print out all stored procedure 
in your database and dropping them for test purposes, for example.
5. Check that the application works properly, all test are green and so on.
6. Compare the performance of two solution; explain to your mentor the benefits 
or disadvantages of storage procedure usage for the taken application.
* 3-5 tables with CRUD operations and two complex SELECTS can be enough.
