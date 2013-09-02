package processor;

import control.RunConfig;
import critter.Critter;
import evaluator.Evaluator;
import log.Log;
import world.Logger;
import world.Population;

import java.util.concurrent.atomic.AtomicBoolean;

public class ProcessRunner implements Runnable {

    private static Log logger = Logger.INSTANCE.getLogger();


    private int currentIndex;
    private final int ID;
    private AtomicBoolean runFlag = new AtomicBoolean(true);
    private int numAttempts;
    private final int maxAttempts;


    Processor processor;


    public ProcessRunner(int ID, Evaluator evaluator) {
        this.ID = ID;
        currentIndex = ID;
        this.maxAttempts = RunConfig.INSTANCE.getMaxAttempts();
        processor = new Processor(evaluator);
    }


    private Critter getNextCritter() {
        currentIndex++;
        Critter newCritter = Population.INSTANCE.getNextCritter(currentIndex);
        return newCritter;

    }

    private void evaluateNextCritter() {
        Critter critter = getNextCritter();
        logger.logDebug("***************");
        logger.logDebug("Processor " + ID);
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
            if (numAttempts > maxAttempts) {
                break;
            }

        }
        logger.logInfo(this.toString());


    }
}
