package Design.Controllers.Manager;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.Courier;
import compShop.model.Good;
import compShop.model.ItemInOrder;
import compShop.model.UsersOrder;
import compShop.service.CourierService;
import compShop.service.GoodService;
import compShop.service.ItemInOrderService;
import compShop.service.OrderService;
import compShop.service.impl.CourierServiceImpl;
import compShop.service.impl.GoodServiceImpl;
import compShop.service.impl.ItemInOrderServiceImpl;
import compShop.service.impl.OrderServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ListItemInOrderCreateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox prod_articl_list;

    @FXML
    private ComboBox orderList;

    @FXML
    private Button saveButton;

    @FXML
    private TextField quantity_prod;


    @FXML
    void initialize() {
        init();

        saveButton.setOnAction(event -> {

            String prod_article = prod_articl_list.getSelectionModel().getSelectedItem().toString();
            Integer order_code = (Integer) orderList.getSelectionModel().getSelectedItem();
            String quantity = quantity_prod.getText();


            FXMLLoader loader = new FXMLLoader();

            if (!prod_article.isEmpty() && order_code != null && !quantity.isEmpty()) {
                saveButton.getScene().getWindow().hide();


                GoodService goodService = new GoodServiceImpl();
                List<Good> goodList = goodService.getAllGood();
                Good good_fk = null;
                for (Good good : goodList) {
                    if (good.getProduct_article().equals(prod_article)) {
                        good_fk = good;
                    }
                }

                if (good_fk.getInStock() >= Integer.valueOf(quantity)) {

                    good_fk.setInStock(good_fk.getInStock() - Integer.valueOf(quantity));
                    goodService.updateGood(good_fk);

                    OrderService orderService = new OrderServiceImpl();
                    List<UsersOrder> allUsersOrder = orderService.getAllUsersOrder();
                    UsersOrder usersOrder_fk = null;
                    for (UsersOrder usersOrder : allUsersOrder) {
                        if (usersOrder.getOrder_code() == order_code) {
                            usersOrder_fk = usersOrder;
                        }
                    }

                    ItemInOrderService itemInOrderService = new ItemInOrderServiceImpl();

                    ItemInOrder itemInOrder = new ItemInOrder(usersOrder_fk, good_fk, Integer.valueOf(quantity));

                    itemInOrderService.saveNewItemInOrder(itemInOrder);
                }else{
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


                SharedClasses.manager_window.showListInOrder();

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
        GoodService goodService = new GoodServiceImpl();
        List<Good> goodList = goodService.getAllGood();

        for (Good good : goodList) {
            prod_articl_list.getItems().add(good.getProduct_article());
        }

        prod_articl_list.getSelectionModel().selectFirst();

        OrderService orderService = new OrderServiceImpl();
        List<UsersOrder> allUsersOrder = orderService.getAllUsersOrder();

        for (UsersOrder usersOrder : allUsersOrder) {
            orderList.getItems().add(usersOrder.getOrder_code());
        }

        orderList.getSelectionModel().selectFirst();

    }

}
