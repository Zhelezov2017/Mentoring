Task 1. Das Experiment

Create HashMap<Integer, Integer>. The first thread adds elements into the map, the other go along the given map and sum the values. 
Threads should work before catching ConcurrentModificationException.

Try to fix the problem with ConcurrentHashMap and Collections.synchronizedMap. 
What has happened after simple Map implementation exchanging? How it can be fixed in code?

Try to write your custom ThreadSafeMap with synchronization and without. 
Run your samples with different versions of Java (6, 8, and 10, 11) and measure the performance. 
Provide a simple report.

Task 2.Race condition
Create three threads:

· 1st thread is infinitely writing random number to the collection

· 2nd thread is printing sum of the numbers in the collection

· 3rd is printing square root of sum of squares of all numbers in the collection

Make these calculations thread-safe using synchronization block and avoid the possible race condition.

Task 3. «Where’s your bus, dude? »

Implement message bus using Producer-Consumer pattern.

1. Implement asynchronous message bus. Do not use queue implementations from java.util.concurrent.
2. Implement producer, which will generate and post randomly messages to the queue
3. Implement consumer, which will consume messages on specific topic and log to the console message payload

Optional: 4. Application should create several consumers and producers that run in parallel.

Task 4. 
Create simple object pool with support for multithreaded environment. 
No any extra inheritance, polymorphism or generics needed here, just implementation of simple class: