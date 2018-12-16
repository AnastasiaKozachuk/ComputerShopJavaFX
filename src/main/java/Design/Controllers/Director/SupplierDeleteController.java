package Design.Controllers.Director;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.PaymentType;
import compShop.model.Supplier;
import compShop.service.PaymentTypeService;
import compShop.service.SupplierService;
import compShop.service.impl.PaymentTypeServiceImpl;
import compShop.service.impl.SupplierServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class SupplierDeleteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteButton;

    @FXML
    private ComboBox listSuppliers;


    private SupplierService supplierService;

    @FXML
    void initialize() {
        supplierService = new SupplierServiceImpl();
        init();

        deleteButton.setOnAction(event -> {

            String organizationName = listSuppliers.getSelectionModel().getSelectedItem().toString();
            FXMLLoader loader = new FXMLLoader();

            if (!organizationName.isEmpty()) {

                deleteButton.getScene().getWindow().hide();

                List<Supplier> allSupplier = supplierService.getAllSupplier();

                for (Supplier item : allSupplier) {
                    if (item.getOrganization_name().equals(organizationName)) {
                        supplierService.deleteSupplier(item);
                    }
                }

                SharedClasses.director_window.showSuppliers();

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

        List<Supplier> allPaymentType = supplierService.getAllSupplier();

        for (Supplier item : allPaymentType) {
            listSuppliers.getItems().add(item.getOrganization_name());
        }
        listSuppliers.getSelectionModel().selectFirst();
    }
}
