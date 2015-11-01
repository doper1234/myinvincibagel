/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invincibagel;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static invincibagel.InvinciBagel.iBagel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chris
 */
public class InvinciBagel extends Application {
//    set to platform fullscreen?
//    static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//    static final double WIDTH = screenSize.getWidth();
//    static final double HEIGHT = screenSize.getHeight();

    static final double WIDTH = 640, HEIGHT = 400;
    static boolean up, down, left, right;
    static StackPane root;
    static HBox buttonContainer;
    static Bagel iBagel;
    Scene scene;
    Image splashScreen, instructionLayer, legalLayer, scoresLayer;
    Image iB0, iB1, iB2, iB3, iB4, iB5, iB6, iB7, iB8;
    ImageView splashScreenBackplate, splashScreenTextArea;
    Button gameButton, helpButton, scoreButton, legalButton, exitButton;
    Insets buttonContainerPadding;
    GamePlayLoop gamePlayLoop;
    CastingDirector castDirector;

    Thread t;
    GameController gameController;
    //ListenRunner lR;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("InvinciBagel");
        root = new StackPane();
        scene = new Scene(root, WIDTH, HEIGHT, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
        createSceneEventHandling();
        loadImageAssets();
        createGameActors();
        addGameActorNodes();
        createCastingDirection();
        createSplashScreenNodes();
        addNodesToStackPane();
        createStartGameLoop();

        gameController = new GameController();
        //gameController.startControllerListener();

        t = new Thread(new ControllerListener());
        t.start();
        //gameController.showControllerData();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public class GamePlayLoop extends AnimationTimer {

        Pos location;

        @Override
        public void handle(long now) {
            iBagel.update();
        }

        @Override
        public void start() {
            super.start();

        }

        @Override
        public void stop() {
            super.stop();
        }

    }

    private void createSceneEventHandling() {
        scene.setOnKeyPressed((KeyEvent event) -> {

            System.out.println(event.getCode());
            switch (event.getCode()) {
                case UP:
                    up = true;
                    break;
                case DOWN:
                    down = true;
                    break;
                case LEFT:
                    left = true;
                    break;
                case RIGHT:
                    right = true;
                    break;
                case W:
                    up = true;
                    break;
                case S:
                    down = true;
                    break;
                case A:
                    left = true;
                    break;
                case D:
                    right = true;
                    break;

            }

        });

        scene.setOnKeyReleased((KeyEvent event) -> {

            System.out.println(event.getCode());
            switch (event.getCode()) {
                case UP:
                    up = false;
                    break;
                case DOWN:
                    down = false;
                    break;
                case LEFT:
                    left = false;
                    break;
                case RIGHT:
                    right = false;
                    break;

                case W:
                    up = false;
                    break;
                case S:
                    down = false;
                    break;
                case A:
                    left = false;
                    break;
                case D:
                    right = false;
                    break;

            }

        });
    }

    private void loadImageAssets() {
        splashScreen = new Image("/hoffivsplash.png", 640, 400, true, false, true);
        instructionLayer = new Image("/invincibagelinstruct.png", 640, 400, true, false, true);
        legalLayer = new Image("/hoffivcreds.png", 640, 400, true, false, true);
        scoresLayer = new Image("/invincibagelscores.png", 640, 400, true, false, true);
        iB0 = new Image("/sprite0.png", 81, 81, true, false, true);
        iB1 = new Image("/sprite1.png", 81, 81, true, false, true);
        iB2 = new Image("/sprite2.png", 81, 81, true, false, true);
        iB3 = new Image("/sprite3.png", 81, 81, true, false, true);
        iB4 = new Image("/sprite4.png", 81, 81, true, false, true);
        iB5 = new Image("/sprite5.png", 81, 81, true, false, true);
        iB6 = new Image("/sprite6.png", 81, 81, true, false, true);
        iB7 = new Image("/sprite7.png", 81, 81, true, false, true);
        iB8 = new Image("/sprite8.png", 81, 81, true, false, true);

    }

    private void createGameActors() {
        iBagel = new Bagel("M150 0 L75 500 L225 200 Z", 0, 0, iB0, iB1, iB2, iB3, iB4, iB5, iB6, iB7, iB8);
    }

    private void addGameActorNodes() {
        rga(iBagel.spriteFrame);

    }

    private void rga(Node n) {
        root.getChildren().add(n);
    }

    private void createCastingDirection() {
        castDirector = new CastingDirector();
        castDirector.addCurrentCast(iBagel);
    }

    private void createSplashScreenNodes() {

        buttonContainer = new HBox(12);
        buttonContainer.setAlignment(Pos.BOTTOM_LEFT);
        buttonContainerPadding = new Insets(0, 0, 10, 16);
        buttonContainer.setPadding(buttonContainerPadding);
        gameButton = new Button();
        gameButton.setText("PLAY GAME");
        gameButton.setOnAction((ActionEvent) -> {
            splashScreenBackplate.setVisible(false);
            splashScreenTextArea.setVisible(false);
        });

        helpButton = new Button();
        helpButton.setText("INSTRUCTIONS");
        helpButton.setOnAction((ActionEvent) -> {
            splashScreenBackplate.setVisible(true);
            splashScreenTextArea.setVisible(true);
            splashScreenTextArea.setImage(instructionLayer);
        });
        scoreButton = new Button();
        scoreButton.setText("HIGH SCORES");
        scoreButton.setOnAction((ActionEvent) -> {
            splashScreenBackplate.setVisible(true);
            splashScreenTextArea.setVisible(true);
            splashScreenTextArea.setImage(scoresLayer);
        });
        legalButton = new Button();
        legalButton.setText("LEGAL & CREDITS");
        legalButton.setOnAction((ActionEvent) -> {
            splashScreenBackplate.setVisible(true);
            splashScreenTextArea.setVisible(true);
            splashScreenTextArea.setImage(legalLayer);
        });
        exitButton = new Button();
        exitButton.setText("EXIT GAME");
        exitButton.setOnAction((ActionEvent event) -> {
            int exit = JOptionPane.showConfirmDialog(null, "Would you like to exit?", "Quit?", JOptionPane.YES_NO_OPTION);
            if (exit == JOptionPane.YES_OPTION) {
                try {
                    System.exit(0);
                    this.stop();
                } catch (Exception ex) {
                    System.out.println(ex.getClass());
                    Logger.getLogger(InvinciBagel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        buttonContainer.getChildren().addAll(gameButton, helpButton, scoreButton, legalButton, exitButton);
        splashScreenBackplate = new ImageView();
        splashScreenBackplate.setImage(splashScreen);
        splashScreenTextArea = new ImageView();
        splashScreenTextArea.setImage(instructionLayer);

    }

    private void addNodesToStackPane() {
        root.getChildren().add(splashScreenBackplate);
        root.getChildren().add(splashScreenTextArea);
        root.getChildren().add(buttonContainer);
    }

    private void createStartGameLoop() {
        gamePlayLoop = new GamePlayLoop();
        gamePlayLoop.start();
    }

    public class ControllerListener implements Runnable {

        @Override
        public void run() {
            while (true) {
                gameController.showControllerData();
                //if (gameController.getButtonPressed() != GameController.ButtonPressed.NO_BUTTON) {
                gameButtonPressedHandler(gameController.buttonPressed);
                //}

            }
        }

    }

    public void gameButtonPressedHandler(int buttonPressed) {
        buttonPressed = gameController.getButtonPressed();
        switch (buttonPressed) {

            case GameController.ButtonPressed.D_PAD_LEFT:
                System.out.println("GAME D LEFT");
                left = true;
                break;
            case GameController.ButtonPressed.D_PAD_RIGHT:
                System.out.println("GAME D RIGHT");
                right = true;
                break;
            case GameController.ButtonPressed.D_PAD_UP:
                System.out.println("GAME D UP");
                up = true;
                break;
            case GameController.ButtonPressed.D_PAD_DOWN:
                System.out.println("GAME D DOWN");
                down = true;
                break;

            case GameController.ButtonPressed.A_BUTTON:
                System.out.println("GAME A PRESSED");
                break;
            case GameController.ButtonPressed.B_BUTTON:
                System.out.println("GAME B PRESSED");
                break;
            case GameController.ButtonPressed.X_BUTTON:
                System.out.println("GAME X PRESSED");
                break;
            case GameController.ButtonPressed.Y_BUTTON:
                System.out.println("GAME Y PRESSED");
                break;

            case GameController.ButtonPressed.START_BUTTON:
                System.out.println("START PRESSED");
                JOptionPane.showConfirmDialog(null, "Yes?");
                break;
            case GameController.ButtonPressed.NO_BUTTON: //System.out.println("Button released or no button pressed"); 
                up = false;
                down = false;
                left = false;
                right = false;
                break;

        }
//        if (buttonPressed == GameController.ButtonPressed.D_PAD_LEFT) {
//            System.out.println("GAME D LEFT");
//
//        }
//        if (buttonPressed == GameController.ButtonPressed.D_PAD_RIGHT) {
//            System.out.println("GAME D RIGHT");
//        }
//
//        if (buttonPressed == GameController.ButtonPressed.A_BUTTON) {
//            System.out.println("GAME A PRESSED");
//        }
    }

//    String cBagel;
//  
    //page236
//  
}
