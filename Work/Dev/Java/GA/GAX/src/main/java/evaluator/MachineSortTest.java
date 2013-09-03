package evaluator;

import machine.Machine;
import runtime.Register;

import java.util.Arrays;
import java.util.Random;

public class MachineSortTest {


    public static final int NUM_TESTS = 10;
    public static final Random rand = new Random();


    public long[] getRegisters(int testNum) {
        long[] newRegisters = new long[20];
        Arrays.fill(newRegisters, rand.nextInt(20));
        newRegisters[0] = 13 + testNum;
        newRegisters[1] = 4 + testNum;
        newRegisters[2] = 7 + testNum;
        newRegisters[3] = 1 + testNum;
        return newRegisters;

    }

    public int calculateFitness(Machine machine, int testNum) {
        Register registers = machine.getRegisters();
        int fitness = 0;
        if (registers.getValue(0) == 1 + testNum) {
            fitness++;
        }
        if (registers.getValue(1) == 2 + testNum) {
            fitness++;
        }
        if (registers.getValue(2) == 14 + testNum) {
            fitness++;
        }
        if (registers.getValue(3) == 17 + testNum) {
            fitness++;
        }

        return fitness;
    }


}
