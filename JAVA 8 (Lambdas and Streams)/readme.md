Task 1: Create several instances of Runnable interface with different behavior using lambda expressions. Run these lambdas via Thread AP
Task 2: A person stream.

Create:
1. Person class with name and age fields
2. A collection of Persons;
3. Two instances of Comparator<Person> interface using lambda expressions: first one for comparing by name, second one – by age

Then sort them using these comparators.
Use forEach method for printing information about all the persons.
Try to use method reference if it is possible.

Task 3: Functional Interface Sandbox
1. Implement each of main Java Standard Library functional interfaces (supplier, predicate etc.) using lambda expressions.
2. Create your own functional interface and add several implementations using both lambda expressions and inner anonymous classes.
3. Add few default methods to it and use them.
4. Add few static methods to it and use them.

Task 4: Custom Functional Interface
1. Write your own functional interface ThreeFunction (it takes three arguments and produce result).
2. Implement this with two different lambdas
3. Provide client code with usage of this lambdas

Task 5: Collection to stream
1. Create the following classes:
a. Author with fields:
  String name, short age, List<Book> books
b. Book with fields
  String title, List<Author> authors, int numberOfPages
2. Create two arrays: Author[] authors and Book[] books. Their elements must use elements from the neighboring array.
3. Create a stream of books and then:
     I. check if some/all book(s) have more than 200 pages;
     II. find the books with max and min number of pages;
     III. filter books with only single author;
     IV. sort the books by number of pages/title;
     V. get list of all titles;
     VI. print them using forEach method;
     VII. get distinct list of all authors
4. Use peek method for debugging intermediate streams during execution the previous task.
5. Use parallel stream with subtask #3.
6. Use the Optional type for determining the title of the biggest book of some author.

Task 6. Custom collector

Define a complex class A with a few fields with different types. This class will be an item in collection.
Define a few methods which calculates some stat over the fields of the A class.
Make stream from the collection of A’s instances.
Write custom collector to accumulate the information from elements of your stream.

1.Implement Collector interface
2.Don’t forget the final transformation.

3.Try to use Characteristics for the optimization purpose.

4.Add tests for your collector.
Test your Collector with the parallel streams for significant amount of items in stream.
Look at the Collector interface and read about combine method if something is going wrong.