package Design.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Design.Auth;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class ControllerAuth {

    public static String userType;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button authButton;

    @FXML
    void initialize() {

        authButton.setOnAction(event -> {


            FXMLLoader loader = new FXMLLoader();
            String password = password_field.getText();

            if (userType.equals("director") && password.equals(Auth.DIRECTOR_PASSWORD)) {

                authButton.getScene().getWindow().hide();
                loader.setLocation(getClass().getResource("/FXML/DirectorWindow.fxml"));

            } else if (userType.equals("courier") && password.equals(Auth.COURIER_PASSWORD)) {

                authButton.getScene().getWindow().hide();
                loader.setLocation(getClass().getResource("/FXML/Courier/ChooseUserCourier.fxml"));

            } else if (userType.equals("manager") && password.equals(Auth.MANAGER_PASSWORD)) {

                authButton.getScene().getWindow().hide();
                loader.setLocation(getClass().getResource("/FXML/Manager/ChoodeUserManager.fxml"));

            } else {

                loader.setLocation(getClass().getResource("/FXML/WrongUser.fxml"));

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

