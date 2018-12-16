package Design.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Design.SharedClasses;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class TableViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView main_table;

    @FXML
    void initialize() {

        main_table.getColumns().addAll(SharedClasses.tableView.getColumns());
        main_table.getItems().addAll(SharedClasses.tableView.getItems());

    }
}

