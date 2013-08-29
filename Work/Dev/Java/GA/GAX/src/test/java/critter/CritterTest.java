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


}
