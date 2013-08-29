package processor;

import critter.Critter;
import world.SimpleWorld;

import java.util.concurrent.atomic.AtomicBoolean;

public class ProcessRunner implements Runnable {
    private int currentIndex;
    private final int ID;
    private AtomicBoolean runFlag = new AtomicBoolean(false);

    Processor processor;


    public ProcessRunner(int ID) {
        this.ID = ID;
        currentIndex = ID;
    }


    private Critter getNextCritter() {
        currentIndex++;
        currentIndex = currentIndex % SimpleWorld.POPULATION_SIZE;
        return SimpleWorld.INSTANCE.getCritter(currentIndex);
    }

    private void evaluateNextCritter() {
        Critter critter = getNextCritter();
        processor.evaluateCritter(critter);
    }

    public void stopRunner() {
        runFlag.set(false);
    }

    @Override
    public void run() {

        while (runFlag.get()) {
            evaluateNextCritter();
        }
    }
}
