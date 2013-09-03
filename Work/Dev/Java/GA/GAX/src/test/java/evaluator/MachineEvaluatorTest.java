package evaluator;

import instruction.OpCodes;
import org.junit.Assert;
import org.junit.Test;

public class MachineEvaluatorTest {


    @Test
    public void testEvaluateFitness() throws Exception {

        {// Test against Add Test Incorrect
            int[] newCodes = {
                    OpCodes.LOAD.ordinal(), 0, 78, 0,
                    OpCodes.LOAD.ordinal(), 1, 37, 0,
                    OpCodes.LOAD.ordinal(), 2, 116, 0,
                    OpCodes.LOAD.ordinal(), 3, 65, 0,
                    OpCodes.HALT.ordinal(), 0, 0, 0
            };

            MachineEvaluator evaluator = new MachineEvaluator();
            Assert.assertNotNull(evaluator);
            int fitness = evaluator.evaluateFitness(newCodes);

            Assert.assertEquals(0, fitness);
        }

//            {// Test against PartialCorrect
//                int[] newCodes = {
//                        OpCodes.LOAD.ordinal(), 0, 0, 0,
//                        OpCodes.LOAD.ordinal(), 1, 7, 0,
//                        OpCodes.LOAD.ordinal(), 2, 6, 0,
//                        OpCodes.LOAD.ordinal(), 3, 5, 0,
//                        OpCodes.HALT.ordinal(), 0, 0, 0
//                };
//
//                MachineEvaluator evaluator = new MachineEvaluator();
//                Assert.assertNotNull(evaluator);
//                int fitness = evaluator.evaluateFitness(newCodes);
//
//                Assert.assertEquals(MachineSortTest.NUM_TESTS, fitness);
//            }
//            {// Test against Correct
//                int[] newCodes = {
//                        OpCodes.LOAD.ordinal(), 0, 0, 0,
//                        OpCodes.LOAD.ordinal(), 1, 1, 0,
//                        OpCodes.LOAD.ordinal(), 2, 2, 0,
//                        OpCodes.LOAD.ordinal(), 3, 3, 0,
//                        OpCodes.HALT.ordinal(), 0, 0, 0
//                };
//
//                MachineEvaluator evaluator = new MachineEvaluator();
//                Assert.assertNotNull(evaluator);
//                int fitness = evaluator.evaluateFitness(newCodes);
//
//                Assert.assertEquals(MachineSortTest.NUM_TESTS*4, fitness);
//            }


//            {// Test Correct
//                int[] newCodes = {
//                        OpCodes.CMP.ordinal(), 0, 1, 0,
//                        OpCodes.CMP.ordinal(), 0, 2, 0,
//                        OpCodes.CMP.ordinal(), 0, 3, 0,
//                        OpCodes.HALT.ordinal(), 0, 0, 0
//
//
//                };
//
//                MachineEvaluator evaluator = new MachineEvaluator();
//                Assert.assertNotNull(evaluator);
//                int fitness = evaluator.evaluateFitness(newCodes);
//                Assert.assertEquals(MachineSortTest.NUM_TESTS, fitness);
//            }


    }


//    @Test
//    public void testJumps() throws Exception {
//        {// Test against Correct program with extra jumps
//            int[] newCodes = {
//                    OpCodes.JMP.ordinal(), 1, 2, 2,
//                    OpCodes.NOOP.ordinal(), 1, 2, 0,
//                    OpCodes.ADD.ordinal(), 2, 3, 0,
//                    OpCodes.ADD.ordinal(), 1, 2, 0,
//                    OpCodes.MOV.ordinal(), 0, 1, 0,
//                    OpCodes.JMP.ordinal(), 1, 2, 7,
//                    OpCodes.NOOP.ordinal(), 1, 2, 0,
//                    OpCodes.NOOP.ordinal(), 1, 2, 0,
//            };
//
//            MachineEvaluator evaluator = new MachineEvaluator();
//            Assert.assertNotNull(evaluator);
//            int fitness = evaluator.evaluateFitness(newCodes);
//            Assert.assertEquals(MachineAddTest.NUM_TESTS, fitness);
//        }
//    }
//
//
//    @Test
//    public void testExamples() throws Exception {
//        //       0	JMZ 	2	2	2
//        //       1	INC 	8	4	0
//        //       2	NOOP 	0	0	0
//        //       3	ADD 	0	2	0
//        //       4	DIV 	7	3	0
//        //       5	ADD 	0	1	0
//        //       6	ADD 	2	9	0
//        //       7	NOOP 	0	0	0
//        int[] newCodes = {8, 2, 2, 2, 4, 8, 4, 6, 0, 1, 9, 4, 2, 0, 2, 3, 7, 7, 3, 1, 2, 0, 1, 9, 2, 2, 9, 5, 0, 6, 0, 8};
//        MachineEvaluator evaluator = new MachineEvaluator();
//        Assert.assertNotNull(evaluator);
//        int fitness = evaluator.evaluateFitness(newCodes);
//        Assert.assertEquals(0, (int) fitness);
//
//
//    }
//

}

