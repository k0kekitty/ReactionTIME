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


public class reactionProject extends Application {
    private boolean start = true;
    private Rectangle redRect = new Rectangle(0,0,300,150);
    private Button startButt = new Button("Start");
    private StackPane pane = new StackPane();
    private Scene scene = new Scene(pane,1000,700);

    @Override
    public void start(Stage primaryStage) throws Exception {

        /* 
        redRect.setFill(Color.RED);
        pane.getChildren().add(redRect);
         */
        pane.getChildren().add(startButt);
        
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
        start = false;
        pane.getChildren().remove(startButt);
        redRect.setFill(Color.RED);
        pane.getChildren().add(redRect);
        //for (int i = 0; i < 6; i++) {
            //pane.getChildren().add(redRect);
        //}

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE) {
                pane.getChildren().clear();
            }
            }
        });
    }

    public static void main(String[] args) throws Exception{
        launch(args);
 }
}