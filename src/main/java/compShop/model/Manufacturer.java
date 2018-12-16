package compShop.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="manufacturers")
public class Manufacturer {

	
	@Column(name="manufact_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer manufact_id;
	
	@Column(name="manufact_name")
	private String manufact_name;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="manufact_id_fk")
	private List<Good> goodsList;
	
	public Manufacturer() {
		
	}

	public Manufacturer(String manufact_name) {
		this.manufact_name = manufact_name;
	}

	@Override
	public String toString() {
		return "Manufacturer [manufact_id=" + manufact_id + ", manufact_name=" + manufact_name + "]";
	}
	
	
	
	
}
