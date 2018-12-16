package Design.Controllers.Director;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.Good;
import compShop.model.Manager;
import compShop.service.GoodService;
import compShop.service.ManagerService;
import compShop.service.impl.GoodServiceImpl;
import compShop.service.impl.ManagerServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class GoodDeleteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteButton;

    @FXML
    private ComboBox goodsList;

    private GoodService goodService;

    @FXML
    void initialize() {

        goodService = new GoodServiceImpl();
        init();

        deleteButton.setOnAction(event -> {

            String productArticle = goodsList.getSelectionModel().getSelectedItem().toString();
            FXMLLoader loader = new FXMLLoader();
            if (!productArticle.isEmpty()) {

                deleteButton.getScene().getWindow().hide();

                List<Good> allGood = goodService.getAllGood();

                for (Good item : allGood) {
                    if (item.getProduct_article().equals(productArticle)) {
                        goodService.deleteGood(item);
                    }
                }

                SharedClasses.director_window.showGoods();

            } else {
                loader.setLocation(getClass().getResource("/FXML/WrongUser.fxml"));
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
            }

        });

    }

    private void init() {

        List<Good> allGood = goodService.getAllGood();

        for (Good item : allGood) {

            goodsList.getItems().add(item.getProduct_article());
        }
        goodsList.getSelectionModel().selectFirst();
    }
}
