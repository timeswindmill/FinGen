package world;

import log.Log;
import log.LogLevel;
import org.junit.Test;

public class SimpleWorldTest {
    @Test
    public void testSetUpWorld() throws Exception {

        SimpleWorldRunner simpleWorld = SimpleWorldRunner.INSTANCE;
        simpleWorld.setUpWorld(9999);


    }

    @Test
    public void testRunWorld() throws Exception {
        SimpleWorldRunner simpleWorld = SimpleWorldRunner.INSTANCE;
        simpleWorld.setUpWorld(9999);
        Log logger = Logger.INSTANCE.getLogger();
        logger.setLevel(LogLevel.INFO);
        simpleWorld.runWorld();


    }


}
