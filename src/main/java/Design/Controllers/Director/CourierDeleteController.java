package Design.Controllers.Director;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.Courier;
import compShop.service.CourierService;
import compShop.service.impl.CourierServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class CourierDeleteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteButton;

    @FXML
    private ComboBox listCourier;

    private CourierService courierService;

    @FXML
    void initialize() {
        courierService = new CourierServiceImpl();
        init();

        deleteButton.setOnAction(event -> {

            String courierName = listCourier.getSelectionModel().getSelectedItem().toString();
            FXMLLoader loader = new FXMLLoader();
            if (!courierName.isEmpty()) {

                deleteButton.getScene().getWindow().hide();

                List<Courier> allCourier = courierService.getAllCourier();

                for (Courier item : allCourier) {
                    if (item.getCourier_name().equals(courierName)) {
                        courierService.deleteCourier(item);
                    }
                }

                SharedClasses.director_window.showCouriers();

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

        List<Courier> allCourier = courierService.getAllCourier();

        for (Courier item : allCourier) {

            listCourier.getItems().add(item.getCourier_name());
        }
        listCourier.getSelectionModel().selectFirst();
    }
}