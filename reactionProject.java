import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ContentDisplay;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Animation;


public class reactionProject extends Application {
    private boolean start = false;
    private boolean rectOnScreen = false;
    private Rectangle redRect = new Rectangle(0,0,300,150);
    private Button startButt = new Button("Start");
    private StackPane pane = new StackPane();
    private Scene scene = new Scene(pane,1000,700);
    private Label description = new Label("This is a reaction game that tests your reaction time. " + 
        "When the red rectangle appears on the screen, press the space bar as quickly as you can. Press start to begin.", startButt);
    private int count = 0;
    private Random generator;
    private int randomInt = 1000;
    
    

    @Override
    public void start(Stage primaryStage) throws Exception {

        /* 
        redRect.setFill(Color.RED);
        pane.getChildren().add(redRect);
         */
        description.setContentDisplay(ContentDisplay.BOTTOM);
        description.setTextFill(Color.RED);
        pane.getChildren().add(description);
        startButt.setOnAction(new startHandler());

        //Scene scene = new Scene(pane,1000,700);

        /* 
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE) {
                pane.getChildren().clear();
            }
            }
        });
        */

        primaryStage.setTitle("Reaction Time");
        primaryStage.setScene(scene);
        primaryStage.show();
 }

    class startHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            beginGame();
        }
    }

    private void beginGame() {
        start = true;
        pane.getChildren().remove(description);
        redRect.setFill(Color.RED);
        pane.getChildren().add(redRect);
        rectOnScreen = true;
        /*
        EventHandler<KeyEvent> spaceHandler = e -> {
                if(event.getCode() == KeyCode.SPACE && count < 6) {
                    if (rectOnScreen == true) {
                        pane.getChildren().clear();
                        rectOnScreen = false;
                    } else if (rectOnScreen == false) {
                        pane.getChildren().add(redRect);
                        rectOnScreen = true;
                    }
                }
        };
        */




        /* 
        Timeline animation = new Timeline();

        animation.getKeyFrames().addAll(new KeyFrame(Duration.ZERO, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (rectOnScreen == false) {
                    pane.getChildren().add(redRect);
                    randomInt = generator.nextInt(1000) + 5;
                    animation.setRate(1d/randomInt);
                    rectOnScreen = true;
                } else {
                    pane.getChildren().clear();
                    randomInt = generator.nextInt(1000) + 5;
                    animation.setRate(1d/randomInt);
                    rectOnScreen = false;
                }
            }
        }));
        //animation.play();
        */


        EventHandler<ActionEvent> eventHandler = e ->{
            if (rectOnScreen == false) {
                pane.getChildren().add(redRect);
                rectOnScreen = true;
            } else {
                pane.getChildren().clear();
                rectOnScreen = false;
            }
        };

        Timeline animation = new Timeline (new KeyFrame(Duration.millis(randomInt), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.SPACE && count < 6) {
                    if (animation.getStatus() == Animation.Status.RUNNING) {
                        //pane.getChildren().clear();
                        animation.pause();
                        int andomInt = generator.nextInt(1) + 5;
                        animation.setRate(1d/andomInt);
                    } else if (animation.getStatus() == Animation.Status.PAUSED) {
                        animation.play();
                        int andomInt = generator.nextInt(1) + 5;
                        animation.setRate(1d/andomInt);
                        //pane.getChildren().add(redRect);
                        //rectOnScreen = true;
                    }
                }
            }
        });
        

        /* 
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.SPACE && count < 6) {
                    if (rectOnScreen == true) {
                        pane.getChildren().clear();
                        rectOnScreen = false;
                    } else if (rectOnScreen == false) {
                        pane.getChildren().add(redRect);
                        rectOnScreen = true;
                    }
                }
            }
        });
        */
    }

    public static void main(String[] args) throws Exception{
        launch(args);
 }
}