package Design.Controllers.Manager;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.Category;
import compShop.model.Good;
import compShop.model.Manufacturer;
import compShop.service.CategoryService;
import compShop.service.GoodService;
import compShop.service.ManufacturerService;
import compShop.service.impl.CategoryServiceImpl;
import compShop.service.impl.GoodServiceImpl;
import compShop.service.impl.ManufacturerServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GoodUpdateControler {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField instock_field;

    @FXML
    private ComboBox deliv_disc_field;

    @FXML
    private TextField minstock_field;

    @FXML
    private ComboBox categoryList;

    @FXML
    private Button saveButton;

    @FXML
    private ComboBox articleIdList;

    @FXML
    private TextField prod_name_field;

    @FXML
    private TextField price_field;

    @FXML
    private ComboBox manufactList;

    private GoodService goodService;

    @FXML
    void initialize() {

        goodService = new GoodServiceImpl();
        init();

        saveButton.setOnAction(event -> {


            String instock = instock_field.getText();
            String pro_article = articleIdList.getSelectionModel().getSelectedItem().toString();
            String minstock = minstock_field.getText();
            String prod_name = prod_name_field.getText();
            String price = price_field.getText();
            String category_name = categoryList.getSelectionModel().getSelectedItem().toString();
            String delivery_disc = deliv_disc_field.getSelectionModel().getSelectedItem().toString();
            String manufact_name = manufactList.getSelectionModel().getSelectedItem().toString();
            FXMLLoader loader = new FXMLLoader();

            if (!instock.isEmpty() && !pro_article.isEmpty() && !minstock.isEmpty() && !prod_name.isEmpty() && !price.isEmpty() && !category_name.isEmpty() && !delivery_disc.isEmpty() && !manufact_name.isEmpty()) {
                saveButton.getScene().getWindow().hide();

                Boolean discount = null;

                if (delivery_disc.equals("Так")) {
                    discount = true;
                } else if (delivery_disc.equals("Hi")) {
                    discount = false;
                }


                Category category = null;
                CategoryService categoryService = new CategoryServiceImpl();

                for (Category item : categoryService.getAllCategory()) {
                    if (item.getCategoryName().equals(category_name)) {
                        category = item;
                    }
                }

                Manufacturer manufacturer = null;
                ManufacturerService manufacturerService = new ManufacturerServiceImpl();

                for (Manufacturer item : manufacturerService.getAllManufacturer()) {
                    if (item.getManufact_name().equals(manufact_name)) {
                        manufacturer = item;
                    }
                }

                Good good = new Good(pro_article, prod_name, Integer.valueOf(minstock), discount, new BigDecimal(price), Integer.valueOf(instock), manufacturer, category);
                goodService.updateGood(good);

                SharedClasses.manager_window.showGoods();
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

        articleIdList.setOnAction(event -> {
            setInformation((String) articleIdList.getSelectionModel().getSelectedItem());
        });

    }

    private void setInformation(String productArticle){

        Good good = goodService.getOneById(productArticle);

        instock_field.setText(good.getInStock().toString());
        minstock_field.setText(good.getMin_stock().toString());
        prod_name_field.setText(good.getProd_full_name());
        price_field.setText(good.getPrice().toString());

        if(good.getDeliver_disk()){
            deliv_disc_field.getSelectionModel().select(0);
        }else{
            deliv_disc_field.getSelectionModel().select(1);
        }

        manufactList.getSelectionModel().select(good.getManufacturerName());
        categoryList.getSelectionModel().select(good.getCategoryName());

    }

    private void init() {

        ManufacturerService manufacturerService = new ManufacturerServiceImpl();
        CategoryService categoryService = new CategoryServiceImpl();

        List<Manufacturer> manufacturerList = manufacturerService.getAllManufacturer();
        List<Category> allCategory = categoryService.getAllCategory();

        for (Manufacturer manufacturer : manufacturerList) {
            manufactList.getItems().add(manufacturer.getManufact_name());
        }

        for (Category category : allCategory) {
            categoryList.getItems().add(category.getCategoryName());
        }


        List<Good> goodList = goodService.getAllGood();

        for(Good good: goodList){
            articleIdList.getItems().add(good.getProduct_article());
        }

        deliv_disc_field.getItems().add("Так");
        deliv_disc_field.getItems().add("Hi");

        articleIdList.getSelectionModel().selectFirst();
        setInformation((String) articleIdList.getSelectionModel().getSelectedItem());
    }
}

