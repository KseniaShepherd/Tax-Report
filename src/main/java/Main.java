import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.LongAdder;

public class Main {
    public static void main(String[] args) {
        List<Long[]> storesProcceds = new ArrayList<>();
        storesProcceds.add(new Long[]{10L, 10L, 10L});
        storesProcceds.add(new Long[]{20L, 20L, 20L});
        storesProcceds.add(new Long[]{10L, 30L, 50L});

        LongAdder proceeds = new LongAdder();

        ExecutorService service = Executors.newFixedThreadPool(3);
        for (Long[] storeProcceds : storesProcceds) {

            Future<?> future = service.submit(new ProceedsCounter(proceeds, storeProcceds));

            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println(proceeds.sum());
    }
}
