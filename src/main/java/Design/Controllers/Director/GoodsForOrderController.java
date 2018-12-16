package Design.Controllers.Director;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

import Design.POJO.GoodsForOrderClass;
import Design.SharedClasses;
import compShop.model.Good;
import compShop.model.ItemInOrder;
import compShop.model.UsersOrder;
import compShop.service.GoodService;
import compShop.service.OrderService;
import compShop.service.impl.GoodServiceImpl;
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

public class GoodsForOrderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button findButton;

    @FXML
    private ComboBox orderCodeList;


    private GoodService goodService;

    @FXML
    void initialize() {

        goodService = new GoodServiceImpl();
        init();

        findButton.setOnAction(event -> {

            String order_code = orderCodeList.getSelectionModel().getSelectedItem().toString();
            FXMLLoader loader = new FXMLLoader();
            if(!order_code.isEmpty()){

                List<Object[]> goodsForOrdrer = goodService.getGoodsForOrder(Integer.valueOf(order_code));
                List<GoodsForOrderClass> goodsForOrderClasses = transformClass(goodsForOrdrer);
                SharedClasses.tableView = createTableView(goodsForOrderClasses);

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

    private List<GoodsForOrderClass> transformClass(List<Object[]> goodsForOrdrer) {

        List<GoodsForOrderClass> goodsForOrderClasses = new ArrayList<>();

        for(Object[] o: goodsForOrdrer){
            GoodsForOrderClass goodsForOrderClass = new GoodsForOrderClass((String)o[0],(String)o[1],(Integer)o[2],(BigDecimal)o[3],(BigDecimal)o[4]);
            goodsForOrderClasses.add(goodsForOrderClass);
        }

        return goodsForOrderClasses;
    }

    private void init() {

        OrderService orderService = new OrderServiceImpl();
        List<UsersOrder> allUsersOrder = orderService.getAllUsersOrder();

        for (UsersOrder item : allUsersOrder) {
            orderCodeList.getItems().add(item.getOrder_code());
        }

        orderCodeList.getSelectionModel().selectFirst();
    }

    private TableView createTableView(List<GoodsForOrderClass> goodsForOrderClasses) {

        TableView main_table = new TableView();

        ObservableList tableList = FXCollections.observableList(goodsForOrderClasses);


        TableColumn column1 = getColumn("Product name", "product_name");
        TableColumn column2 = getColumn("Product article", "product_article");
        TableColumn column3 = getColumn("Quantity", "prod_quantity");
        TableColumn column4 = getColumn("Price per unit", "price_per_unit");
        TableColumn column5 = getColumn("Whole price", "whole_price");

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
