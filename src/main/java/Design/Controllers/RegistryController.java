package Design.Controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import compShop.model.Customer;
import compShop.service.CustomerService;
import compShop.service.impl.CustomerServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class RegistryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField customer_password;

    @FXML
    private Button registerButton;

    @FXML
    private RadioButton men_sex;

    @FXML
    private TextField customer_login;

    @FXML
    private ToggleGroup sex;

    @FXML
    private TextField customer_phone;

    @FXML
    private RadioButton women_sex;

    @FXML
    private TextField customer_name;

    @FXML
    private DatePicker customer_databirth;

    @FXML
    private TextField customer_surname;

    @FXML
    private TextField customer_patron;

    @FXML
    void initialize() {


        registerButton.setOnAction(event -> {


            String name = customer_name.getText();
            String surname = customer_surname.getText();
            String patron = customer_patron.getText();
            String phone = customer_phone.getText();
            String login = customer_login.getText();
            String password = customer_password.getText();
            LocalDate dateBirth = customer_databirth.getValue();

            FXMLLoader loader = new FXMLLoader();

            if (!name.isEmpty() && !surname.isEmpty() && !patron.isEmpty() && !phone.isEmpty() && !login.isEmpty() && !password.isEmpty() && dateBirth != null) {

                registerButton.getScene().getWindow().hide();
                loader.setLocation(getClass().getResource("/FXML/CustomerWindow.fxml"));

                Customer customer = new Customer();
                customer.setCust_name(name);
                customer.setCust_surname(surname);
                customer.setCust_patron(patron);
                customer.setCust_phone(phone);
                customer.setCustomer_login(login);
                customer.setCust_pass(password);

                if (women_sex.isSelected()) {
                    customer.setCust_sex("Жінка");
                } else if (men_sex.isSelected()) {
                    customer.setCust_sex("Чоловік");
                }

                customer.setCust_birthday(dateBirth);
                customer.setRegister_date(LocalDate.now());


                CustomerService customerService = new CustomerServiceImpl();
                customerService.saveNewCustomer(customer);
            }else{
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
}

