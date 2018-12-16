package Design.Controllers.Courier;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.Courier;
import compShop.service.CourierService;
import compShop.service.ManagerService;
import compShop.service.impl.CourierServiceImpl;
import compShop.service.impl.ManagerServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CourierHeaderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button find_courier_button;

    @FXML
    private Button count_finished_orders;

    @FXML
    void initialize() {
        find_courier_button.setOnAction(event -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/Courier/CourierByOrderCode.fxml"));
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

        count_finished_orders.setOnAction(event -> {

            CourierService courierService = new CourierServiceImpl();
            List<Object[]> courierList = courierService.getCountFinishedOrdersForEachCourier();

            Long result = 0l;
            for(Object[] o: courierList){
                Integer courierId = (Integer)o[0];
                if(courierId == SharedClasses.courier_client.getCourier_Id()){
                    result = (Long) o[2];
                }
            }
            SharedClasses.number = result;
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
