import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class Main extends Application 
{ 
  
  @Override
  public void start(Stage stage) throws IOException {

    FXMLLoader fxmlLoader = new     
FXMLLoader(Main.class.getResource("./dlmh_home.fxml"));
    fxmlLoader.setController(new homeController());
    Scene scene = new Scene(fxmlLoader.load());
    stage.setTitle("Don't Leave Me Hanging");
    stage.setScene(scene);
    stage.show();
    /*
    Label label; 
    TextField tf;
    Button button;
    VBox vbox;
    Scene scene;
    
    tf = new TextField("Text Field!");
    tf.setMaxWidth(200);

    label = new Label("Type text and click the button");
    button = new Button("Click"); 

    button.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        label.setText(tf.getText());
      }
    });

    vbox = new VBox(label, tf, button);
    vbox.setSpacing(20);
    vbox.setAlignment(Pos.CENTER);
    scene = new Scene(vbox, 300, 200);
    
    primaryStage.setTitle("A Simple Scene!");
    primaryStage.setScene(scene);
    primaryStage.show();
    */
  } 
    
  public static void main(String[] args) {
    launch(args);
  }
} 
