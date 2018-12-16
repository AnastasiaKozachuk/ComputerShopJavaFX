package compShop.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="suppliers_phones")
public class SupplierPhone {

	@Column(name="phone_number")
	@Id
	private String phone_numb;
	
	@ManyToOne
	@JoinColumn(name="edrpou_fk", nullable=false)
	private Supplier supplier_fk;
	
	public SupplierPhone() {
		
	}

	public Integer getEdrpou() {
		return supplier_fk.getEdrpou();
	}

	
	
	@Override
	public String toString() {
		return "SupplierPhone [phone_numb=" + phone_numb + ", supplier_fk=" + supplier_fk + "]";
	}


	public SupplierPhone(String phone_numb, Supplier supplier_fk) {
		super();
		this.phone_numb = phone_numb;
		this.supplier_fk = supplier_fk;
	}
	
	
}
