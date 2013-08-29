package world;

import critter.Critter;
import processor.ProcessRunner;

public enum SimpleWorld {

    INSTANCE;

    public static final int NUM_PROCESSORS = 1;
    public static final int POPULATION_SIZE = 1000;
    public static final int DEFAULT_CODE_LENGTH = 10;

    Critter[] critterArray = new Critter[POPULATION_SIZE];
    ProcessRunner[] runnerArray = new ProcessRunner[NUM_PROCESSORS];

    public Critter getCritter(int index) {
        return critterArray[index];
    }

    public ProcessRunner[] getRunnerArray() {
        return runnerArray;
    }


    public void setUpWorld() {
        for (int ii = 0; ii < critterArray.length; ii++) {
            int[] newCode = Critter.createRandomCode(DEFAULT_CODE_LENGTH);
            critterArray[ii] = new Critter(ii, newCode);
        }

        for (int ii = 0; ii < NUM_PROCESSORS; ii++) {
            ProcessRunner procRunner = new ProcessRunner(ii * 10);
            runnerArray[ii] = procRunner;
        }


    }

    public void runWorld() {

        for (int ii = 0; ii < NUM_PROCESSORS; ii++) {
            Thread thread = new Thread(runnerArray[ii]);

            thread.start();


        }


    }


}
