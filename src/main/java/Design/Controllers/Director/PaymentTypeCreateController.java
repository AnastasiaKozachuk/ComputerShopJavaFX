package Design.Controllers.Director;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.PaymentType;
import compShop.service.PaymentTypeService;
import compShop.service.impl.PaymentTypeServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PaymentTypeCreateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameField;

    @FXML
    private Button saveButton;

    @FXML
    void initialize() {
        saveButton.setOnAction(event -> {

            String name = nameField.getText();

            FXMLLoader loader = new FXMLLoader();

            if (!name.isEmpty()) {
                saveButton.getScene().getWindow().hide();

                PaymentType paymentType = new PaymentType(name);

                PaymentTypeService paymentTypeService = new PaymentTypeServiceImpl();
                paymentTypeService.saveNewPaymentType(paymentType);

                SharedClasses.director_window.showPaymentTypes();

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
