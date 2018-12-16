package Design.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.Customer;
import compShop.service.CustomerService;
import compShop.service.impl.CustomerServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuthCustomerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button registerButton;

    @FXML
    private Button enterButton;

    @FXML
    private TextField loginField;


    @FXML
    void initialize() {

        enterButton.setOnAction(event -> {

            String login = loginField.getText();
            String password = password_field.getText();

            CustomerService customerService = new CustomerServiceImpl();
            Customer customer = customerService.getOneByLogin(login);

            FXMLLoader loader = new FXMLLoader();

            if(customer!=null && customer.getCust_pass().equals(password)){
                registerButton.getScene().getWindow().hide();
                SharedClasses.customer_client = customer;
                loader.setLocation(getClass().getResource("/FXML/CustomerWindow.fxml"));
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

        registerButton.setOnAction(event -> {
            registerButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/Registry.fxml"));

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

