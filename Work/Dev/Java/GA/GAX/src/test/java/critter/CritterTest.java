package critter;

import junit.framework.Assert;
import org.junit.Test;

public class CritterTest {

    @Test
    public void testCreateCritter() {
        int[] code = {1, 2, 3, 4, 5};
        Critter critter = new Critter(1, code);
        Assert.assertNotNull(critter);
        int[] codeCopy = critter.getCode();
        for (int ii = 0; ii < code.length; ii++) {
            Assert.assertEquals(code[ii], codeCopy[ii]);
        }

    }


    @Test
    public void testUpdateCritter() {
        int[] code1 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        int[] code2 = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        Critter critter = new Critter(1, code1);
        Assert.assertNotNull(critter);

        critter.updateCode(code1, code2);

        boolean different = false;
        // check not all 1
        int[] codeCopy = critter.getCode();
        for (int thisInt : codeCopy) {
            if (thisInt != 1) {
                different = true;
                break;
            }
        }

        Assert.assertTrue(different);
        // now check not all 2
        different = false;
        for (int thisInt : codeCopy) {
            if (thisInt != 2) {
                different = true;
                break;
            }
        }
        Assert.assertTrue(different);


    }


}
