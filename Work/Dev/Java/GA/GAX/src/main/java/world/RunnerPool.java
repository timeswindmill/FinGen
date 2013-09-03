package world;

import evaluator.Evaluator;
import processor.ProcessRunner;

public enum RunnerPool {


    INSTANCE;

    private ProcessRunner[] runnerArray;

    public void setUpPool(Evaluator evaluator, int numberThreads, Population population) {
        runnerArray = new ProcessRunner[numberThreads];
        for (int ii = 0; ii < numberThreads; ii++) {
            ProcessRunner procRunner = new ProcessRunner(ii * 10, evaluator, population);
            runnerArray[ii] = procRunner;
        }

    }

    public int getPoolSize() {
        return runnerArray.length;
    }

    public ProcessRunner getProcessRunner(int index) {
        return runnerArray[index];
    }


}
