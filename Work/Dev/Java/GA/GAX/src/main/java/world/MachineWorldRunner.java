package world;

import control.RunConfig;
import control.results.ResultsFile;
import control.results.ResultsLine;
import evaluator.Evaluator;
import evaluator.MachineEvaluator;
import log.Log;
import processor.Processor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public enum MachineWorldRunner {

    INSTANCE;

    private final Log logger = Logger.INSTANCE.getLogger();

    private final Evaluator evaluator = new MachineEvaluator();

    public void setUpWorld() {
        Population population = new Population(RunConfig.INSTANCE.getPopulationSize());

        // try to set up from results file
        // read results file
        String fileName = RunConfig.INSTANCE.getResultsFileName();
        List<ResultsLine> resultsLines = null;
        if (fileName != null) {
            resultsLines = ResultsFile.readResults(fileName);
        }
        if (resultsLines.size() > 0) {
            population.createPopulation(resultsLines);
            logger.logInfo("Loaded Population from results file");
        } else {
            population.createPopulation();
            logger.logInfo("Created Random Population");
        }
        logger.logInfo("Population size : " + population.getPopulationSize());
        RunnerPool.INSTANCE.setUpPool(evaluator, RunConfig.INSTANCE.getNumThreads(), population);
    }


    public void runWorld() {
        logger.logInfo("Running World");
        int numberProcessors = RunConfig.INSTANCE.getNumThreads();
        ExecutorService taskExecutor = Executors.newFixedThreadPool(numberProcessors);
        for (int ii = 0; ii < numberProcessors; ii++) {
            taskExecutor.submit(RunnerPool.INSTANCE.getProcessRunner(ii));
        }
        try {
            taskExecutor.shutdown();
            taskExecutor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.out.print("Error " + e);
        }
        // save the results to file
        String saveFile = RunConfig.INSTANCE.getResultsFileName();
        Processor[] procs = new Processor[numberProcessors];
        for (int ii = 0; ii < numberProcessors; ii++) {
            procs[ii] = RunnerPool.INSTANCE.getProcessRunner(ii).getProcessor();
        }
        ResultsFile.saveResults(procs, saveFile);

    }


}
