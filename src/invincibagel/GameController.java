/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invincibagel;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

/**
 *
 * @author Chris
 */
public class GameController {

    Controller defaultController;
    boolean controllerInitialized;
    Thread listenerThread;
    int buttonPressed = ButtonPressed.NO_BUTTON;
    final static double D_PAD_LEFT = 1.0;
    final static double D_PAD_RIGHT = 0.5;
    final static double D_PAD_UP = 0.25;
    final static double D_PAD_DOWN = 0.75;

    public interface ButtonPressed {

        final static int NO_BUTTON = 99;

        final static int A_BUTTON = 1;
        final static int B_BUTTON = 2;
        final static int X_BUTTON = 3;
        final static int Y_BUTTON = 0;
        
        //final static int 
                //x is 0, o is 1 okay so can we try again t I want to update this online
                        

        final static int SELECT_BUTTON = 8;
        final static int START_BUTTON = 9;

        final static int LEFT_TRIGGER_PRESSED = 6;
        final static int RIGHT_TRIGGER_PRESSED = 7;
        final static int LEFT_BUTTON = 4;
        final static int RIGHT_BUTTON = 5;

        final static int LEFT_ANALOG_PRESSED = 10;
        final static int RIGHT_ANALOG_PRESSED = 11;

        final static int D_PAD_LEFT = 50;
        final static int D_PAD_RIGHT = 51;
        final static int D_PAD_UP = 52;
        final static int D_PAD_DOWN = 53;

        final static int ANALOG_TRUE_LEFT = 0;
        final static int ANALOG_TRUE_RIGHT = 100;
        final static int ANALOG_TRUE_UP = 0;
        final static int ANALOG_TRUE_DOWN = 100;

    }
    

    public GameController() {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        for (int i = 0; i < controllers.length; i++) {
            Controller controller = controllers[i];
            System.out.println(controller.getName());
            if (controller.getType() == Controller.Type.GAMEPAD || controller.getName().contains("PLAYSTATION")) {
                System.out.println("we have a ps3 controller, right bradley? Yes");
                defaultController = controller;
                System.out.println(defaultController.getType());
                controllerInitialized = true;

            }
            

        }
        if (defaultController == null) {
            controllerInitialized = false;
        }
    }

    public void startControllerListener() {
        listenerThread = new Thread(new ListenRunner());
        listenerThread.start();
    }

    public void showControllerData() {

        if (!defaultController.poll()) {
            JOptionPane.showMessageDialog(null, "Controller Disconnected");
            return;
        }
        buttonPressed = ButtonPressed.NO_BUTTON;

        int xAxisPercentage = 0;
        int yAxisPercentage = 0;

        Component[] components = defaultController.getComponents();
        for (int i = 0; i < components.length; i++) {
            Component component = components[i];
            //System.out.println(component.getName() + "this will tell us what inputs the controller has");
            //I was pushing x and o and they showed up, but i did not see the rest of the buttons oh
            Component.Identifier componentIdentifier = component.getIdentifier();
            if (componentIdentifier.getName().matches("^[0-18]*$")    ) {

                //System.out.println("we got a button" + component.getName());
                boolean isItPressed = true;
                if (component.getPollData() == 0.0f) {
                    isItPressed = false;
                    
                }
                if (isItPressed) {
                    String name = componentIdentifier.getName();
                    System.out.println("it's working! " + component.getName());
                    //we'll try again
                    //buttonPressed = Integer.parseInt(name);
//int number = Integer.parseInt(name);

                        //System.out.println(componentIdentifier.getName());
//                        String buttonIndex;
//                        buttonIndex = component.getIdentifier().toString();
//                        System.out.println(buttonIndex + ",");
                    //continue;
                }

            }
            if (componentIdentifier == Component.Identifier.Axis.POV) {
                float hatSwitchPosition = component.getPollData();
                //System.out.println("Hat Switch Position: " + hatSwitchPosition);
                if (hatSwitchPosition == D_PAD_LEFT) {
                    buttonPressed = ButtonPressed.D_PAD_LEFT;
                    //System.out.println("D left");
                }
                if (hatSwitchPosition == D_PAD_RIGHT) {
                    buttonPressed = ButtonPressed.D_PAD_RIGHT;
                    //System.out.println("D right");
                }
                if(hatSwitchPosition == D_PAD_DOWN){
                    buttonPressed = ButtonPressed.D_PAD_DOWN;
                }
                if(hatSwitchPosition == D_PAD_UP){
                    buttonPressed = ButtonPressed.D_PAD_UP;
                }
            }

            if (component.isAnalog()) {
                float axisValue = component.getPollData();
                int axisValueInPercentage = getAxisValueInPercentage(axisValue);

                if (componentIdentifier == Component.Identifier.Axis.X) {
                    xAxisPercentage = axisValueInPercentage;
                }
                if (componentIdentifier == Component.Identifier.Axis.Y) {
                    yAxisPercentage = axisValueInPercentage;
                }

                //System.out.println(componentIdentifier.getName() + ": " + xAxisPercentage + ", " + yAxisPercentage);
            }
        }
        try {
            Thread.sleep(25);
        } catch (InterruptedException ex) {
            Logger.getLogger(InvinciBagel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private int getAxisValueInPercentage(float axisValue) {
        return (int) (((2 - (1 - axisValue)) * 100) / 2);
    }

    public int getButtonPressed() {

        return buttonPressed;
    }

    public class ListenRunner implements Runnable {

        @Override
        public void run() {
            while (true) {
                showControllerData();
                System.out.println("running runnable");
            }

        }
    }
}
