package world;

import control.RunConfig;
import evaluator.Evaluator;
import evaluator.SimpleEvaluator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public enum SimpleWorldRunner {

    INSTANCE;

    private final Evaluator evaluator = new SimpleEvaluator();

    public void setUpWorld(int maxAttempts) {
        Population population = new Population(RunConfig.INSTANCE.getPopulationSize());
        population.createPopulation();
        RunnerPool.INSTANCE.setUpPool(evaluator, RunConfig.INSTANCE.getNumThreads(), population);
    }


    public void runWorld() {

        ExecutorService taskExecutor = Executors.newFixedThreadPool(RunnerPool.INSTANCE.getPoolSize());
        for (int ii = 0; ii < RunnerPool.INSTANCE.getPoolSize(); ii++) {
            taskExecutor.submit(RunnerPool.INSTANCE.getProcessRunner(ii));
        }
        try {
            taskExecutor.shutdown();
            taskExecutor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.out.print("Error " + e);
        }


    }


}
