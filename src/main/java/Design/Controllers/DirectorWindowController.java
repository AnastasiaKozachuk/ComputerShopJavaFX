package Design.Controllers;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

@SuppressWarnings("unchecked")
public class DirectorWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView main_table;

    @FXML
    private AnchorPane header_view;

    @FXML
    private VBox main_vbox;

    @FXML
    private Button couriers;

    @FXML
    private Button suppliers_phones;

    @FXML
    private Button clients;

    @FXML
    private Button menegers;

    @FXML
    private Button suppliers;

    @FXML
    private Button manufacturers;

    @FXML
    private Button goods;

    @FXML
    private Button payment_types;

    @FXML
    private Button list_item_in_orders;

    @FXML
    private Button deliveries;

    @FXML
    private Button order_status;

    @FXML
    private Button delivery_types;

    @FXML
    private Button orders;

    @FXML
    private Button category;

    @FXML
    void initialize() {


        init();


        SharedClasses.director_window = this;

        couriers.setOnAction(event -> {
            showCouriers();
            setHeader("/FXML/Director/CourierHeader.fxml");

        });

        suppliers_phones.setOnAction(event -> {
            showSupplierPhones();
            setHeader("/FXML/Director/SuppliersPhoneHeader.fxml");
        });

        clients.setOnAction(event -> {
            showClients();
            header_view.getChildren().clear();
        });

        menegers.setOnAction(event -> {
            showManagers();
            setHeader("/FXML/Director/ManagerHeader.fxml");
        });


        suppliers.setOnAction(event -> {
            showSuppliers();
            setHeader("/FXML/Director/SupplierHeader.fxml");
        });

        manufacturers.setOnAction(event -> {
            showManufacturers();
            setHeader("/FXML/Director/ManufacturerHeader.fxml");
        });

        goods.setOnAction(event -> {
            showGoods();
            setHeader("/FXML/Director/GoodHeader.fxml");
        });

        payment_types.setOnAction(event -> {
            showPaymentTypes();
            setHeader("/FXML/Director/PaymentTypeHeader.fxml");
        });

        list_item_in_orders.setOnAction(event -> {
            showListInOrder();
            header_view.getChildren().clear();
        });

        deliveries.setOnAction(event -> {
            showDeliveries();
            setHeader("/FXML/Director/DeliveryHeader.fxml");
        });

        order_status.setOnAction(event -> {
            showOrderStatus();
            setHeader("/FXML/Director/OrderStatusHeader.fxml");
        });

        delivery_types.setOnAction(event -> {
            showDeliveryTypes();
            setHeader("/FXML/Director/DeliveryTypeHeader.fxml");
        });

        orders.setOnAction(event -> {
            showOrders();
            setHeader("/FXML/Director/OrderHeader.fxml");
        });

        category.setOnAction(event -> {
            showCategories();
            setHeader("/FXML/Director/CategoryHeader.fxml");
        });


    }


    private void setHeader(String path ){
        header_view.getChildren().clear();
        try {
            AnchorPane newLoadedPane =  FXMLLoader.load(getClass().getResource(path));
            header_view.getChildren().add(newLoadedPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showListInOrder() {
        main_table.getItems().clear();
        main_table.getColumns().clear();
        ItemInOrderService itemInOrderService = new ItemInOrderServiceImpl();

        List<ItemInOrder> allList = itemInOrderService.getAllItemInOrder();
        ObservableList tableList = FXCollections.observableList(allList);

        TableColumn column1 = getColumn("Order code", "orderCode");
        TableColumn column2 = getColumn("Product article", "productArticle");
        TableColumn column3 = getColumn("Quantity", "order_quant_prod");
        TableColumn column4 = getColumn("Total price", "totalPrice");

        main_table.getColumns().addAll(column1, column2, column3, column4);
        main_table.setItems(tableList);

    }

    public void showOrders() {
        main_table.getItems().clear();
        main_table.getColumns().clear();
        OrderService orderService = new OrderServiceImpl();

        List<UsersOrder> allList = orderService.getAllUsersOrder();
        ObservableList tableList = FXCollections.observableList(allList);

        TableColumn column1 = getColumn("Order code", "order_code");
        TableColumn column2 = getColumn("Order date", "order_date");
        TableColumn column3 = getColumn("City", "rec_city");
        TableColumn column4 = getColumn("Street", "rec_street");
        TableColumn column5 = getColumn("House number", "rec_house_num");
        TableColumn column6 = getColumn("Apartment number", "rec_apart_num");
        TableColumn column7 = getColumn("Post office number", "post_office_num");
        TableColumn column8 = getColumn("Payment date", "payment_date");
        TableColumn column9 = getColumn("Approximate deliv date", "appr_deliv_date");
        TableColumn column10 = getColumn("Actual delivery date", "actual_deliv_date");
        TableColumn column11 = getColumn("Wishes", "add_wishes");
        TableColumn column12 = getColumn("Login", "customerLogin");
        TableColumn column13 = getColumn("Payment type", "paymantTypeName");
        TableColumn column14 = getColumn("Delivery type", "deliveryTypeName");
        TableColumn column15 = getColumn("Courier", "courierName");
        TableColumn column16 = getColumn("Manager", "managerName");
        TableColumn column17 = getColumn("Order status", "statusName");

        main_table.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8, column9, column10, column11, column12, column13, column14, column15, column16, column17);
        main_table.setItems(tableList);

    }

    public void showOrderStatus() {
        main_table.getItems().clear();
        main_table.getColumns().clear();
        OrderStatusService ordStatusService = new OrderStatusServiceImpl();

        List<OrderStatus> allList = ordStatusService.getAllOrderStatus();
        ObservableList tableList = FXCollections.observableList(allList);

        TableColumn column1 = getColumn("Id", "order_status_id");
        TableColumn column2 = getColumn("Name", "order_status_name");

        main_table.getColumns().addAll(column1, column2);
        main_table.setItems(tableList);

    }

    public void showDeliveries() {
        main_table.getItems().clear();
        main_table.getColumns().clear();
        DeliveryService deliveryService = new DeliveryServiceImpl();

        List<Delivery> allList = deliveryService.getAllDelivery();
        ObservableList tableList = FXCollections.observableList(allList);

        TableColumn column1 = getColumn("EDRPOU", "edrpou_fk");
        TableColumn column2 = getColumn("Product article", "prod_article_fk");

        main_table.getColumns().addAll(column1, column2);
        main_table.setItems(tableList);
    }

    public void showGoods() {
        main_table.getItems().clear();
        main_table.getColumns().clear();
        GoodService goodService = new GoodServiceImpl();

        List<Good> allList = goodService.getAllGood();
        ObservableList tableList = FXCollections.observableList(allList);

        TableColumn column1 = getColumn("Product article", "product_article");
        TableColumn column2 = getColumn("Name", "prod_full_name");
        TableColumn column3 = getColumn("Minimum stock", "min_stock");
        TableColumn column4 = getColumn("Discount delivery", "deliver_disk");
        TableColumn column5 = getColumn("Price", "price");
        TableColumn column6 = getColumn("In stock", "inStock");
        TableColumn column7 = getColumn("Category", "categoryName");
        TableColumn column8 = getColumn("Manufacturer", "manufacturerName");

        main_table.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8);
        main_table.setItems(tableList);

    }


    public void showManufacturers() {
        main_table.getItems().clear();
        main_table.getColumns().clear();
        ManufacturerService manufactService = new ManufacturerServiceImpl();

        List<Manufacturer> allList = manufactService.getAllManufacturer();
        ObservableList tableList = FXCollections.observableList(allList);

        TableColumn column1 = getColumn("Id", "manufact_id");
        TableColumn column2 = getColumn("Name", "manufact_name");

        main_table.getColumns().addAll(column1, column2);
        main_table.setItems(tableList);

    }

    public void showSuppliers() {
        main_table.getItems().clear();
        main_table.getColumns().clear();
        SupplierService supplierService = new SupplierServiceImpl();

        List<Supplier> allList = supplierService.getAllSupplier();
        ObservableList tableList = FXCollections.observableList(allList);

        TableColumn column1 = getColumn("EDRPOU", "edrpou");
        TableColumn column2 = getColumn("Organization name", "organization_name");
        TableColumn column3 = getColumn("Contact person name", "contact_pers_name");
        TableColumn column4 = getColumn("Contact person surname", "contact_pers_surname");
        TableColumn column5 = getColumn("Contact person patron", "contact_pers_patron");
        TableColumn column6 = getColumn("City", "sup_city");
        TableColumn column7 = getColumn("Street", "sup_street");
        TableColumn column8 = getColumn("House number", "sup_house_num");
        TableColumn column9 = getColumn("Apartment number", "sup_apar_num");
        TableColumn column10 = getColumn("Web page", "sup_web_page");
        TableColumn column11 = getColumn("MFI", "sup_mfi");
        TableColumn column12 = getColumn("Currency account", "sup_currency_account");

        main_table.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8, column9, column10, column11, column12);
        main_table.setItems(tableList);
    }

    public void showClients() {
        main_table.getItems().clear();
        main_table.getColumns().clear();
        CustomerService customerService = new CustomerServiceImpl();

        List<Customer> allList = customerService.getAllCustomer();
        ObservableList tableList = FXCollections.observableList(allList);

        TableColumn column1 = getColumn("Login", "customer_login");
        TableColumn column2 = getColumn("Name", "cust_name");
        TableColumn column3 = getColumn("Surname", "cust_surname");
        TableColumn column4 = getColumn("Patron", "cust_patron");
        TableColumn column5 = getColumn("Phone", "cust_phone");
        TableColumn column6 = getColumn("Sex", "cust_sex");
        TableColumn column7 = getColumn("Register date", "register_date");
        TableColumn column8 = getColumn("Birthday", "cust_birthday");

        main_table.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8);
        main_table.setItems(tableList);

    }

    public void showSupplierPhones() {
        main_table.getItems().clear();
        main_table.getColumns().clear();
        SupplierPhoneService supplPhonesService = new SupplierPhoneServiceImpl();

        List<SupplierPhone> allList = supplPhonesService.getAllSupplierPhone();

        ObservableList tableList = FXCollections.observableList(allList);
        TableColumn column1 = getColumn("Phone", "phone_numb");
        TableColumn column2 = getColumn("EDRPOU", "edrpou");

        main_table.getColumns().addAll(column1, column2);
        main_table.setItems(tableList);

    }


    public void showManagers() {
        main_table.getItems().clear();
        main_table.getColumns().clear();
        ManagerService courierService = new ManagerServiceImpl();

        List<Manager> allList = courierService.getAllManager();
        ObservableList tableList = FXCollections.observableList(allList);

        TableColumn column1 = getColumn("Id", "manager_id");
        TableColumn column2 = getColumn("Name", "manager_name");
        TableColumn column3 = getColumn("Surname", "manager_surname");
        TableColumn column4 = getColumn("Patron", "manager_patron");
        TableColumn column5 = getColumn("Phone", "manager_phone");

        main_table.getColumns().addAll(column1, column2, column3, column4, column5);
        main_table.setItems(tableList);
    }

    public void showCouriers() {
        main_table.getItems().clear();
        main_table.getColumns().clear();
        CourierService courierService = new CourierServiceImpl();

        List<Courier> allList = courierService.getAllCourier();
        ObservableList tableList = FXCollections.observableList(allList);

        TableColumn column1 = getColumn("Id", "courier_Id");
        TableColumn column2 = getColumn("Surname", "courier_name");
        TableColumn column3 = getColumn("Name", "courier_surname");
        TableColumn column4 = getColumn("Patron", "courier_patron");
        TableColumn column5 = getColumn("Phone", "courier_phone");

        main_table.getColumns().addAll(column1, column2, column3, column4, column5);
        main_table.setItems(tableList);
    }

    public void showDeliveryTypes() {
        main_table.getItems().clear();
        main_table.getColumns().clear();
        DeliveryTypeService delivTypeService = new DeliveryTypeServiceImpl();

        List<DeliveryType> allList = delivTypeService.getAllDeliveryType();
        ObservableList tableList = FXCollections.observableList(allList);

        TableColumn column1 = getColumn("Id", "delivery_type_id");
        TableColumn column2 = getColumn("Name", "delivery_type_name");

        main_table.getColumns().addAll(column1, column2);
        main_table.setItems(tableList);
    }

    public void showPaymentTypes() {
        main_table.getItems().clear();
        main_table.getColumns().clear();
        PaymentTypeService paymTypeService = new PaymentTypeServiceImpl();

        List<PaymentType> allList = paymTypeService.getAllPaymentType();
        ObservableList tableList = FXCollections.observableList(allList);

        TableColumn column1 = getColumn("Id", "paym_type_id");
        TableColumn column2 = getColumn("Name", "paym_type_name");

        main_table.getColumns().addAll(column1, column2);
        main_table.setItems(tableList);

    }

    public void showCategories() {
        main_table.getItems().clear();
        main_table.getColumns().clear();
        CategoryService categoryService = new CategoryServiceImpl();

        List<Category> allList = categoryService.getAllCategory();
        ObservableList tableList = FXCollections.observableList(allList);

        TableColumn column1 = getColumn("Id", "categoryId");
        TableColumn column2 = getColumn("Name", "categoryName");
        TableColumn column3 = getColumn("Description", "categoryDescription");

        main_table.getColumns().addAll(column1, column2, column3);
        main_table.setItems(tableList);
    }

    private void init() {
        showCategories();
        setHeader("/FXML/Director/CategoryHeader.fxml");
    }

    private <S, T> TableColumn<S, T> getColumn(String column_name, String field_name) {

        TableColumn<S, T> newColumn = new TableColumn<>(column_name);
        newColumn.setCellValueFactory(new PropertyValueFactory<>(field_name));

        return newColumn;
    }

}

