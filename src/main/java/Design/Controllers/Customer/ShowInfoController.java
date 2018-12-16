package Design.Controllers.Customer;

import java.net.URL;
import java.util.ResourceBundle;

import Design.SharedClasses;
import compShop.model.Courier;
import compShop.model.Manager;
import compShop.model.UsersOrder;
import compShop.service.OrderService;
import compShop.service.impl.OrderServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ShowInfoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label order_code;

    @FXML
    private Label manager_name;

    @FXML
    private Label payment_type;

    @FXML
    private Label manager_phone;

    @FXML
    private Label courier_name;

    @FXML
    private Label delivery_tyoe;

    @FXML
    private Label result_summ;

    @FXML
    private Label rec_house_num;

    @FXML
    private Label rec_apart_num;

    @FXML
    private Label courier_phone;

    @FXML
    private Label rec_city;

    @FXML
    private Label rec_street;


    @FXML
    void initialize() {
        Object[] obj = SharedClasses.result_info;

        order_code.setText(obj[0].toString());

        try{
            rec_city.setText(obj[5].toString());
        }catch (NullPointerException e){
            rec_city.setText("-");
        }


        try{
            rec_street.setText(obj[6].toString());
        }catch (NullPointerException e){
            rec_street.setText("-");
        }



        try{
            rec_house_num.setText(obj[7].toString());
        }catch (NullPointerException e){
            rec_house_num.setText("-");
        }



        try{
            rec_apart_num.setText(obj[8].toString());
        }catch (NullPointerException e){
            rec_apart_num.setText("-");
        }

        delivery_tyoe.setText(obj[9].toString());
        payment_type.setText(obj[10].toString());
        result_summ.setText(obj[11].toString());


        OrderService orderService = new OrderServiceImpl();

        UsersOrder usersOrder = orderService.getOneById(Integer.valueOf(order_code.getText()));

        Manager manager = usersOrder.getManager_fk();
        Courier courier = usersOrder.getCourier_fk();

        if(manager!=null){
            manager_name.setText(manager.getManager_name() +" "+manager.getManager_surname()+" "+manager.getManager_patron());
            manager_phone.setText(manager.getManager_phone());
        }else{
            manager_name.setText("-");
            manager_phone.setText("-");
        }

        if(courier!=null){
            courier_name.setText(courier.getCourier_name() +" "+courier.getCourier_surname()+" "+courier.getCourier_patron());
            courier_phone.setText(courier.getCourier_phone());
        }else{
             courier_name.setText("-");
             courier_phone.setText("-");
        }

    }
}
