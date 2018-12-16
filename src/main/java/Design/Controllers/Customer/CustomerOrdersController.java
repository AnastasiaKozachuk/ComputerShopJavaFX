package Design.Controllers.Customer;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Design.POJO.OrderView;
import Design.SharedClasses;
import compShop.model.UsersOrder;
import compShop.service.CustomerService;
import compShop.service.OrderService;
import compShop.service.impl.CustomerServiceImpl;
import compShop.service.impl.OrderServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CustomerOrdersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView main_table;

    @FXML
    private Button order_info_field;

    @FXML
    private Button count_summa_field;


    @FXML
    void initialize() {

        init();

        order_info_field.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/Customer/ListItemInfoOrder.fxml"));
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

        count_summa_field.setOnAction(event -> {

            CustomerService customerService = new CustomerServiceImpl();

            BigDecimal earnedMoney = customerService.countFullSumOfBoughtGoods(SharedClasses.customer_client.getCustomer_login());
            SharedClasses.number = earnedMoney;

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/ResultWindow.fxml"));
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

        List<UsersOrder> usersOrders = orderService.getAllUsersOrder();

        List<UsersOrder> myUserOrders = new ArrayList<>();

        for (UsersOrder usersOrder : usersOrders) {
            if (usersOrder.getCustomer_fk().getCustomer_login().equals(SharedClasses.customer_client.getCustomer_login())) {
                myUserOrders.add(usersOrder);
            }
        }

        TableView tableView = createTableView(myUserOrders);
        main_table.getColumns().addAll(tableView.getColumns());
        main_table.getItems().addAll(tableView.getItems());

    }


    private TableView createTableView(List<UsersOrder> usersOrders) {

        TableView main_table = new TableView();

        ObservableList tableList = FXCollections.observableList(usersOrders);

        TableColumn column1 = getColumn("Order code", "order_code");
        TableColumn column2 = getColumn("Order date", "order_date");
        TableColumn column3 = getColumn("City", "rec_city");
        TableColumn column4 = getColumn("Street", "rec_street");
        TableColumn column5 = getColumn("House number", "rec_house_num");
        TableColumn column6 = getColumn("Apartment number", "rec_apart_num");
        TableColumn column7 = getColumn("Post office number", "post_office_num");
        TableColumn column8 = getColumn("Payment date", "payment_date");
        TableColumn column9 = getColumn("Approximate deliv date", "appr_deliv_date");
        TableColumn column10 = getColumn("Actual delivery date", "actual_deliv_date");
        TableColumn column11 = getColumn("Wishes", "add_wishes");
        TableColumn column12 = getColumn("Login", "customerLogin");
        TableColumn column13 = getColumn("Payment type", "paymantTypeName");
        TableColumn column14 = getColumn("Delivery type", "deliveryTypeName");
        TableColumn column15 = getColumn("Courier", "courierName");
        TableColumn column16 = getColumn("Manager", "managerName");
        TableColumn column17 = getColumn("Order status", "statusName");

        main_table.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8, column9, column10, column11, column12, column13, column14, column15, column16, column17);
        main_table.setItems(tableList);

        return main_table;

    }

    private <S, T> TableColumn<S, T> getColumn(String column_name, String field_name) {

        TableColumn<S, T> newColumn = new TableColumn<>(column_name);
        newColumn.setCellValueFactory(new PropertyValueFactory<>(field_name));

        return newColumn;
    }


}

