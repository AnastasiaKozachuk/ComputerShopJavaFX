package Design.Controllers.Courier;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.Courier;
import compShop.model.Manager;
import compShop.service.CourierService;
import compShop.service.impl.CourierServiceImpl;
import compShop.service.impl.ManagerServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ChooseUserCourierController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox listCategory;

    @FXML
    private Button chooseButton;


    private CourierService courierService;

    @FXML
    void initialize() {
        courierService = new CourierServiceImpl();
        init();

        chooseButton.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            String selectedCourier = listCategory.getSelectionModel().getSelectedItem().toString();

            if (!selectedCourier.isEmpty()) {
                chooseButton.getScene().getWindow().hide();
                List<Courier> allCourier = courierService.getAllCourier();
                Courier courierPerson = null;
                for (Courier courier : allCourier) {
                    if (courier.getCourier_name().equals(selectedCourier)) {
                        courierPerson = courier;
                    }
                }

                SharedClasses.courier_client = courierPerson;
                loader.setLocation(getClass().getResource("/FXML/CourierWindow.fxml"));
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

    private void init() {

        List<Courier> allCourier = courierService.getAllCourier();

        for (Courier courier : allCourier) {
            listCategory.getItems().add(courier.getCourier_name());
        }

        listCategory.getSelectionModel().selectFirst();

    }

}
