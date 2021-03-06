package Design.Controllers.Director;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class DeleteCategoryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox listCategory;

    @FXML
    private Button deleteButton;

    private CategoryService categoryService;

    @FXML
    void initialize() {

        categoryService = new CategoryServiceImpl();
        init();

        deleteButton.setOnAction(event -> {

            String categoryName = listCategory.getSelectionModel().getSelectedItem().toString();
            FXMLLoader loader = new FXMLLoader();
            if (!categoryName.isEmpty()) {

                deleteButton.getScene().getWindow().hide();

                List<Category> allCateg = categoryService.getAllCategory();

                for (Category item : allCateg) {
                    if (item.getCategoryName().equals(categoryName)) {
                        categoryService.deleteCategory(item);
                    }
                }

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

    private void init() {

        List<Category> allCateg = categoryService.getAllCategory();

        for (Category item : allCateg) {

            listCategory.getItems().add(item.getCategoryName());
        }
        listCategory.getSelectionModel().selectFirst();
    }
}

