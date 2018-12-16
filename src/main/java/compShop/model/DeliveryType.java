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

@Setter
@Getter
@Entity
@Table(name="delivery_type")
public class DeliveryType {

	@Id
	@Column(name="del_type_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer delivery_type_id;
	
	@Column(name="del_type_name")
	private String delivery_type_name;
	
	@OneToMany
	@JoinColumn(name="deliv_type_fk")
	private List<UsersOrder> orderList;
	
	public DeliveryType() {
		
	}

	public DeliveryType( String delivery_type_name) {
		this.delivery_type_name = delivery_type_name;
	}

	@Override
	public String toString() {
		return "DeliveryType [delivery_type_id=" + delivery_type_id + ", delivery_type_name=" + delivery_type_name
				+ "]";
	}
	
}
