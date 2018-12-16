package compShop.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Setter
@Getter
@Entity
@Table(name="goods")
public class Good implements Serializable{

	@Column(name="prod_article")
	@Id
	private String product_article;
	
	@Column(name="full_prod_name")
	private String prod_full_name;
	
	@Column(name="min_stock")
	private Integer min_stock;
	
	@Column(name="deliver_disc")
	private Boolean deliver_disk;
	
	@Column(name="price")
	private BigDecimal price;
	
	@Column(name="instock")
	private Integer inStock;
	
	@ManyToOne
	@JoinColumn(name="manufact_id_fk", nullable=false)
	private Manufacturer manufact_fk;
	
	@ManyToOne
	@JoinColumn(name="category_id_fk", nullable=false)
	private Category category_fk;

	@ManyToMany(targetEntity = Supplier.class, cascade = { CascadeType.ALL } , fetch=FetchType.EAGER)
	@JoinTable(name = "deliveries", 
				joinColumns = { @JoinColumn(name = "prod_article_fk") }, 
				inverseJoinColumns = { @JoinColumn(name = "edrpou_fk")})
	private List<Supplier> goodSuppliers;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER , mappedBy="good_fk")
	private List<ItemInOrder> goodsItemsList;


	public String getCategoryName(){
		return category_fk.getCategoryName();
	}

	public String getManufacturerName(){
		return manufact_fk.getManufact_name();
	}

	public Good() {
		
	}

	public Good(String product_article, String prod_full_name, Integer min_stock, Boolean deliver_disk,
			BigDecimal price, Integer inStock, Manufacturer manufact_fk, Category category_fk) {
		super();
		this.product_article = product_article;
		this.prod_full_name = prod_full_name;
		this.min_stock = min_stock;
		this.deliver_disk = deliver_disk;
		this.price = price;
		this.inStock = inStock;
		this.manufact_fk = manufact_fk;
		this.category_fk = category_fk;
	}

	@Override
	public String toString() {
		return "Good [product_article=" + product_article + ", prod_full_name=" + prod_full_name + ", min_stock="
				+ min_stock + ", deliver_disk=" + deliver_disk + ", price=" + price + ", inStock=" + inStock
				+ ", manufact_fk=" + manufact_fk + ", category_fk=" + category_fk + "]";
	}


	
	
	
}
