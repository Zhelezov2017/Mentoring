import com.epam.java.training.concurrency.task1.model.ThreadSafeMapWithSynchronization;
import com.epam.java.training.concurrency.task1.model.ThreadSafeMapWithoutSynchronization;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by Vladislav Zhelezov.
 */
public class TestBenchmark {

    @Test
    public void givenMaps_whenGetPut500KTimesHashTable()
            throws Exception {
        Map<String, Object> hashtable = new Hashtable<>();
        //Time work for 500k run
        long hashtableAvgRuntime = timeElapseForGetPut(hashtable);
        System.out.println("Hashtable time: " + hashtableAvgRuntime);

    }

    @Test
    public void givenMaps_whenGetPut500KTimesSynchronizedMap() throws InterruptedException {
        Map<String, Object> synchronizedHashMap = Collections.synchronizedMap(new HashMap<>());
        long syncHashMapAvgRuntime = timeElapseForGetPut(synchronizedHashMap);
        System.out.println("SynchronizedHashMap time: " + syncHashMapAvgRuntime);

    }


    @Test
    public void givenMaps_whenGetPut500KTimesConcurrentMap() throws InterruptedException{
        Map<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        long concurrentHashMapAvgRuntime = timeElapseForGetPut(concurrentHashMap);
        System.out.println("ConcurrentHashMap time: " + concurrentHashMapAvgRuntime);

    }


    @Test
    public void givenMaps_whenGetPut500KTimesThreadSafeMapWithoutSynchronization() throws InterruptedException {
        ThreadSafeMapWithoutSynchronization threadSafeMapWithoutSynchronization
                = new ThreadSafeMapWithoutSynchronization<String, Object>(1000000);
        long threadSafeMapWithSynchronizationAvgRuntime  = timeElapseForGetPut(threadSafeMapWithoutSynchronization);
        System.out.println("ThreadSafeMapWithoutSynchronization time: " + threadSafeMapWithSynchronizationAvgRuntime);
    }


    @Test
    public void givenMaps_whenGetPut500KTimesThreadSafeMapWithSynchronization() throws InterruptedException {
        ThreadSafeMapWithSynchronization threadSafeMapWithSynchronization
                = new ThreadSafeMapWithSynchronization<>(new HashMap<>());
        long concurrentHashMapAvgRuntimeAvgRuntime = timeElapseForGetPut(threadSafeMapWithSynchronization);
        System.out.println("ThreadSafeMapWithSynchronization time: " + concurrentHashMapAvgRuntimeAvgRuntime);
    }

    private long timeElapseForGetPut(Map<String, Object> map)
            throws InterruptedException {
        ExecutorService executorService =
                Executors.newFixedThreadPool(4);
        long startTime = System.nanoTime();
        for (int i = 0; i < 4; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 500_000; j++) {
                    int value = ThreadLocalRandom
                            .current()
                            .nextInt(10000);
                    String key = String.valueOf(value);
                    map.put(key, value);
                    map.get(key);
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        return (System.nanoTime() - startTime) / 500_000;
    }
}
