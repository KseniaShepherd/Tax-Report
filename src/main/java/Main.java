import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.LongAdder;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Long[]> storesProcceds = new ArrayList<>();
        storesProcceds.add(new Long[]{10L, 10L, 10L});
        storesProcceds.add(new Long[]{20L, 20L, 20L});
        storesProcceds.add(new Long[]{10L, 30L, 50L});

        LongAdder proceeds = new LongAdder();

        ExecutorService service = Executors.newFixedThreadPool(3);
        List<Future<?>> futures = new ArrayList<>();

        for (Long[] storeProcceds : storesProcceds) {
            Future<?> future = service.submit(new ProceedsCounter(proceeds, storeProcceds));
            futures.add(future);
        }

        for (Future<?> future : futures) {
            future.get();
        }

        System.out.println(proceeds.sum());
        service.shutdown();
    }
}
