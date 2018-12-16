package Design.Controllers.Manager;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.service.ManagerService;
import compShop.service.impl.ManagerServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ManagerHeaderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button findManagerByOrderCode;

    @FXML
    private Button countFinishedOrdersForManager;

    @FXML
    void initialize() {
        findManagerByOrderCode.setOnAction(event -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/Manager/ManagerByOrderCode.fxml"));
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

        countFinishedOrdersForManager.setOnAction(event -> {

            ManagerService managerService = new ManagerServiceImpl();
            List<Object[]>  manageList = managerService.getCountFinishedOrdersForEachManager();

            Integer result = 0;
            for(Object[] o: manageList){
                Integer manageId = (Integer)o[0];
                if(manageId == SharedClasses.manager_client.getManager_id()){
                   result = (Integer) o[2];
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
