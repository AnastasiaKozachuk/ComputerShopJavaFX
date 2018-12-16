package Design.Controllers.Director;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import Design.SharedClasses;
import compShop.model.Category;
import compShop.model.Good;
import compShop.model.Supplier;
import compShop.service.CategoryService;
import compShop.service.GoodService;
import compShop.service.impl.CategoryServiceImpl;
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

public class GoodSupplierController {

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

                Set<Supplier> setSupplier = new HashSet<>(goodService.getSupplierOfProd(prod_article));
                List<Supplier> supplierList = new ArrayList<>(setSupplier);

                SharedClasses.tableView = createTableView(supplierList);

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

    private TableView createTableView(List<Supplier> suppliers) {

        TableView main_table = new TableView();

        ObservableList tableList = FXCollections.observableList(suppliers);

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
