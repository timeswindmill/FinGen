package evaluator;

import instruction.Program;
import machine.Machine;

public class MachineEvaluator implements Evaluator {


    private Program createProgram(int[] bits) {

        Program newProg = Program.createProgram(bits);
        return newProg;

    }


    @Override
    public int evaluateFitness(int[] code) {

        int totalFitness = 0;
        MachineAddTest machineTest = new MachineAddTest();
        Machine machine = new Machine();
        Program thisProgram = createProgram(code);
        machine.setProgram(thisProgram);
        // initialise registers
        for (int ii = 0; ii < machineTest.NUM_TESTS; ii++) {
            long[] registers = machineTest.getRegisters(ii);
            machine.loadRegisters(registers);
            machine.runProgram();

            if (machine.isLoopDetected()) {
                totalFitness--;
            } else {
                int thisFitness = machineTest.calculateFitness(machine, ii);
                totalFitness += thisFitness;
            }
        }
        return totalFitness;
    }


}
