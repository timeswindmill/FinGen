package world;

import evaluator.Evaluator;
import processor.ProcessRunner;

public enum RunnerPool {


    INSTANCE;

    public static final int NUM_PROCESSORS = 4;
    private final ProcessRunner[] runnerArray = new ProcessRunner[NUM_PROCESSORS];

    public void setUpPool(Evaluator evaluator) {

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
