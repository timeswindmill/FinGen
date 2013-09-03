package processor;

import control.RunConfig;
import critter.Critter;
import evaluator.Evaluator;
import log.Log;
import world.Logger;
import world.Population;

import java.util.concurrent.atomic.AtomicBoolean;

public class ProcessRunner implements Runnable {

    private static final Log logger = Logger.INSTANCE.getLogger();


    private int currentIndex;
    private final int ID;
    private final AtomicBoolean runFlag = new AtomicBoolean(true);
    private int numAttempts;
    private final int maxAttempts;


    private final Processor processor;
    private final Population population;

    public ProcessRunner(int ID, Evaluator evaluator, Population population) {
        this.ID = ID;
        currentIndex = ID;
        this.maxAttempts = RunConfig.INSTANCE.getMaxAttempts();
        processor = new Processor(evaluator);
        this.population = population;
    }


    private Critter getNextCritter() {
        currentIndex++;
        Critter newCritter = population.getNextCritter(currentIndex);
        return newCritter;

    }

    private void evaluateNextCritter() {
        Critter critter = getNextCritter();
        logger.logDebug("***************");
        logger.logDebug("Processor " + ID);
        processor.evaluateCritter(critter);
    }

//    public void stopRunner() {
//        runFlag.set(false);
//    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("############\n");
        sb.append("Runner  : " + ID + "\n");
        sb.append("Processor Results" + "\n");
        sb.append(processor.resultsToString());
        sb.append("Num Attempts : " + numAttempts + "\n");
        sb.append("Current Index : " + currentIndex + "\n");
        sb.append("Run Flag : " + runFlag + "\n");

        return sb.toString();


    }

    public Processor getProcessor() {
        return processor;
    }

    @Override
    public void run() {
        logger.logInfo("Starting Runner " + ID);
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
