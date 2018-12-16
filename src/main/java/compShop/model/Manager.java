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
@Table(name="managers")
public class Manager {

	@Column(name="manager_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer manager_id;
	
	@Column(name="manager_surname")
	private String manager_surname;
	
	@Column(name="manager_name")
	private String manager_name;
	
	@Column(name="manager_patron")
	private String manager_patron;
	
	@Column(name="manager_phone")
	private String manager_phone;
	
	@OneToMany
	@JoinColumn(name="manager_fk")
	private List<UsersOrder> orderList;
	
	public Manager () {
		
	}

	public Manager(String manager_surname, String manager_name, String manager_patron, String manager_phone) {
		this.manager_surname = manager_surname;
		this.manager_name = manager_name;
		this.manager_patron = manager_patron;
		this.manager_phone = manager_phone;
	}

	@Override
	public String toString() {
		return "Manager [manager_id=" + manager_id + ", manager_surname=" + manager_surname + ", manager_name="
				+ manager_name + ", manager_patron=" + manager_patron + ", manager_phone=" + manager_phone + "]";
	}
	
	
	
}
