package Design.Controllers.Manager;

import java.net.URL;
import java.util.ResourceBundle;

import Design.SharedClasses;
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
    private Label payment_type;

    @FXML
    private Label delivery_tyoe;

    @FXML
    private Label result_summ;

    @FXML
    private Label cust_name;

    @FXML
    private Label cust_surname;

    @FXML
    private Label rec_house_num;

    @FXML
    private Label rec_apart_num;

    @FXML
    private Label cust_phone;

    @FXML
    private Label cust_patron;

    @FXML
    private Label rec_city;

    @FXML
    private Label rec_street;


    @FXML
    void initialize() {

        Object[] obj = SharedClasses.result_info;

        order_code.setText(obj[0].toString());
        cust_name.setText(obj[1].toString());
        cust_surname.setText(obj[2].toString());
        cust_patron.setText(obj[3].toString());
        cust_phone.setText(obj[4].toString());


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

    }
}

