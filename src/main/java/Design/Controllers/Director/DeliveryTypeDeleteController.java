package Design.Controllers.Director;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class DeliveryTypeDeleteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteButton;

    @FXML
    private ComboBox listDeliveryType;

    private DeliveryTypeService deliveryTypeService;

    @FXML
    void initialize() {
        deliveryTypeService = new DeliveryTypeServiceImpl();
        init();

        deleteButton.setOnAction(event -> {

            String deliveryTypeName = listDeliveryType.getSelectionModel().getSelectedItem().toString();
            FXMLLoader loader = new FXMLLoader();

            if (!deliveryTypeName.isEmpty()) {

                deleteButton.getScene().getWindow().hide();

                List<DeliveryType> allDeliveryType = deliveryTypeService.getAllDeliveryType();

                for (DeliveryType item : allDeliveryType) {
                    if (item.getDelivery_type_name().equals(deliveryTypeName)) {
                        deliveryTypeService.deleteDeliveryType(item);
                    }
                }

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

    private void init() {

        List<DeliveryType> allDeliveryType = deliveryTypeService.getAllDeliveryType();

        for (DeliveryType item : allDeliveryType) {

            listDeliveryType.getItems().add(item.getDelivery_type_name());
        }
        listDeliveryType.getSelectionModel().selectFirst();
    }
}

