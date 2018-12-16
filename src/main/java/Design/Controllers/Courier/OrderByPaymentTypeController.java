package Design.Controllers.Courier;

import Design.POJO.OrderView;
import Design.SharedClasses;
import compShop.model.PaymentType;
import compShop.service.OrderService;
import compShop.service.PaymentTypeService;
import compShop.service.impl.OrderServiceImpl;
import compShop.service.impl.PaymentTypeServiceImpl;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderByPaymentTypeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button findButton;

    @FXML
    private ComboBox paymTypeList;

    @FXML
    void initialize() {
        init();

        findButton.setOnAction(event -> {

            String paym_type_name = paymTypeList.getSelectionModel().getSelectedItem().toString();
            FXMLLoader loader = new FXMLLoader();

            if(!paym_type_name.isEmpty()){

                OrderService orderService = new OrderServiceImpl();

                List<Object[]> resultOrders = orderService.getOrderCodeAndCustLogByPaymType(paym_type_name);

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
        PaymentTypeService paymentTypeService = new PaymentTypeServiceImpl();
        List<PaymentType> allPaymentType = paymentTypeService.getAllPaymentType();

        for (PaymentType item : allPaymentType) {
            paymTypeList.getItems().add(item.getPaym_type_name());
        }

        paymTypeList.getSelectionModel().selectFirst();
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

