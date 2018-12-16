package compShop.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="customers")
public class Customer {

	@Id
	@Column(name="customer_login")
	private String customer_login;
	
	@Column(name="cust_sex")
	private String cust_sex;
	
	@Column(name="customer_name")
	private String cust_name;
	
	@Column(name="customer_surname")
	private String cust_surname;
	
	@Column(name="customer_patron")
	private String cust_patron;
	
	@Column(name="register_time")
	private LocalDate register_date;
	
	@Column(name="customer_phone")
	private String cust_phone;
	
	@Column(name="birthday_date")
	private LocalDate cust_birthday;
	
	@Column(name="customer_pass")
	private String cust_pass;
	
	//Formula
	//private Integer cust_age;
	
	@OneToMany
	@JoinColumn(name="cust_login_fk")
	private List<UsersOrder> orderList;
	
	public Customer() {
		
	}
	
	public Customer(String customer_login, String cust_sex, String cust_name, String cust_surname, String cust_patron,
					LocalDate register_date, String cust_phone, LocalDate cust_birthday, String cust_pass) {
		this.customer_login = customer_login;
		this.cust_sex = cust_sex;
		this.cust_name = cust_name;
		this.cust_surname = cust_surname;
		this.cust_patron = cust_patron;
		this.register_date = register_date;
		this.cust_phone = cust_phone;
		this.cust_birthday = cust_birthday;
		this.cust_pass = cust_pass;
	}

	@Override
	public String toString() {
		return "Customer [customer_login=" + customer_login + ", cust_sex=" + cust_sex + ", cust_name=" + cust_name
				+ ", cust_surname=" + cust_surname + ", cust_patron=" + cust_patron + ", register_date=" + register_date
				+ ", cust_phone=" + cust_phone + ", cust_birthday=" + cust_birthday + ", cust_pass=" + cust_pass+ "]";
	}	
	
}
