package Design.Controllers.Courier;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.Courier;
import compShop.model.Manager;
import compShop.model.OrderStatus;
import compShop.model.UsersOrder;
import compShop.service.OrderService;
import compShop.service.OrderStatusService;
import compShop.service.impl.OrderServiceImpl;
import compShop.service.impl.OrderStatusServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class NewOrderForCourierController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox newOrderList;

    @FXML
    private Button chooseButton;

    @FXML
    void initialize() {
        init();

        chooseButton.setOnAction(event -> {

            Integer order_code_chosen = (Integer) newOrderList.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader();

            if (order_code_chosen != null) {
                chooseButton.getScene().getWindow().hide();
                OrderService orderService = new OrderServiceImpl();
                UsersOrder usersOrder = orderService.getOneById(order_code_chosen);

                Courier courier = SharedClasses.courier_client;
                usersOrder.setCourier_fk(courier);

                OrderStatusService orderStatusService = new OrderStatusServiceImpl();
                List<OrderStatus> allOrderStatus = orderStatusService.getAllOrderStatus();
                OrderStatus orderSt = null;
                for (OrderStatus orderStatus : allOrderStatus) {
                    if(orderStatus.getOrder_status_name().equals("Виконується кур'єром")){
                        orderSt= orderStatus;
                    }
                }
                usersOrder.setOrderStatus_fk(orderSt);

                orderService.updateUsersOrder(usersOrder);

                SharedClasses.courier_window.showOrders();

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

        OrderService orderService = new OrderServiceImpl();
        List<Object[]> allUsersOrder = orderService.getNewOrderForCouriers();

        for (Object[] obj : allUsersOrder) {
            newOrderList.getItems().add(obj[0]);
        }

        newOrderList.getSelectionModel().selectFirst();

    }
}

