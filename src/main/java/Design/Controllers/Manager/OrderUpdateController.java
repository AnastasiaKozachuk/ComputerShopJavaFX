package Design.Controllers.Manager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import Design.SharedClasses;
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
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import javax.jws.soap.SOAPBinding;

public class OrderUpdateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker appr_deliv_date_field;

    @FXML
    private ComboBox order_code_field;

    @FXML
    private ComboBox order_status_field;

    @FXML
    private Button saveButton;

    @FXML
    private DatePicker payment_date_field;

    @FXML
    private DatePicker actual_deliv_date_field;

    @FXML
    void initialize() {

        init();

        saveButton.setOnAction(event -> {


            Integer order_code_chosen = (Integer) order_code_field.getSelectionModel().getSelectedItem();
            LocalDate appr_deliv_date = appr_deliv_date_field.getValue();
            LocalDate payment_date = payment_date_field.getValue();
            LocalDate actual_deliv_date = actual_deliv_date_field.getValue();
            String statusName = (String) order_status_field.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();

            if (order_code_chosen != null && appr_deliv_date!=null && !statusName.isEmpty()) {

                saveButton.getScene().getWindow().hide();
                OrderService orderService = new OrderServiceImpl();
                UsersOrder usersOrder = orderService.getOneById(order_code_chosen);

                usersOrder.setAppr_deliv_date(appr_deliv_date);
                usersOrder.setActual_deliv_date(actual_deliv_date);
                usersOrder.setPayment_date(payment_date);

                OrderStatusService orderStatusService = new OrderStatusServiceImpl();
                List<OrderStatus> allOrderStatus = orderStatusService.getAllOrderStatus();

                for (OrderStatus orderStatus : allOrderStatus) {
                    if(orderStatus.getOrder_status_name().equals(statusName)){
                        usersOrder.setOrderStatus_fk(orderStatus);
                    }
                }

                orderService.updateUsersOrder(usersOrder);

                SharedClasses.manager_window.showOrders();

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


        order_code_field.setOnAction(event -> {

            customizeView((Integer) order_code_field.getSelectionModel().getSelectedItem());
        });

    }


    private void customizeView(Integer orderCode) {

        OrderService orderService = new OrderServiceImpl();
        UsersOrder usersOrder = orderService.getOneById(orderCode);

        order_code_field.getSelectionModel().select(orderCode);
        appr_deliv_date_field.setValue(usersOrder.getAppr_deliv_date());
        actual_deliv_date_field.setValue(usersOrder.getActual_deliv_date());
        payment_date_field.setValue(usersOrder.getPayment_date());
        order_status_field.getSelectionModel().select(usersOrder.getStatusName());
    }

    private void init() {
        OrderService orderService = new OrderServiceImpl();
        List<UsersOrder> allUsersOrder = orderService.getAllUsersOrder();

        for (UsersOrder obj : allUsersOrder) {

            if (obj.getManager_fk().getManager_id() == SharedClasses.manager_client.getManager_id()) {
                order_code_field.getItems().add(obj.getOrder_code());
            }
        }


        order_code_field.getSelectionModel().selectFirst();

        OrderStatusService orderStatusService = new OrderStatusServiceImpl();
        List<OrderStatus> allOrderStatus = orderStatusService.getAllOrderStatus();

        for (OrderStatus orderStatus : allOrderStatus) {
            order_status_field.getItems().add(orderStatus.getOrder_status_name());
        }

        order_status_field.getSelectionModel().selectFirst();


        customizeView((Integer) order_code_field.getSelectionModel().getSelectedItem());
    }

}
