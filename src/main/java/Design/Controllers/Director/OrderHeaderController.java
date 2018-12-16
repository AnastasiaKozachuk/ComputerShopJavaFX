package Design.Controllers.Director;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.service.OrderService;
import compShop.service.impl.OrderServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;

public class OrderHeaderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button earned_money;

    @FXML
    private Button finished_order;

    private OrderService orderService ;

    @FXML
    void initialize() {

        orderService = new OrderServiceImpl();

        earned_money.setOnAction(event -> {

            BigDecimal earnedMoney = orderService.getEarnedMoneyPerMonth();
            SharedClasses.number = earnedMoney;

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/ResultWindow.fxml"));
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

        finished_order.setOnAction(event -> {

            Long finishedOrder = orderService.getCountOfFinishedOrderPerMonth();
            SharedClasses.number = finishedOrder;

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/ResultWindow.fxml"));
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

}
