package Design.Controllers.Manager;

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

public class ChooseUserManagerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox listCategory;

    @FXML
    private Button chooseButton;

    private ManagerService managerService;

    @FXML
    void initialize() {
        managerService = new ManagerServiceImpl();
        init();

        chooseButton.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            String selectedManager = listCategory.getSelectionModel().getSelectedItem().toString();

            if (!selectedManager.isEmpty()) {
                chooseButton.getScene().getWindow().hide();
                List<Manager> managerList = managerService.getAllManager();
                Manager managerPerson = null;
                for (Manager manager : managerList) {
                    if (manager.getManager_surname().equals(selectedManager)) {
                        managerPerson = manager;
                    }
                }


                SharedClasses.manager_client = managerPerson;
                loader.setLocation(getClass().getResource("/FXML/ManagerWindow.fxml"));
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

    private void init() {

        List<Manager> managerList = managerService.getAllManager();

        for (Manager manager : managerList) {
            listCategory.getItems().add(manager.getManager_surname());
        }

        listCategory.getSelectionModel().selectFirst();

    }


}

