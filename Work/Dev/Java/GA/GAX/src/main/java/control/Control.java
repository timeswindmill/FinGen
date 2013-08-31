package control;

import world.MachineWorldRunner;

public class Control {


    public static void main(String[] args) {

        Control control = new Control();
        control.start();
    }


    public void start() {

        MachineWorldRunner runner = MachineWorldRunner.INSTANCE;
        runner.setUpWorld();
        runner.runWorld();

    }


}
