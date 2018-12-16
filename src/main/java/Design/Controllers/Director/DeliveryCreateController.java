package Design.Controllers.Director;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.Delivery;
import compShop.service.DeliveryService;
import compShop.service.impl.DeliveryServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class DeliveryCreateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox edrpouList;

    @FXML
    private ComboBox prod_list;

    @FXML
    private Button saveButton;

    private DeliveryService deliveryService;

    @FXML
    void initialize() {
        deliveryService = new DeliveryServiceImpl();
        init();

        saveButton.setOnAction(event -> {

            String productArt = prod_list.getSelectionModel().getSelectedItem().toString();
            Integer edrpou = (Integer) edrpouList.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();

            if (!productArt.isEmpty() && edrpou!=null) {
                saveButton.getScene().getWindow().hide();

                Delivery delivery = new Delivery(edrpou,productArt);

                deliveryService.saveNewDelivery(delivery);

                SharedClasses.director_window.showDeliveries();

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

        List<Delivery> allDelivery = deliveryService.getAllDelivery();

        for (Delivery item : allDelivery) {
            edrpouList.getItems().add(item.getEdrpou_fk());
            prod_list.getItems().add(item.getProd_article_fk());
        }
        edrpouList.getSelectionModel().selectFirst();
        prod_list.getSelectionModel().selectFirst();

    }


}
