package control;

import world.MachineWorldRunner;

import java.io.IOException;

public class Control {


    public static void main(String[] args) {

        Control control = new Control();

        if (args.length > 0) {

            try {
                RunConfig.INSTANCE.setUpProperties(args[0]);
            } catch (IOException e) {
                System.out.println("Error reading properties file " + e);
            }
            control.start();

        } else {
            System.out.println("No properties file specified ");
        }

    }


    public void start() {

        MachineWorldRunner runner = MachineWorldRunner.INSTANCE;
        runner.setUpWorld();
        runner.runWorld();

    }


}
