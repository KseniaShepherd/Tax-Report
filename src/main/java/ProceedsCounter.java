import java.util.concurrent.atomic.LongAdder;

public class ProceedsCounter implements Runnable {
    private LongAdder totalProceeds;
    private Long[] storeProceeds;

    public ProceedsCounter(LongAdder totalProceeds, Long[] storeProceeds) {
        this.totalProceeds = totalProceeds;
        this.storeProceeds = storeProceeds;
    }

    @Override
    public void run() {
        for (Long proceed : storeProceeds) {
            totalProceeds.add(proceed);
        }
    }
}
