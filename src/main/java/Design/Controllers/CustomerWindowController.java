package Design.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class CustomerWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button allOrders;

    @FXML
    private AnchorPane Main_pane;

    @FXML
    private Button viewProfile;

    @FXML
    private Button newOrder;


    @FXML
    void initialize() {

        init();

        allOrders.setOnAction(event -> {

            Main_pane.getChildren().clear();
            try {
                AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/FXML/Customer/CustomerOrders.fxml"));
                Main_pane.getChildren().add(newLoadedPane);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        viewProfile.setOnAction(event -> {
            Main_pane.getChildren().clear();
            try {
                AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/FXML/Customer/ProfileInfo.fxml"));
                Main_pane.getChildren().add(newLoadedPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        newOrder.setOnAction(event -> {
            Main_pane.getChildren().clear();
            try {
                AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/FXML/Customer/NewOrder.fxml"));
                Main_pane.getChildren().add(newLoadedPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


    private void init() {
        Main_pane.getChildren().clear();
        try {
            AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/FXML/Customer/ProfileInfo.fxml"));
            Main_pane.getChildren().add(newLoadedPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
