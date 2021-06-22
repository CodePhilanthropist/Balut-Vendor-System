package balutvendorsystem;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BalutVendorSystem extends Application {
    
    public static Stage stage = null;

    private double xoffset = 0;
    private double yoffset = 0;
    
    @FXML
    private Pane main_area;

   @FXML
    private void createAccount(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/createaccount/CreateAccount.fxml"));
        main_area.getChildren().removeAll();
        main_area.getChildren().addAll(fxml);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("BalutVendorSystem.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/design/style.css").toExternalForm());
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);

            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xoffset = event.getSceneX();
                    yoffset = event.getSceneY();
                }

            });

            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - xoffset);
                    stage.setY(event.getScreenY() - yoffset);
                }

            });
            this.stage = stage;
            stage.show();
        } catch (Exception e) {
            System.out.println("Something wrong " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
