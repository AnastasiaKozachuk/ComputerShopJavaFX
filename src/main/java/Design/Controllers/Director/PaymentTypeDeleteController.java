package Design.Controllers.Director;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class PaymentTypeDeleteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteButton;

    @FXML
    private ComboBox listPaymentType;

    private PaymentTypeService paymentTypeService;

    @FXML
    void initialize() {
        paymentTypeService = new PaymentTypeServiceImpl();
        init();

        deleteButton.setOnAction(event -> {

            String paymentTypeName = listPaymentType.getSelectionModel().getSelectedItem().toString();
            FXMLLoader loader = new FXMLLoader();

            if (!paymentTypeName.isEmpty()) {

                deleteButton.getScene().getWindow().hide();

                List<PaymentType> allPaymentType = paymentTypeService.getAllPaymentType();

                for (PaymentType item : allPaymentType) {
                    if (item.getPaym_type_name().equals(paymentTypeName)) {
                        paymentTypeService.deletePaymentType(item);
                    }
                }

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

    private void init() {

        List<PaymentType> allPaymentType = paymentTypeService.getAllPaymentType();

        for (PaymentType item : allPaymentType) {
            listPaymentType.getItems().add(item.getPaym_type_name());
        }
        listPaymentType.getSelectionModel().selectFirst();
    }
}