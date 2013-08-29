package critter;

import java.util.Random;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Critter {
    private final int ID;
    private AtomicIntegerArray code;
    private final int codeLength;
    private final static Random rand = new Random();

    public static int RANDOM_PERCENT = 5;
    public static int MAX_BIT = 99;

    public Critter(int ID, int[] newCode) {
        this.ID = ID;
        //      handlerID = new AtomicInteger(0);
        code = new AtomicIntegerArray(newCode);
        codeLength = newCode.length;

    }

    public int[] getCode() {

        int[] arrayCopy = new int[codeLength];
        for (int ii = 0; ii < codeLength; ii++) {
            arrayCopy[ii] = code.get(ii);
        }

        return arrayCopy;
    }

    public boolean updateCode(int[] oldCode, int[] newCode) {
        for (int ii = 0; ii < codeLength; ii++) {

            int nextBit = rand.nextBoolean() ? oldCode[ii] : newCode[ii];
            if (rand.nextInt(100) < RANDOM_PERCENT) {
                nextBit = rand.nextInt(MAX_BIT);
            }
            if (!code.compareAndSet(ii, oldCode[ii], newCode[ii])) {
                return false;
            }
        }
        return true;
    }


    public static int[] createRandomCode(int length) {
        int[] randomCode = new int[length];
        for (int ii = 0; ii < length; ii++) {
            randomCode[ii] = rand.nextInt(MAX_BIT);
        }
        return randomCode;
    }

}
