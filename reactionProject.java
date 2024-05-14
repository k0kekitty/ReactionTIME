import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;


public class reactionProject extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
    StackPane pane = new StackPane();
    Rectangle redRect = new Rectangle(0,0,300,150);
    redRect.setFill(Color.RED);
    pane.getChildren().add(redRect);



    Scene scene = new Scene(pane,1000,700);
    primaryStage.setTitle("Reaction Time");
    primaryStage.setScene(scene);
    primaryStage.show();
 }

    public static void main(String[] args) throws Exception{
        launch(args);
 }
}