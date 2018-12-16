package Design.Controllers.Director;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.DeliveryType;
import compShop.service.DeliveryTypeService;
import compShop.service.impl.DeliveryTypeServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeliveryTypeCreateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameField;

    @FXML
    private Button saveButton;

    @FXML
    void initialize() {
        saveButton.setOnAction(event -> {

            String name = nameField.getText();

            FXMLLoader loader = new FXMLLoader();

            if (!name.isEmpty()) {
                saveButton.getScene().getWindow().hide();

                DeliveryType deliveryType = new DeliveryType(name);

                DeliveryTypeService deliveryTypeService = new DeliveryTypeServiceImpl();
                deliveryTypeService.saveNewDeliveryType(deliveryType);

                SharedClasses.director_window.showDeliveryTypes();

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
