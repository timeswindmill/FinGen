package world;

public enum SimpleWorldRunner {

    INSTANCE;


    public void setUpWorld() {
        Population.INSTANCE.createPopulation();
        RunnerPool.INSTANCE.setUpPool();

    }

    public void runWorld() {

        for (int ii = 0; ii < RunnerPool.INSTANCE.getPoolSize(); ii++) {
            Thread thread = new Thread(RunnerPool.INSTANCE.getProcessRunner(ii));

            thread.start();


        }


    }


}
