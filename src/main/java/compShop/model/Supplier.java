package compShop.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@Table(name="suppliers")
public class Supplier implements Serializable {

	
	@Column(name="edrpou")
	@Id
	private Integer edrpou;
	
	@Column(name="organization_name")
	private String organization_name;
	
	@Column(name="contact_pers_name")
	private String contact_pers_name;
	
	@Column(name="contact_pers_surname")
	private String contact_pers_surname;
	
	@Column(name="contact_pers_patron")
	private String contact_pers_patron;
	
	@Column(name="sup_city")
	private String sup_city;
	
	@Column(name="sup_street")
	private String sup_street;
	
	@Column(name="sup_house")
	private String sup_house_num;
	
	@Column(name="sup_apar_num")
	private String sup_apar_num;

	@Column(name="sup_web_page")
	private String sup_web_page;
	
	@Column(name="mfi")
	private String sup_mfi;
	
	@Column(name="cur_account")
	private String sup_currency_account;
	
	@ManyToMany(mappedBy = "goodSuppliers" , fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Good> deliveredGoods;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="supplier_fk")
	private List<SupplierPhone> supPhones;
	
	public Supplier() {
		
	}

	public Supplier(Integer edrpou, String organization_name, String contact_pers_name, String contact_pers_surname,
			String contact_pers_patron, String sup_city, String sup_street, String sup_house_num, String sup_apar_num,
			String sup_web_page, String sup_mfi, String sup_currency_account) {
	
		this.edrpou = edrpou;
		this.organization_name = organization_name;
		this.contact_pers_name = contact_pers_name;
		this.contact_pers_surname = contact_pers_surname;
		this.contact_pers_patron = contact_pers_patron;
		this.sup_city = sup_city;
		this.sup_street = sup_street;
		this.sup_house_num = sup_house_num;
		this.sup_apar_num = sup_apar_num;
		this.sup_web_page = sup_web_page;
		this.sup_mfi = sup_mfi;
		this.sup_currency_account = sup_currency_account;
	}

	@Override
	public String toString() {
		return "Supplier [edrpou=" + edrpou + ", organization_name=" + organization_name + ", contact_pers_name="
				+ contact_pers_name + ", contact_pers_surname=" + contact_pers_surname + ", contact_pers_patron="
				+ contact_pers_patron + ", sup_city=" + sup_city + ", sup_street=" + sup_street + ", sup_house_num="
				+ sup_house_num + ", sup_apar_num=" + sup_apar_num + ", sup_web_page=" + sup_web_page + ", sup_mfi="
				+ sup_mfi + ", sup_currency_account=" + sup_currency_account + "]";
	}
	
	
	
}
