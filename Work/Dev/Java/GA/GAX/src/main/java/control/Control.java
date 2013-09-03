package control;

import log.Log;
import log.LogLevel;
import world.Logger;
import world.MachineWorldRunner;

import java.io.IOException;

public class Control {

    private static Log logger = Logger.INSTANCE.getLogger();

    public static void main(String[] args) {

        logger.setLevel(LogLevel.INFO);
        Control control = new Control();

        if (args.length > 0) {

            try {
                RunConfig.INSTANCE.setUpProperties(args[0]);
                logger.logInfo(RunConfig.INSTANCE.toString());
            } catch (IOException e) {
                logger.logError("Error reading properties file " + e);
            }
            control.start();

        } else {
            logger.logError("No properties file specified ");
        }

    }


    public void start() {

        MachineWorldRunner runner = MachineWorldRunner.INSTANCE;
        runner.setUpWorld();
        runner.runWorld();

    }


}
