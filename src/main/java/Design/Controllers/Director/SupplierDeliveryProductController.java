package Design.Controllers.Director;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.Good;
import compShop.model.Supplier;
import compShop.model.SupplierPhone;
import compShop.service.SupplierService;
import compShop.service.impl.SupplierServiceImpl;
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

public class SupplierDeliveryProductController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button findButton;

    @FXML
    private ComboBox supplList;

    private SupplierService supplierService;

    @FXML
    void initialize() {
        supplierService = new SupplierServiceImpl();
        init();

        findButton.setOnAction(event -> {


            FXMLLoader loader = new FXMLLoader();

            String organizationName = supplList.getSelectionModel().getSelectedItem().toString();

            if (!organizationName.isEmpty()) {

                List<Supplier> allSupplier = supplierService.getAllSupplier();

                Supplier searchSupplier = null;

                for (Supplier item : allSupplier) {
                    if (item.getOrganization_name().equals(organizationName)) {
                        searchSupplier = item;
                    }
                }

                List<Good> deliveryProductOfSupplier = supplierService.getDeliveryProductOfSupplier(searchSupplier.getEdrpou());

                SharedClasses.tableView = createTableView(deliveryProductOfSupplier);

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

    private TableView createTableView(List<Good> goodList) {

        TableView main_table = new TableView();

        ObservableList tableList = FXCollections.observableList(goodList);

        TableColumn column1 = getColumn("Product article", "product_article");
        TableColumn column2 = getColumn("Name", "prod_full_name");
        TableColumn column3 = getColumn("Minimum stock", "min_stock");
        TableColumn column4 = getColumn("Discount delivery", "deliver_disk");
        TableColumn column5 = getColumn("Price", "price");
        TableColumn column6 = getColumn("In stock", "inStock");
        TableColumn column7 = getColumn("Category", "categoryName");
        TableColumn column8 = getColumn("Manufacturer", "manufacturerName");

        main_table.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8);
        main_table.setItems(tableList);

        return main_table;

    }

    private void init() {

        List<Supplier> allPaymentType = supplierService.getAllSupplier();

        for (Supplier item : allPaymentType) {
            supplList.getItems().add(item.getOrganization_name());
        }
        supplList.getSelectionModel().selectFirst();
    }

    private <S, T> TableColumn<S, T> getColumn(String column_name, String field_name) {

        TableColumn<S, T> newColumn = new TableColumn<>(column_name);
        newColumn.setCellValueFactory(new PropertyValueFactory<>(field_name));

        return newColumn;
    }
}
