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

/**
 * Program that measures reaction time of the user based on how quickly the user can
 * press the space bar when the red rectangle appears on the screen.
 *
 * @author Maria Cymbalyuk
 * @version 1.0
 */
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
    private int randomInt = 3000;
    private long totalTime = 0;
    private long startTime = 0;
    private long finish = 0;
    private long estimatedTime = 0;

    //Sets stage and allows screen to appear
    @Override
    public void start(Stage primaryStage) throws Exception {
        description.setContentDisplay(ContentDisplay.BOTTOM);
        description.setTextFill(Color.RED);
        pane.getChildren().add(description);
        startButt.setOnAction(new startHandler());

        primaryStage.setTitle("Reaction Time");
        primaryStage.setScene(scene);
        primaryStage.show();
 }

    //Event handler for the start button so that the game can begin
    class startHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            beginGame();
        }
    }

    //Method that has the rectangle appear and the reaction time is recorded
    private void beginGame() {
        start = true;
        pane.getChildren().remove(description);
        rectOnScreen = false;

        redRect.setFill(Color.RED);

        EventHandler<ActionEvent> eventHandler = e -> {
            if (rectOnScreen == false) {
                //long startTimeforRect = System.nanoTime();
                pane.getChildren().add(redRect);
                //long endTimeForRect = System.nanoTime();
                //System.out.println("Time for rectangle to appear: " + (endTimeForRect - startTimeforRect));
                startTime = System.currentTimeMillis();
                rectOnScreen = true;
            }
        };

        Timeline animation = new Timeline (new KeyFrame(Duration.millis(randomInt), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);

        animation.play();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.SPACE && count < 6) {
                    if (rectOnScreen == true) {
                        finish = System.currentTimeMillis();
                        rectOnScreen = false;
                        estimatedTime = finish - startTime;
                        totalTime += estimatedTime;
                        count++;
                        pane.getChildren().clear();
                        System.out.println("The start time is: " + startTime);
                        System.out.println("The finish time is: " + finish);
                        System.out.println();
                    } else if (rectOnScreen == false) {
                        System.out.println("The rectangle is not on the screen.");
                    }
                } else if (event.getCode() == KeyCode.SPACE && count >= 5) {
                        start = false;
                        animation.pause();
                        pane.getChildren().clear();
                        System.out.println("Your average reaction time is " + (totalTime/count) + " in milliseconds.");
                }
            }
        });
    }

   /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   * @param args The default Driver parameter
   */
    public static void main(String[] args) throws Exception {
        launch(args);
 }
}