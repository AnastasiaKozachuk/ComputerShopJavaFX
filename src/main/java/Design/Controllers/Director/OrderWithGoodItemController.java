package Design.Controllers.Director;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import Design.SharedClasses;
import compShop.model.Good;
import compShop.model.ItemInOrder;
import compShop.model.Supplier;
import compShop.service.GoodService;
import compShop.service.impl.GoodServiceImpl;
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

public class OrderWithGoodItemController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox prodArtList;

    @FXML
    private Button findButton;

    private GoodService goodService;

    @FXML
    void initialize() {

        goodService = new GoodServiceImpl();
        init();

        findButton.setOnAction(event -> {

            String prod_article = prodArtList.getSelectionModel().getSelectedItem().toString();
            FXMLLoader loader = new FXMLLoader();
            if(!prod_article.isEmpty()){

                Set<ItemInOrder> itemInOrders = new HashSet<>(goodService.getAllOrderWithItem(prod_article));
                List<ItemInOrder> itemInOrdersList = new ArrayList<>(itemInOrders);

                SharedClasses.tableView = createTableView(itemInOrdersList);

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

    private void init() {

        List<Good> allGood = goodService.getAllGood();

        for (Good item : allGood) {
            prodArtList.getItems().add(item.getProduct_article());
        }

        prodArtList.getSelectionModel().selectFirst();
    }

    private TableView createTableView(List<ItemInOrder> itemInOrders) {

        TableView main_table = new TableView();

        ObservableList tableList = FXCollections.observableList(itemInOrders);


        TableColumn column1 = getColumn("Order code", "orderCode");
        TableColumn column2 = getColumn("Product article", "productArticle");
        TableColumn column3 = getColumn("Quantity", "order_quant_prod");
        TableColumn column4 = getColumn("Total price", "totalPrice");

        main_table.getColumns().addAll(column1, column2, column3, column4);
        main_table.setItems(tableList);

        return main_table;

    }

    private <S, T> TableColumn<S, T> getColumn(String column_name, String field_name) {

        TableColumn<S, T> newColumn = new TableColumn<>(column_name);
        newColumn.setCellValueFactory(new PropertyValueFactory<>(field_name));

        return newColumn;
    }
}

