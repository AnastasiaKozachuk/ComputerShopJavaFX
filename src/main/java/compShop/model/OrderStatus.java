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
@Table(name="order_status")
public class OrderStatus {

	
	@Column(name="order_status_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer order_status_id;
	
	@Column(name="order_status_name")
	private String order_status_name;
	
	@OneToMany
	@JoinColumn(name="order_status_fk")
	private List<UsersOrder> orderList;
	
	public OrderStatus() {
		
	}

	public OrderStatus( String order_status_name) {
		this.order_status_name = order_status_name;
	}

	@Override
	public String toString() {
		return "OrderStatus [order_status_id=" + order_status_id + ", order_status_name=" + order_status_name + "]";
	}
	
	
	
}
