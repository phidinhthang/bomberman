package oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import oop.bomberman.game.Game;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Group root = new Group();
    public static Scene scene = new Scene(App.root);
    public static int scale = 2;
    private static AnimationTimer gameLoop;
    private static double windowWidth;
    private static double windowHeight;
    private static Stage stage;
    private Game game;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setScene(scene);
        this.stage = stage;


        game = new Game();
        game.init();

        stage.setTitle("Bomberman");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        scene.getWindow().setHeight(0);

        App.gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                App.this.update();
                App.this.draw();
            }
        };

        App.gameLoop.start();
    }

    public void update() {
        this.game.update();
    }

    public void draw() {
        this.game.draw();
    }

    public static void setWindowWidth(double width) {
        App.windowWidth = width;
        System.out.println("width " + width);
        App.scene.getWindow().setWidth(width + 16);
    }

    public static void setWindowHeight(double height) {
        App.windowHeight = height;
        System.out.println("height " + height);
        App.scene.getWindow().setHeight(height + 40);
    }

    public static double getWindowWidth() {
        return App.windowWidth;
    }

    public static double getWindowHeight() {
        return App.windowHeight;
    }

    public static void main(String[] args) {
        launch();
    }  
}