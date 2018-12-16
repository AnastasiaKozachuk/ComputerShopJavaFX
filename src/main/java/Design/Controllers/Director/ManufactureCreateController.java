package Design.Controllers.Director;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.Manufacturer;
import compShop.service.ManufacturerService;
import compShop.service.impl.ManufacturerServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManufactureCreateController {

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

                Manufacturer manufacturer = new Manufacturer(name);

                ManufacturerService manufacturerService = new ManufacturerServiceImpl();
                manufacturerService.saveNewManufacturer(manufacturer);

                SharedClasses.director_window.showManufacturers();

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
