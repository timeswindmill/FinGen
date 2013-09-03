package evaluator;

import instruction.OpCodes;
import junit.framework.Assert;
import org.junit.Test;

public class MachineEvaluatorTest {

    @Test
    public void testEvaluateFitness() throws Exception {

        {// Test against Add Test Incorrect
            int[] newCodes = {OpCodes.ADD.ordinal(), 2, 3, 0, OpCodes.HALT.ordinal(), 0, 0, 0};

            MachineEvaluator evaluator = new MachineEvaluator();
            Assert.assertNotNull(evaluator);
            int fitness = evaluator.evaluateFitness(newCodes);

            Assert.assertEquals(0, fitness);
        }

        {// Test against Add Test Correct program
            int[] newCodes = {OpCodes.ADD.ordinal(), 2, 3, 0, OpCodes.ADD.ordinal(), 1, 2, 0, OpCodes.MOV.ordinal(), 0, 1, 0, OpCodes.HALT.ordinal(), 1, 2, 0};

            MachineEvaluator evaluator = new MachineEvaluator();
            Assert.assertNotNull(evaluator);
            int fitness = evaluator.evaluateFitness(newCodes);
            Assert.assertEquals(MachineAddTest.NUM_TESTS, fitness);
        }
        {// Test against Add Test  - Partial Correct program
            int[] newCodes = {OpCodes.LOAD.ordinal(), 0, 37, 0, OpCodes.HALT.ordinal(), 0, 0, 0};
            MachineEvaluator evaluator = new MachineEvaluator();
            Assert.assertNotNull(evaluator);
            int fitness = evaluator.evaluateFitness(newCodes);
            Assert.assertEquals(1, fitness);
        }
        {// Test against Infinite loop program
            int[] newCodes = {OpCodes.ADD.ordinal(), 3, 2, 0, OpCodes.MOV.ordinal(), 0, 1, 0, OpCodes.JMP.ordinal(), 1, 2, 0};

            MachineEvaluator evaluator = new MachineEvaluator();
            Assert.assertNotNull(evaluator);
            int fitness = evaluator.evaluateFitness(newCodes);
            Assert.assertEquals(-1 * MachineAddTest.NUM_TESTS, fitness);
        }
        {// Test against Another Correct program
            int[] newCodes = {OpCodes.ADD.ordinal(), 2, 1, 0, OpCodes.ADD.ordinal(), 2, 3, 0, OpCodes.MOV.ordinal(), 0, 2, 0, OpCodes.JMP.ordinal(), 1, 2, 7};

            MachineEvaluator evaluator = new MachineEvaluator();
            Assert.assertNotNull(evaluator);
            int fitness = evaluator.evaluateFitness(newCodes);
            Assert.assertEquals(MachineAddTest.NUM_TESTS, fitness);
        }
        {// Test against Another Correct program
            int[] newCodes = {OpCodes.ADD.ordinal(), 2, 3, 0, OpCodes.NOOP.ordinal(), 1, 2, 0, OpCodes.ADD.ordinal(), 1, 2, 0, OpCodes.MOV.ordinal(), 0, 1, 0};

            MachineEvaluator evaluator = new MachineEvaluator();
            Assert.assertNotNull(evaluator);
            int fitness = evaluator.evaluateFitness(newCodes);
            Assert.assertEquals(MachineAddTest.NUM_TESTS, fitness);
        }

    }

    @Test
    public void testJumps() throws Exception {
        {// Test against Correct program with extra jumps
            int[] newCodes = {
                    OpCodes.JMP.ordinal(), 1, 2, 2,
                    OpCodes.NOOP.ordinal(), 1, 2, 0,
                    OpCodes.ADD.ordinal(), 2, 3, 0,
                    OpCodes.ADD.ordinal(), 1, 2, 0,
                    OpCodes.MOV.ordinal(), 0, 1, 0,
                    OpCodes.JMP.ordinal(), 1, 2, 7,
                    OpCodes.NOOP.ordinal(), 1, 2, 0,
                    OpCodes.NOOP.ordinal(), 1, 2, 0,
            };

            MachineEvaluator evaluator = new MachineEvaluator();
            Assert.assertNotNull(evaluator);
            int fitness = evaluator.evaluateFitness(newCodes);
            Assert.assertEquals(MachineAddTest.NUM_TESTS, fitness);
        }
    }


    @Test
    public void testExamples() throws Exception {
        //       0	JMZ 	2	2	2
        //       1	INC 	8	4	0
        //       2	NOOP 	0	0	0
        //       3	ADD 	0	2	0
        //       4	DIV 	7	3	0
        //       5	ADD 	0	1	0
        //       6	ADD 	2	9	0
        //       7	NOOP 	0	0	0
        int[] newCodes = {8, 2, 2, 2, 4, 8, 4, 6, 0, 1, 9, 4, 2, 0, 2, 3, 7, 7, 3, 1, 2, 0, 1, 9, 2, 2, 9, 5, 0, 6, 0, 8};
        MachineEvaluator evaluator = new MachineEvaluator();
        Assert.assertNotNull(evaluator);
        int fitness = evaluator.evaluateFitness(newCodes);
        Assert.assertEquals(0, (int) fitness);


    }


}

