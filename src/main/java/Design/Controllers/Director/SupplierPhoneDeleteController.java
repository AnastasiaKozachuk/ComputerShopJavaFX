package Design.Controllers.Director;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.SupplierPhone;
import compShop.service.SupplierPhoneService;
import compShop.service.impl.SupplierPhoneServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class SupplierPhoneDeleteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteButton;

    @FXML
    private ComboBox listSuppliersPhones;

    private SupplierPhoneService supplierPhoneService;

    @FXML
    void initialize() {
        supplierPhoneService = new SupplierPhoneServiceImpl();

        init();

        deleteButton.setOnAction(event -> {

            FXMLLoader loader = new FXMLLoader();

            String supplierPhone = listSuppliersPhones.getSelectionModel().getSelectedItem().toString();

            if (!supplierPhone.isEmpty()) {

                deleteButton.getScene().getWindow().hide();

                List<SupplierPhone> allSupplierPhone = supplierPhoneService.getAllSupplierPhone();

                for (SupplierPhone item : allSupplierPhone) {
                    if (item.getPhone_numb().equals(supplierPhone)) {
                        supplierPhoneService.deleteSupplierPhone(item);
                    }
                }

                SharedClasses.director_window.showSupplierPhones();

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

        List<SupplierPhone> allSupplierPhone = supplierPhoneService.getAllSupplierPhone();

        for (SupplierPhone item : allSupplierPhone) {
            listSuppliersPhones.getItems().add(item.getPhone_numb());
        }
        listSuppliersPhones.getSelectionModel().selectFirst();
    }
}

