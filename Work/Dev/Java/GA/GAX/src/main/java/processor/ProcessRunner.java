package processor;

import critter.Critter;
import evaluator.SimpleEvaluator;
import world.Population;

import java.util.concurrent.atomic.AtomicBoolean;

public class ProcessRunner implements Runnable {
    private int currentIndex;
    private final int ID;
    private AtomicBoolean runFlag = new AtomicBoolean(true);
    private int numAttempts;

    public static final int MAX_ATTEMPTS = 5000;

    Processor processor;


    public ProcessRunner(int ID, SimpleEvaluator evaluator) {
        this.ID = ID;
        currentIndex = ID;
        processor = new Processor(evaluator);
    }


    private Critter getNextCritter() {
        currentIndex++;
        Critter newCritter = Population.INSTANCE.getNextCritter(currentIndex);
        return newCritter;

    }

    private void evaluateNextCritter() {
        Critter critter = getNextCritter();
        processor.evaluateCritter(critter);
    }

    public void stopRunner() {
        runFlag.set(false);
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Runner  : " + ID + "\n");
        sb.append("Processor Results" + "\n");
        sb.append(processor.resultsToString());
        sb.append("Num Attempts : " + numAttempts + "\n");
        sb.append("Current Index : " + currentIndex + "\n");
        sb.append("Run Flag : " + runFlag + "\n");

        return sb.toString();


    }

    @Override
    public void run() {

        while (runFlag.get()) {
            evaluateNextCritter();
            numAttempts++;
            if (numAttempts > MAX_ATTEMPTS) {
                break;
            }

        }
        System.out.println(this);
    }
}
