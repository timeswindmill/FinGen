package world;

import critter.Critter;

public enum Population {

    INSTANCE;


    public static final int POPULATION_SIZE = 1000;
    public static final int DEFAULT_CODE_LENGTH = 10;

    private final Critter[] critterArray = new Critter[POPULATION_SIZE];

//    public Critter getCritter(int index) {
//        return critterArray[index];
//    }

    public void createPopulation() {
        for (int ii = 0; ii < critterArray.length; ii++) {
            int[] newCode = Critter.createRandomCode(DEFAULT_CODE_LENGTH);
            critterArray[ii] = new Critter(ii, newCode);
        }

    }

    public Critter getNextCritter(int critterIndex) {

        return critterArray[critterIndex % POPULATION_SIZE];
    }


}
