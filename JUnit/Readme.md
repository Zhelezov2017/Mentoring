Task 2.  Test your crazy calculator
Implement Calculator class and cover its methods with JUnit tests with both positive and negative scenarios. Group negative and positive tests cases into separate test suites.

Add following functionality:

1. Addition, subtraction, multiplication, division, root/power function for int and double values.

2. Division, root/power should check parameters and throw IllegalArgumentException (division by zero etc.).

3. isPrime function and design test parameters for negative timeout scenario.

4. Fibonacci sequence function. Use Java Hamcrest matchers to validate result.

You should follow TDD approach: red - green – refactor

Task 4. Mock framework

Take your pet/mentoring application from previous performed tasks.
The application should have two layers: DAO and Service.
Let’s test Service layer:
Implement mock object for DAO using any mocking framework
Implement JUnit test and cover all service API methods:

· Standard tests

· Tests with timeout

· Tests on exception

Task 6. (20 points) Test your crazy calculator with TestNG

Take your solution from task 2 and a few functions. Migrate your tests to TestNG.
Also, your tests should have to:

· Configured via testng.xml

· Run in parallel

· Grouped and Prioritized test cases (add more tests if it’s required)

· Support a few parameters

· Dependencies between tested methods

Task 5. SonarQube(Download maven with parameters "clean install sonar:sonar -f pom.xml" for coverage tests)
Report-tests in resources (png screens). Was to provide plugin JaCoCo.

Set up SonarQube coverage plugin to use coverage reports for one of your previous applications.

Note: In this task SonarQube could be changed on JaCoCo