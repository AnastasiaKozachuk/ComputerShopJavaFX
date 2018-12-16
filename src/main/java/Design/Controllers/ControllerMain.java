package Design.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ControllerMain {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton manager;

    @FXML
    private RadioButton courier;

    @FXML
    private RadioButton director;

    @FXML
    private Button chooseUser;

    @FXML
    private ToggleGroup users;

    @FXML
    private RadioButton customer;

    @FXML
    void initialize() {

        chooseUser.setOnAction(event -> {

            chooseUser.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();

            if (director.isSelected()) {
                loader.setLocation(getClass().getResource("/FXML/Auth.fxml"));
                ControllerAuth.userType = "director";
            } else if (courier.isSelected()) {
                loader.setLocation(getClass().getResource("/FXML/Auth.fxml"));
                ControllerAuth.userType = "courier";
            } else if (manager.isSelected()) {
                loader.setLocation(getClass().getResource("/FXML/Auth.fxml"));
                ControllerAuth.userType = "manager";
            } else if (customer.isSelected()) {
                loader.setLocation(getClass().getResource("/FXML/AuthCustomer.fxml"));
            }

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

        });

    }
}
