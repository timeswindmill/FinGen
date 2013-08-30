package world;

import evaluator.SimpleEvaluator;
import processor.ProcessRunner;

public enum RunnerPool {


    INSTANCE;

    public static final int NUM_PROCESSORS = 1;
    private final ProcessRunner[] runnerArray = new ProcessRunner[NUM_PROCESSORS];
    private final SimpleEvaluator evaluator = new SimpleEvaluator();


    public void setUpPool() {

        for (int ii = 0; ii < NUM_PROCESSORS; ii++) {
            ProcessRunner procRunner = new ProcessRunner(ii * 10, evaluator);
            runnerArray[ii] = procRunner;
        }

    }

    public int getPoolSize() {
        return NUM_PROCESSORS;
    }

    public ProcessRunner getProcessRunner(int index) {
        return runnerArray[index];
    }


}
