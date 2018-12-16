package Design.Controllers.Director;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ManufacturerDeleteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteButton;

    @FXML
    private ComboBox listManufactures;

    private ManufacturerService manufacturerService;

    @FXML
    void initialize() {
        manufacturerService = new ManufacturerServiceImpl();
        init();

        deleteButton.setOnAction(event -> {

            String manufactName = listManufactures.getSelectionModel().getSelectedItem().toString();
            FXMLLoader loader = new FXMLLoader();

            if (!manufactName.isEmpty()) {

                deleteButton.getScene().getWindow().hide();

                List<Manufacturer> allManufacturer = manufacturerService.getAllManufacturer();

                for (Manufacturer item : allManufacturer) {
                    if (item.getManufact_name().equals(manufactName)) {
                        manufacturerService.deleteManufacturer(item);
                    }
                }

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

    private void init() {

        List<Manufacturer> allManufacturer = manufacturerService.getAllManufacturer();

        for (Manufacturer item : allManufacturer) {
            listManufactures.getItems().add(item.getManufact_name());
        }
        listManufactures.getSelectionModel().selectFirst();
    }
}

