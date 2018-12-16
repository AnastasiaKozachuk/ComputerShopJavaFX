package Design.Controllers.Director;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.OrderStatus;
import compShop.service.OrderStatusService;
import compShop.service.impl.OrderStatusServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class OrderStatusDeleteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteButton;

    @FXML
    private ComboBox listOrderStatus;



    private OrderStatusService orderStatusService;

    @FXML
    void initialize() {
        orderStatusService = new OrderStatusServiceImpl();
        init();

        deleteButton.setOnAction(event -> {

            String orderStatusName = listOrderStatus.getSelectionModel().getSelectedItem().toString();
            FXMLLoader loader = new FXMLLoader();

            if (!orderStatusName.isEmpty()) {

                deleteButton.getScene().getWindow().hide();

                List<OrderStatus> allOrderStatus = orderStatusService.getAllOrderStatus();

                for (OrderStatus item : allOrderStatus) {
                    if (item.getOrder_status_name().equals(orderStatusName)) {
                        orderStatusService.deleteOrderStatus(item);
                    }
                }

                SharedClasses.director_window.showOrderStatus();

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

        List<OrderStatus> allOrderStatus = orderStatusService.getAllOrderStatus();

        for (OrderStatus item : allOrderStatus) {
            listOrderStatus.getItems().add(item.getOrder_status_name());
        }
        listOrderStatus.getSelectionModel().selectFirst();
    }
}

