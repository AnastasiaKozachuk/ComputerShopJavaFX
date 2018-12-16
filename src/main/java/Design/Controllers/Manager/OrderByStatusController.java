package Design.Controllers.Manager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Design.POJO.OrderView;
import Design.SharedClasses;
import compShop.model.Category;
import compShop.model.Good;
import compShop.model.OrderStatus;
import compShop.service.CategoryService;
import compShop.service.GoodService;
import compShop.service.OrderService;
import compShop.service.OrderStatusService;
import compShop.service.impl.CategoryServiceImpl;
import compShop.service.impl.GoodServiceImpl;
import compShop.service.impl.OrderServiceImpl;
import compShop.service.impl.OrderStatusServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class OrderByStatusController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox statusList;

    @FXML
    private Button findButton;

    @FXML
    void initialize() {
        init();

        findButton.setOnAction(event -> {

            String status_name = statusList.getSelectionModel().getSelectedItem().toString();
            FXMLLoader loader = new FXMLLoader();

            if(!status_name.isEmpty()){

                OrderService orderService = new OrderServiceImpl();

                List<Object[]> resultOrders = orderService.getOrderCodeAndCustLogByOrdStat(status_name);

                List<OrderView> orderViews = transformClassses(resultOrders);
                SharedClasses.tableView = createTableView(orderViews);
                loader.setLocation(getClass().getResource("/FXML/TableView.fxml"));

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

    private List<OrderView> transformClassses(List<Object[]> resultOrders) {
        List<OrderView> orderViewList = new ArrayList<>();

        for(Object[] ob:resultOrders ){
            OrderView orderView = new OrderView((Integer)ob[0],(String)ob[1]);
            orderViewList.add(orderView);
        }

        return orderViewList;
    }

    private void init() {
        OrderStatusService orderStatusService = new OrderStatusServiceImpl();
        List<OrderStatus> allOrderStatus = orderStatusService.getAllOrderStatus();

        for (OrderStatus orderStatus : allOrderStatus) {
            statusList.getItems().add(orderStatus.getOrder_status_name());
        }

        statusList.getSelectionModel().selectFirst();
    }


    private TableView createTableView(List<OrderView> orderViews) {

        TableView main_table = new TableView();

        ObservableList tableList = FXCollections.observableList(orderViews);

        TableColumn column1 = getColumn("Login", "login");
        TableColumn column2 = getColumn("Order code", "orderCode");

        main_table.getColumns().addAll(column1, column2);
        main_table.setItems(tableList);

        return main_table;

    }

    private <S, T> TableColumn<S, T> getColumn(String column_name, String field_name) {

        TableColumn<S, T> newColumn = new TableColumn<>(column_name);
        newColumn.setCellValueFactory(new PropertyValueFactory<>(field_name));

        return newColumn;
    }


}
