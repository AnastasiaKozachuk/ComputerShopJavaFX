package Design.Controllers.Courier;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.Category;
import compShop.model.Good;
import compShop.model.UsersOrder;
import compShop.service.CategoryService;
import compShop.service.GoodService;
import compShop.service.ItemInOrderService;
import compShop.service.OrderService;
import compShop.service.impl.CategoryServiceImpl;
import compShop.service.impl.GoodServiceImpl;
import compShop.service.impl.ItemInOrderServiceImpl;
import compShop.service.impl.OrderServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ListItemInfoOrderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button findButton;

    @FXML
    private ComboBox orderList;

    @FXML
    void initialize() {

        init();

        findButton.setOnAction(event -> {

            Integer order_code = (Integer)orderList.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader();
            if(order_code!=null){

                ItemInOrderService itemInOrderService = new ItemInOrderServiceImpl();
                Object[] objectArr = itemInOrderService.getInfoOfOrder(order_code);

                SharedClasses.result_info = objectArr;

                loader.setLocation(getClass().getResource("/FXML/Manager/ShowInfo.fxml"));

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

    private void init() {

        OrderService orderService = new OrderServiceImpl();
        List<UsersOrder> allUsersOrder = orderService.getAllUsersOrder();

        for (UsersOrder usersOrder : allUsersOrder) {
            orderList.getItems().add(usersOrder.getOrder_code());
        }

        orderList.getSelectionModel().selectFirst();

    }
}


