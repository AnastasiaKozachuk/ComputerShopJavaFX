package compShop.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="couriers")
@Setter
@Getter
public class Courier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="courier_id", updatable = false, nullable = false)
	private Integer courier_Id;
	
	@Column(name="courier_name", nullable = false)
	private String courier_name;
	
	@Column(name="courier_surname", nullable = false)
	private String courier_surname;
	
	@Column(name="courier_patron",  nullable = false)
	private String courier_patron;
	
	@Column(name="courier_phone",  nullable = false)
	private String courier_phone;
	
	@OneToMany(mappedBy = "courier_fk")
	private List<UsersOrder> orderList;
	
	public Courier() {}

	public Courier(String courier_name, String courier_surname, String courier_patron, String courier_phone) {
		this.courier_name = courier_name;
		this.courier_surname = courier_surname;
		this.courier_patron = courier_patron;
		this.courier_phone = courier_phone;
	}

	@Override
	public String toString() {
		return "Courier [courier_Id=" + courier_Id + ", courier_name=" + courier_name + ", courier_surname="
				+ courier_surname + ", courier_patron=" + courier_patron + ", courier_phone=" + courier_phone + "]";
	}
	
	
	
}
