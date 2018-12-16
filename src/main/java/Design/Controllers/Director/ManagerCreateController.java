package Design.Controllers.Director;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.Manager;
import compShop.service.ManagerService;
import compShop.service.impl.ManagerServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManagerCreateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField patron_field;

    @FXML
    private TextField surname_field;

    @FXML
    private TextField phone_field;

    @FXML
    private TextField nameField;

    @FXML
    private Button saveButton;

    @FXML
    void initialize() {
        saveButton.setOnAction(event -> {

            String name = nameField.getText();
            String surname = surname_field.getText();
            String patron = patron_field.getText();
            String phone = phone_field.getText();


            FXMLLoader loader = new FXMLLoader();

            if (!name.isEmpty() && !surname.isEmpty() && !patron.isEmpty() && !phone.isEmpty()) {
                saveButton.getScene().getWindow().hide();

                Manager manager = new Manager(name,surname,patron,phone);

                ManagerService managerService = new ManagerServiceImpl();
                managerService.saveNewManager(manager);

                SharedClasses.director_window.showManagers();

            } else {
                loader.setLocation(getClass().getResource("/FXML/WrongUser.fxml"));
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
            }

        });
    }
}