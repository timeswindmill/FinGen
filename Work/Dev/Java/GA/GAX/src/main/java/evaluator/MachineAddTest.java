package evaluator;

import machine.Machine;

import java.util.Arrays;

public class MachineAddTest {

    public static final int NUM_TESTS = 8;

    public long[] getRegisters(int testNum) {
        long[] newRegisters = new long[20];
        Arrays.fill(newRegisters, 1);
        switch (testNum) {

            case 0:
                newRegisters[0] = 1;
                newRegisters[1] = 2;
                newRegisters[2] = 2;
                newRegisters[3] = 5;
                break;

            case 1:
                newRegisters[0] = 11;
                newRegisters[1] = 5;
                newRegisters[2] = 102;
                newRegisters[3] = 7;
                break;

            case 2:
                newRegisters[0] = 0;
                newRegisters[1] = -12;
                newRegisters[2] = 22;
                newRegisters[3] = -2;
                break;

            case 3:
                newRegisters[0] = 7;
                newRegisters[1] = 0;
                newRegisters[2] = 19;
                newRegisters[3] = -9;
                break;

            case 4:
                newRegisters[0] = 0;
                newRegisters[1] = 10;
                newRegisters[2] = 19;
                newRegisters[3] = 8;
                break;

            case 5:
                newRegisters[0] = 11;
                newRegisters[1] = 20;
                newRegisters[2] = 12;
                newRegisters[3] = -12;
                break;

            case 6:
                newRegisters[0] = 18;
                newRegisters[1] = 2;
                newRegisters[2] = 11;
                newRegisters[3] = 32;
                break;

            case 7:
                newRegisters[0] = 75;
                newRegisters[1] = 0;
                newRegisters[2] = 145;
                newRegisters[3] = 14;
                break;

        }

        return newRegisters;

    }

    public int calculateFitness(Machine machine, int testNum) {
        double correctAnswer = 0;
        int fitness = 0;
        switch (testNum) {

            case 0:
                correctAnswer = 9;
                break;
            case 1:
                correctAnswer = 114;
                break;
            case 2:
                correctAnswer = 8;
                break;
            case 3:
                correctAnswer = 10;
                break;
            case 4:
                correctAnswer = 37;
                break;
            case 5:
                correctAnswer = 20;
                break;
            case 6:
                correctAnswer = 45;
                break;
            case 7:
                correctAnswer = 159;
                break;

        }

        if (machine.getRegisters().getValue(0) == correctAnswer) {
            fitness++;
        }
        return fitness;
    }


}
