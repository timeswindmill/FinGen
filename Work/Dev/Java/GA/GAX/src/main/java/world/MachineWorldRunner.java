package world;

import control.RunConfig;
import evaluator.Evaluator;
import evaluator.MachineEvaluator;
import log.FileLogger;
import log.Log;

public enum MachineWorldRunner {

    INSTANCE;

    private Log logger = new FileLogger();

    private final Evaluator evaluator = new MachineEvaluator();

    public void setUpWorld() {
        Population.INSTANCE.createPopulation();
        RunnerPool.INSTANCE.setUpPool(evaluator, RunConfig.INSTANCE.getNumThreads());
    }

    public Log getLogger() {
        return logger;

    }


    public void runWorld() {

        for (int ii = 0; ii < RunnerPool.INSTANCE.getPoolSize(); ii++) {
            Thread thread = new Thread(RunnerPool.INSTANCE.getProcessRunner(ii));
            thread.start();


        }


    }


}
