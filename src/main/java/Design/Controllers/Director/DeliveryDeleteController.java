package Design.Controllers.Director;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.Delivery;
import compShop.model.Good;
import compShop.service.DeliveryService;
import compShop.service.SupplierService;
import compShop.service.impl.DeliveryServiceImpl;
import compShop.service.impl.SupplierServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class DeliveryDeleteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteButton;

    @FXML
    private ComboBox edrpouList;

    @FXML
    private ComboBox prod_list;

    private DeliveryService deliveryService;

    @FXML
    void initialize() {
        deliveryService = new DeliveryServiceImpl();

        init();

        deleteButton.setOnAction(event -> {

            FXMLLoader loader = new FXMLLoader();

            String productArt = prod_list.getSelectionModel().getSelectedItem().toString();
            Integer edrpou = (Integer) edrpouList.getSelectionModel().getSelectedItem();

            if (!productArt.isEmpty() && edrpou!=null) {

                deleteButton.getScene().getWindow().hide();

                Delivery delivery = new Delivery(edrpou,productArt);

                deliveryService.deleteDelivery(delivery);

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


        edrpouList.setOnAction(event -> {
            showPhonesByEdrpou((Integer) edrpouList.getSelectionModel().getSelectedItem());
        });

    }

    private void init() {

        List<Delivery> allDelivery = deliveryService.getAllDelivery();

        for (Delivery item : allDelivery) {
            edrpouList.getItems().add(item.getEdrpou_fk());
        }
        edrpouList.getSelectionModel().selectFirst();

        showPhonesByEdrpou((Integer) edrpouList.getSelectionModel().getSelectedItem());
    }

    private void showPhonesByEdrpou(Integer selectedItem) {

        prod_list.getItems().clear();
        SupplierService supplierService = new SupplierServiceImpl();
        List<Good> selectedItemList = supplierService.getDeliveryProductOfSupplier(selectedItem);

        for(Good good: selectedItemList){
            prod_list.getItems().add(good.getProduct_article());
        }
        prod_list.getSelectionModel().selectFirst();
    }
}
