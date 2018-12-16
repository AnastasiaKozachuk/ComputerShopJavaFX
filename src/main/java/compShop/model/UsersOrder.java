package compShop.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class UsersOrder {

    @Column(name = "order_code")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_code;

    @Column(name = "order_date")
    private LocalDate order_date;

    @Column(name = "rec_city")
    private String rec_city;

    @Column(name = "rec_street")
    private String rec_street;

    @Column(name = "rec_house")
    private String rec_house_num;

    @Column(name = "rec_apart_number")
    private String rec_apart_num;

    @Column(name = "post_office_number")
    private String post_office_num;

    @Column(name = "payment_date")
    private LocalDate payment_date;

    @Column(name = "appr_deliv_date")
    private LocalDate appr_deliv_date;

    @Column(name = "actual_deliv_date")
    private LocalDate actual_deliv_date;

    @Column(name = "add_wishes")
    private String add_wishes;

    @ManyToOne
    @JoinColumn(name = "cust_login_fk", nullable = false)
    private Customer customer_fk;

    @ManyToOne
    @JoinColumn(name = "paym_type_fk", nullable = false)
    private PaymentType paymentType_fk;

    @ManyToOne
    @JoinColumn(name = "deliv_type_fk", nullable = false)
    private DeliveryType deliveryType_fk;

    @ManyToOne
    @JoinColumn(name = "courier_fk")
    private Courier courier_fk;

    @ManyToOne
    @JoinColumn(name = "manager_fk")
    private Manager manager_fk;

    @ManyToOne
    @JoinColumn(name = "order_status_fk", nullable = false)
    private OrderStatus orderStatus_fk;

    @OneToMany(mappedBy = "order_fk", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ItemInOrder> goodsItemsList;


    public String getCustomerLogin() {
        return customer_fk.getCustomer_login();
    }

    public String getPaymantTypeName() {
        return paymentType_fk.getPaym_type_name();
    }

    public String getDeliveryTypeName() {
        return deliveryType_fk.getDelivery_type_name();
    }

    public String getCourierName() {
        if (courier_fk == null) {
            return "";
        }
        return courier_fk.getCourier_name();
    }

    public String getManagerName() {
        if (manager_fk == null) {
            return "";
        }
        return manager_fk.getManager_surname();
    }

    public String getStatusName() {
        return orderStatus_fk.getOrder_status_name();
    }

    public UsersOrder() {

    }

    public UsersOrder(LocalDate order_date, String rec_city, String rec_street, String rec_house_num,
                      String rec_apart_num, String post_office_num, LocalDate payment_date, LocalDate appr_deliv_date,
                      LocalDate actual_deliv_date, String add_wishes, Customer customer_fk, PaymentType paymentType_fk,
                      DeliveryType deliveryType_fk, Courier courier_fk, Manager manager_fk, OrderStatus orderStatus_fk) {
        this.order_date = order_date;
        this.rec_city = rec_city;
        this.rec_street = rec_street;
        this.rec_house_num = rec_house_num;
        this.rec_apart_num = rec_apart_num;
        this.post_office_num = post_office_num;
        this.payment_date = payment_date;
        this.appr_deliv_date = appr_deliv_date;
        this.actual_deliv_date = actual_deliv_date;
        this.add_wishes = add_wishes;
        this.customer_fk = customer_fk;
        this.paymentType_fk = paymentType_fk;
        this.deliveryType_fk = deliveryType_fk;
        this.courier_fk = courier_fk;
        this.manager_fk = manager_fk;
        this.orderStatus_fk = orderStatus_fk;
    }

    @Override
    public String toString() {
        return "Order [order_code=" + order_code + ", order_date=" + order_date + ", rec_city=" + rec_city
                + ", rec_street=" + rec_street + ", rec_house_num=" + rec_house_num + ", rec_apart_num=" + rec_apart_num
                + ", post_office_num=" + post_office_num + ", payment_date=" + payment_date + ", appr_deliv_date="
                + appr_deliv_date + ", actual_deliv_date=" + actual_deliv_date + ", add_wishes=" + add_wishes
                + ", customer_fk=" + customer_fk + ", paymentType_fk=" + paymentType_fk + ", deliveryType_fk="
                + deliveryType_fk + ", courier_fk=" + courier_fk + ", manager_fk=" + manager_fk + ", orderStatus_fk="
                + orderStatus_fk + "]";
    }


}
