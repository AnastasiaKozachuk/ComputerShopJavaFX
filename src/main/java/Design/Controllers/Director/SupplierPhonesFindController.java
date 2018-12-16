package Design.Controllers.Director;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.Good;
import compShop.model.Manufacturer;
import compShop.model.Supplier;
import compShop.model.SupplierPhone;
import compShop.service.ManufacturerService;
import compShop.service.SupplierService;
import compShop.service.impl.ManufacturerServiceImpl;
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

public class SupplierPhonesFindController {

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

                List<SupplierPhone> allSupplierPhones = supplierService.getAllSupplierPhonesById(searchSupplier.getEdrpou());

                SharedClasses.tableView = createTableView(allSupplierPhones);

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

    private TableView createTableView(List<SupplierPhone> supplierPhones) {

        TableView main_table = new TableView();

        ObservableList tableList = FXCollections.observableList(supplierPhones);

        TableColumn column1 = getColumn("Phone", "phone_numb");
        TableColumn column2 = getColumn("EDRPOU", "edrpou");

        main_table.getColumns().addAll(column1, column2);
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
