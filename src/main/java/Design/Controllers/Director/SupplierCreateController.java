package Design.Controllers.Director;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SupplierCreateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField appertm_num_field;

    @FXML
    private TextField web_page_field;

    @FXML
    private TextField edrpou_field;

    @FXML
    private TextField street_field;

    @FXML
    private TextField patron_field;

    @FXML
    private TextField surname_field;

    @FXML
    private TextField org_name_field;

    @FXML
    private TextField house_num_field;

    @FXML
    private TextField name_field;

    @FXML
    private TextField city_field;

    @FXML
    private Button saveButton;

    @FXML
    private TextField curr_acc_field;

    @FXML
    private TextField mfi_field;


    @FXML
    void initialize() {

        saveButton.setOnAction(event -> {

            String appertm_num = appertm_num_field.getText();
            String web_page = web_page_field.getText();
            Integer edrpou = Integer.valueOf(edrpou_field.getText());
            String street = street_field.getText();
            String patron = patron_field.getText();
            String surname = surname_field.getText();
            String org_name = org_name_field.getText();
            String name = name_field.getText();
            String city = city_field.getText();
            String curr_acc = curr_acc_field.getText();
            String house_num = house_num_field.getText();
            String mfi = mfi_field.getText();

            FXMLLoader loader = new FXMLLoader();

            if (!name.isEmpty() && edrpou != null && !street.isEmpty() && !patron.isEmpty() && !surname.isEmpty() && !org_name.isEmpty() && !city.isEmpty() && !curr_acc.isEmpty() && !house_num.isEmpty() && !mfi.isEmpty()) {
                saveButton.getScene().getWindow().hide();

                Supplier supplier = new Supplier(edrpou, org_name, name, surname, patron, city, street, house_num, appertm_num, web_page, mfi, curr_acc);

                SupplierService supplierService = new SupplierServiceImpl();
                supplierService.saveNewSupplier(supplier);

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
}
