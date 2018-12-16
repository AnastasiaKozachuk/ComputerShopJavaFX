package Design.Controllers.Director;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.Category;
import compShop.service.CategoryService;
import compShop.service.impl.CategoryServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateCategoryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea categDescription;

    @FXML
    private TextField nameCategory;

    @FXML
    private Button saveButton;

    @FXML
    void initialize() {

        saveButton.setOnAction(event -> {

            String name = nameCategory.getText();
            String description = categDescription.getText();
            FXMLLoader loader = new FXMLLoader();

            if (!name.isEmpty()) {
                saveButton.getScene().getWindow().hide();

                Category category = new Category();

                category.setCategoryName(name);

                if (!description.isEmpty()) {
                    category.setCategoryDescription(description);
                }


                CategoryService categoryService = new CategoryServiceImpl();
                categoryService.saveNewCategory(category);

                SharedClasses.director_window.showCategories();

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
}

