package Design.Controllers.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.Courier;
import compShop.model.Customer;
import compShop.service.CourierService;
import compShop.service.CustomerService;
import compShop.service.impl.CourierServiceImpl;
import compShop.service.impl.CustomerServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProfileInfoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField newPassword_field;

    @FXML
    private Button updateInfo;

    @FXML
    private TextField newSurname_field;

    @FXML
    private Label patron;

    @FXML
    private Label birthdate;

    @FXML
    private TextField newPhone_field;

    @FXML
    private Label sex;

    @FXML
    private TextField newName_field;

    @FXML
    private Label login;

    @FXML
    private Label password;

    @FXML
    private Label phone;

    @FXML
    private Label surname;

    @FXML
    private Label name;

    @FXML
    private TextField newPatron_field;

    @FXML
    void initialize() {


        showInfo();

        updateInfo.setOnAction(event -> {

            String newName = newName_field.getText();
            String newSurname = newSurname_field.getText();
            String newPatron = newPatron_field.getText();
            String newPhone = newPhone_field.getText();
            String newPass = newPassword_field.getText();

            CustomerService customerService = new CustomerServiceImpl();

            Customer customer = SharedClasses.customer_client;

            if (!newName.isEmpty()) {
                customer.setCust_name(newName);
            }

            if (!newSurname.isEmpty()) {
                customer.setCust_surname(newSurname);
            }

            if (!newPatron.isEmpty()) {
                customer.setCust_patron(newPatron);
            }

            if (!newPhone.isEmpty()) {
                customer.setCust_phone(newPhone);
            }

            if (!newPass.isEmpty()) {
                customer.setCust_pass(newPass);
            }

            customerService.updateCustomer(customer);

            showInfo();
            newName_field.setText("");
            newSurname_field.setText("");
            newPatron_field.setText("");
            newPhone_field.setText("");
            newPassword_field.setText("");
        });


    }

    private void showInfo() {

        Customer customer = SharedClasses.customer_client;

        patron.setText(customer.getCust_patron());
        birthdate.setText(customer.getCust_birthday().toString());
        sex.setText(customer.getCust_sex());
        login.setText(customer.getCustomer_login());
        password.setText(customer.getCust_pass());
        phone.setText(customer.getCust_phone());
        surname.setText(customer.getCust_surname());
        name.setText(customer.getCust_name());
    }
}
