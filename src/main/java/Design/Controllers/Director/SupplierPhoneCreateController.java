package Design.Controllers.Director;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.Supplier;
import compShop.model.SupplierPhone;
import compShop.service.SupplierPhoneService;
import compShop.service.SupplierService;
import compShop.service.impl.SupplierPhoneServiceImpl;
import compShop.service.impl.SupplierServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SupplierPhoneCreateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox edrpouList;

    @FXML
    private Button saveButton;

    @FXML
    private TextField phoneField;

    private SupplierPhoneService supplierPhoneService;

    @FXML
    void initialize() {

        supplierPhoneService = new SupplierPhoneServiceImpl();
        init();

        saveButton.setOnAction(event -> {

            String phone = phoneField.getText();

            FXMLLoader loader = new FXMLLoader();

            if (!phone.isEmpty()) {
                saveButton.getScene().getWindow().hide();


                SupplierService supplierService = new SupplierServiceImpl();
                Supplier supplier = supplierService.getOneById((Integer) edrpouList.getSelectionModel().getSelectedItem());

                SupplierPhone supplierPhone = new SupplierPhone(phone, supplier);

                supplierPhoneService.saveNewSupplierPhone(supplierPhone);

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
            edrpouList.getItems().add(item.getEdrpou());
        }
        edrpouList.getSelectionModel().selectFirst();
    }
}
