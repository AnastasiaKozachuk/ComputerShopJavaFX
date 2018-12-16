package Design.Controllers.Manager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Design.POJO.GoodsForOrderClass;
import Design.SharedClasses;
import compShop.model.Manager;
import compShop.model.UsersOrder;
import compShop.service.ManagerService;
import compShop.service.OrderService;
import compShop.service.impl.ManagerServiceImpl;
import compShop.service.impl.OrderServiceImpl;
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

public class ManagerByOrderCodeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button findButton;

    @FXML
    private ComboBox listOrderCode;


    @FXML
    void initialize() {
        init();

        findButton.setOnAction(event -> {

            ManagerService managerService = new ManagerServiceImpl();
            String order_code = listOrderCode.getSelectionModel().getSelectedItem().toString();
            FXMLLoader loader = new FXMLLoader();
            if (!order_code.isEmpty()) {

                Manager manager = managerService.getOneByOrderCode(Integer.valueOf(order_code));
                List<Manager> managerList = new ArrayList<>();
                managerList.add(manager);
                SharedClasses.tableView = createTableView(managerList);

                loader.setLocation(getClass().getResource("/FXML/TableView.fxml"));

            } else {
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

        for (UsersOrder item : allUsersOrder) {
            listOrderCode.getItems().add(item.getOrder_code());
        }

        listOrderCode.getSelectionModel().selectFirst();
    }

    private TableView createTableView(List<Manager> managerList) {

        TableView main_table = new TableView();

        ObservableList tableList = FXCollections.observableList(managerList);

        TableColumn column1 = getColumn("Id", "manager_id");
        TableColumn column2 = getColumn("Name", "manager_name");
        TableColumn column3 = getColumn("Surname", "manager_surname");
        TableColumn column4 = getColumn("Patron", "manager_patron");
        TableColumn column5 = getColumn("Phone", "manager_phone");

        main_table.getColumns().addAll(column1, column2, column3, column4, column5);
        main_table.setItems(tableList);

        return main_table;

    }

    private <S, T> TableColumn<S, T> getColumn(String column_name, String field_name) {

        TableColumn<S, T> newColumn = new TableColumn<>(column_name);
        newColumn.setCellValueFactory(new PropertyValueFactory<>(field_name));

        return newColumn;
    }

}
