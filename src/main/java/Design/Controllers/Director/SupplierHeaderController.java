package Design.Controllers.Director;

import Design.SharedClasses;
import compShop.model.Good;
import compShop.model.Supplier;
import compShop.service.SupplierService;
import compShop.service.impl.SupplierServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierHeaderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Button all_supplier_phones;

    @FXML
    private Button delete_button;

    @FXML
    private Button create_button;

    @FXML
    private Button supply_all_goods;

    @FXML
    private Button supply_0goods;

    @FXML
    private Button all_supplier_goods;

    @FXML
    private Button supply_more1_goods;


    private SupplierService supplierService;

    @FXML
    void initialize() {

        supplierService = new SupplierServiceImpl();

        create_button.setOnAction(event -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/Director/SupplierCreate.fxml"));
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

        delete_button.setOnAction(event -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/Director/SupplierDelete.fxml"));
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

        supply_all_goods.setOnAction(event -> {

            List<Supplier> allSuppl = supplierService.findSuppliersOfAllProducts();
            SharedClasses.tableView = createTableView(allSuppl);


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/TableView.fxml"));
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

        supply_0goods.setOnAction(event -> {
            List<Supplier> allSuppl = supplierService.findSuppliersOfZeroProd();
            SharedClasses.tableView = createTableView(allSuppl);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/TableView.fxml"));
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

        all_supplier_goods.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/Director/SupplierDeliveryProduct.fxml"));
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

        supply_more1_goods.setOnAction(event -> {

            List<Supplier> allSuppl = supplierService.findSuppliersMoreThanOneProd();
            SharedClasses.tableView = createTableView(allSuppl);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/TableView.fxml"));
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

        all_supplier_phones.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/Director/SuppliersPhonesFind.fxml"));
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

    private TableView createTableView(List<Supplier> supplierList) {

        TableView main_table = new TableView();

        ObservableList tableList = FXCollections.observableList(supplierList);

        TableColumn column1 = getColumn("EDRPOU", "edrpou");
        TableColumn column2 = getColumn("Organization name", "organization_name");
        TableColumn column3 = getColumn("Contact person name", "contact_pers_name");
        TableColumn column4 = getColumn("Contact person surname", "contact_pers_surname");
        TableColumn column5 = getColumn("Contact person patron", "contact_pers_patron");
        TableColumn column6 = getColumn("City", "sup_city");
        TableColumn column7 = getColumn("Street", "sup_street");
        TableColumn column8 = getColumn("House number", "sup_house_num");
        TableColumn column9 = getColumn("Apartment number", "sup_apar_num");
        TableColumn column10 = getColumn("Web page", "sup_web_page");
        TableColumn column11 = getColumn("MFI", "sup_mfi");
        TableColumn column12 = getColumn("Currency account", "sup_currency_account");

        main_table.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8, column9, column10, column11, column12);
        main_table.setItems(tableList);

        return main_table;

    }

    private <S, T> TableColumn<S, T> getColumn(String column_name, String field_name) {

        TableColumn<S, T> newColumn = new TableColumn<>(column_name);
        newColumn.setCellValueFactory(new PropertyValueFactory<>(field_name));

        return newColumn;
    }

}
