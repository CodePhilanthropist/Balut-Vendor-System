/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createaccount;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import balutvendorsystem.BalutVendorSystem;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author k
 */
public class CreateAccountController implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private TextField mobileno;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private TextField address;

    @FXML
    private TextField city;

    @FXML
    private TextField balance;

    @FXML
    private ComboBox<String> accounttype;

    @FXML
    private ComboBox<String> question;

    @FXML
    private TextField answer;

    @FXML
    private PasswordField password;

    ObservableList<String> list = FXCollections.observableArrayList("Male", "Female", "Other");
    ObservableList<String> list1 = FXCollections.observableArrayList("Vendor", "Admin");
    ObservableList<String> list2 = FXCollections.observableArrayList("What's your pet's name?", "Who is your favorite superhero?", "What the secret quote?");

    public void backToLogin(MouseEvent event) throws IOException {
        BalutVendorSystem.stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/balutvendorsystem/BalutVendorSystem.fxml")));
    }

    public void closeApp(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    private void newAccount(MouseEvent event) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/balut", "root", "");
            String sql = "SELECT * FROM userdata WHERE AccountNo=? AND Password=?";
            ps = con.prepareStatement(sql);

            ps.setString(1, name.getText());
            ps.setString(2, password.getText());
            acc = accountno.getText();

            rs = ps.executeQuery();
            if (rs.next()) {
//                ((Node) event.getSource()).getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/dashboard/Dashboard.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("/design/style.css").toExternalForm());
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();
                this.stage = stage;

            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText("Error in login");
                a.setContentText("Your account number or pin is wrong. Enter again..!!!");
                a.showAndWait();
            }
//            con.close();
//            ps.close();

        } catch (Exception e) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
                String sql = "SELECT * FROM userdata WHERE AccountNo=? AND PIN=?";
                ps = con.prepareStatement(sql);

                ps.setString(1, accountno.getText());
                ps.setString(2, pin.getText());
                acc = accountno.getText();

                rs = ps.executeQuery();
                if (rs.next()) {
//                ((Node) event.getSource()).getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("/dashboard/Dashboard.fxml"));
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(getClass().getResource("/design/style.css").toExternalForm());
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(scene);
                    stage.show();
                    this.stage = stage;

                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Error");
                    a.setHeaderText("Error in login");
                    a.setContentText("Your account number or pin is wrong. Enter again..!!!");
                    a.showAndWait();
                }
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText("Error in login .");
                a.setContentText("There is some error. PLEASE TRY AGAIN..!!!");
                a.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
