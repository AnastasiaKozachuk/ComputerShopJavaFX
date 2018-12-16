package Design.Controllers.Customer;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.*;
import compShop.service.*;
import compShop.service.impl.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class NewOrderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add_product;

    @FXML
    private Button makeOrder_field;

    @FXML
    private ComboBox delivery__field;

    @FXML
    private TextField street_field;

    @FXML
    private Button clean_product;

    @FXML
    private TextField appartment_field;

    @FXML
    private TextArea wishes_field;

    @FXML
    private TextField house_field;

    @FXML
    private ComboBox product_name_field;

    @FXML
    private TextField city_field;

    @FXML
    private TableView product_table;

    @FXML
    private TextField quantity_field;

    @FXML
    private ComboBox payment__field;

    @FXML
    private TextField post_index__field;

    @FXML
    private Label OrderSumm;

    private List<ItemInOrder> orderList = new ArrayList<>();

    @FXML
    void initialize() {

        init();

        add_product.setOnAction(event -> {


            Object obj = product_name_field.getSelectionModel().getSelectedItem();
            String quantity = quantity_field.getText();
            FXMLLoader loader = new FXMLLoader();

            if (obj != null && !quantity.isEmpty()) {
                String product_name = product_name_field.getSelectionModel().getSelectedItem().toString();
                GoodService goodService = new GoodServiceImpl();

                Good chosenGood = null;

                for (Good good : goodService.getAllGood()) {
                    if (good.getProd_full_name().equals(product_name)) {
                        chosenGood = good;
                    }
                }
                if (chosenGood.getInStock() >= Integer.valueOf(quantity)) {

                    ItemInOrder itemInOrder = new ItemInOrder();
                    itemInOrder.setGood_fk(chosenGood);
                    itemInOrder.setOrder_quant_prod(Integer.valueOf(quantity));

                    orderList.add(itemInOrder);
                    showListInOrder();

                    BigDecimal orderSum = BigDecimal.ZERO;

                    for (ItemInOrder item : orderList) {
                        orderSum = orderSum.add(item.getTotalPrice());
                    }

                    OrderSumm.setText(orderSum.toString());

                }else{
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


                product_name_field.getSelectionModel().clearSelection();
                quantity_field.setText("");
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

        clean_product.setOnAction(event -> {

            product_table.getItems().clear();
            product_table.getColumns().clear();
            orderList = new ArrayList<>();
            OrderSumm.setText("0");

        });

        makeOrder_field.setOnAction(event -> {


            String street = street_field.getText();
            String apartm = appartment_field.getText();
            String wishes = wishes_field.getText();
            String house = house_field.getText();
            String city = city_field.getText();
            String post_index = post_index__field.getText();

            String deliveryType = delivery__field.getSelectionModel().getSelectedItem().toString();
            String paymentType = payment__field.getSelectionModel().getSelectedItem().toString();

            FXMLLoader loader = new FXMLLoader();

            if (!deliveryType.isEmpty() && !paymentType.isEmpty() && orderList.size() > 0) {

                OrderService orderService = new OrderServiceImpl();
                ItemInOrderService itemInOrderService = new ItemInOrderServiceImpl();

                UsersOrder usersOrder = new UsersOrder();
                usersOrder.setOrder_date(LocalDate.now());
                usersOrder.setAppr_deliv_date(LocalDate.now().plus(2, ChronoUnit.DAYS));

                if (!street.isEmpty()) {
                    usersOrder.setRec_street(street);
                }

                if (!apartm.isEmpty()) {
                    usersOrder.setRec_apart_num(apartm);
                }

                if (!wishes.isEmpty()) {
                    usersOrder.setAdd_wishes(wishes);
                }

                if (!house.isEmpty()) {
                    usersOrder.setRec_house_num(house);
                }

                if (!city.isEmpty()) {
                    usersOrder.setRec_city(city);
                }

                if (!post_index.isEmpty()) {
                    usersOrder.setPost_office_num(post_index);
                }

                PaymentTypeService paymentTypeService = new PaymentTypeServiceImpl();
                PaymentType paymTypeRes = null;
                for (PaymentType item : paymentTypeService.getAllPaymentType()) {
                    if (item.getPaym_type_name().equals(paymentType)) {
                        paymTypeRes = item;
                    }
                }

                DeliveryTypeService deliveryTypeService = new DeliveryTypeServiceImpl();
                DeliveryType delivTypeRes = null;
                for (DeliveryType item : deliveryTypeService.getAllDeliveryType()) {
                    if (item.getDelivery_type_name().equals(deliveryType)) {
                        delivTypeRes = item;
                    }
                }


                OrderStatusService orderStatusService = new OrderStatusServiceImpl();
                OrderStatus orderStatusRes = orderStatusService.getOneById(1);


                CustomerService customerService = new CustomerServiceImpl();
                Customer customer = customerService.getOneByLogin(SharedClasses.customer_client.getCustomer_login());


                usersOrder.setCustomer_fk(customer);
                usersOrder.setPaymentType_fk(paymTypeRes);
                usersOrder.setDeliveryType_fk(delivTypeRes);
                usersOrder.setOrderStatus_fk(orderStatusRes);

                Integer usersOrderCode = orderService.saveNewUsersOrder(usersOrder);

                usersOrder.setOrder_code(usersOrderCode);

                GoodService goodService = new GoodServiceImpl();
                List<Good> goodList = goodService.getAllGood();

                for (ItemInOrder itemInOrder : orderList) {


                    itemInOrder.setOrder_fk(usersOrder);
                    itemInOrderService.saveNewItemInOrder(itemInOrder);

                    Good good_fk = null;
                    for (Good good : goodList) {
                        if (good.getProduct_article().equals(itemInOrder.getProductArticle())) {
                            good_fk = good;
                        }
                    }

                    good_fk.setInStock(good_fk.getInStock() - itemInOrder.getOrder_quant_prod());
                    goodService.updateGood(good_fk);

                }


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


    public void showListInOrder() {
        product_table.getColumns().clear();

        ObservableList tableList = FXCollections.observableList(orderList);

        TableColumn column2 = getColumn("Product article", "productArticle");
        TableColumn column3 = getColumn("Quantity", "order_quant_prod");
        TableColumn column4 = getColumn("Total price", "totalPrice");

        product_table.getColumns().addAll(column2, column3, column4);
        product_table.setItems(tableList);

    }

    private <S, T> TableColumn<S, T> getColumn(String column_name, String field_name) {

        TableColumn<S, T> newColumn = new TableColumn<>(column_name);
        newColumn.setCellValueFactory(new PropertyValueFactory<>(field_name));

        return newColumn;
    }

    private void init() {

        PaymentTypeService paymentTypeService = new PaymentTypeServiceImpl();
        List<PaymentType> allPaymentType = paymentTypeService.getAllPaymentType();

        for (PaymentType item : allPaymentType) {
            payment__field.getItems().add(item.getPaym_type_name());
        }

        DeliveryTypeService deliveryTypeService = new DeliveryTypeServiceImpl();
        List<DeliveryType> deliveryTypes = deliveryTypeService.getAllDeliveryType();

        for (DeliveryType item : deliveryTypes) {
            delivery__field.getItems().add(item.getDelivery_type_name());
        }

        GoodService goodService = new GoodServiceImpl();
        List<Good> allGood = goodService.getAllGood();

        for (Good item : allGood) {

            product_name_field.getItems().add(item.getProd_full_name());
        }


    }
}
