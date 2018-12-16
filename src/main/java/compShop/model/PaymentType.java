package compShop.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="payment_type")
public class PaymentType {

	
	@Column(name="paym_type_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer paym_type_id;
	
	@Column(name="paym_type_name")
	private String paym_type_name;
	
	@OneToMany
	@JoinColumn(name="paym_type_fk")
	private List<UsersOrder> orderList;
	
	public PaymentType() {
		
	}

	public PaymentType(String paym_type_name) {
		this.paym_type_name = paym_type_name;
	}

	@Override
	public String toString() {
		return "PaymentType [paym_type_id=" + paym_type_id + ", paym_type_name=" + paym_type_name + "]";
	}
	
	
	
}
