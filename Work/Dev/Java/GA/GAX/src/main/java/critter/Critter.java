package critter;

import control.RunConfig;
import control.results.ResultsLine;
import log.Log;
import world.Logger;

import java.util.Random;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Critter {
    private final int ID;
    private final AtomicIntegerArray code;
    private final static Random rand = new Random();

    private final Log logger = Logger.INSTANCE.getLogger();


    public Critter(int ID, int[] newCode) {
        this.ID = ID;
        code = new AtomicIntegerArray(newCode);
    }

    public Critter(int ID, ResultsLine line) {
        this.ID = ID;
        int[] bits = line.getBits();
        code = new AtomicIntegerArray(bits);
    }


    public int getID() {
        return ID;
    }

    public int[] getCode() {

        int[] arrayCopy = new int[code.length()];
        for (int ii = 0; ii < code.length(); ii++) {
            arrayCopy[ii] = code.get(ii);
        }

        return arrayCopy;
    }

    public boolean updateCode(int[] oldCode, int[] newCode) {
        if ((oldCode == null) || (newCode == null)) {
            logger.logError("New Code is " + newCode);
            logger.logError("Old Code is " + oldCode);
            logger.logError("Critter is " + ID);

        }
        int randomPercent = RunConfig.INSTANCE.getMutatePercent();
        int maxBit = RunConfig.INSTANCE.getMaxDnaBit();
        for (int ii = 0; ii < code.length(); ii++) {

            int nextBit = rand.nextBoolean() ? oldCode[ii] : newCode[ii];
            if (rand.nextInt(100) < randomPercent) {
                nextBit = rand.nextInt(maxBit);
            }
            if (!code.compareAndSet(ii, oldCode[ii], nextBit)) {
                return false;
            }
        }
        return true;
    }


    public static int[] createRandomCode(int length) {
        int maxBit = RunConfig.INSTANCE.getMaxDnaBit();
        int[] randomCode = new int[length];
        for (int ii = 0; ii < length; ii++) {
            randomCode[ii] = rand.nextInt(maxBit);
        }
        return randomCode;
    }

}
