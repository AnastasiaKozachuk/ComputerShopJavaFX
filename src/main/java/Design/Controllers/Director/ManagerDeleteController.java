package Design.Controllers.Director;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.Manager;
import compShop.service.ManagerService;
import compShop.service.impl.ManagerServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ManagerDeleteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteButton;

    @FXML
    private ComboBox listManager;

    private ManagerService managerService;

    @FXML
    void initialize() {

        managerService = new ManagerServiceImpl();
        init();

        deleteButton.setOnAction(event -> {

            String managerName = listManager.getSelectionModel().getSelectedItem().toString();
            FXMLLoader loader = new FXMLLoader();
            if (!managerName.isEmpty()) {

                deleteButton.getScene().getWindow().hide();

                List<Manager> allManager = managerService.getAllManager();

                for (Manager item : allManager) {
                    if (item.getManager_surname().equals(managerName)) {
                        managerService.deleteManager(item);
                    }
                }

                SharedClasses.director_window.showManagers();

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

        List<Manager> allCourier = managerService.getAllManager();

        for (Manager item : allCourier) {

            listManager.getItems().add(item.getManager_surname());
        }
        listManager.getSelectionModel().selectFirst();
    }
}

