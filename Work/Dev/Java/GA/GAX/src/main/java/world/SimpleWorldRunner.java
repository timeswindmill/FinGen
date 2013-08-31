package world;

import evaluator.Evaluator;
import evaluator.SimpleEvaluator;

public enum SimpleWorldRunner {

    INSTANCE;

    private final Evaluator evaluator = new SimpleEvaluator();

    public void setUpWorld() {
        Population.INSTANCE.createPopulation();
        RunnerPool.INSTANCE.setUpPool(evaluator);
    }


    public void runWorld() {

        for (int ii = 0; ii < RunnerPool.INSTANCE.getPoolSize(); ii++) {
            Thread thread = new Thread(RunnerPool.INSTANCE.getProcessRunner(ii));
            thread.start();


        }


    }


}
