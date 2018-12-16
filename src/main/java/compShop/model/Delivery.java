package compShop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Setter
@Getter
@Entity
@Table(name="deliveries")
public class Delivery implements Serializable {

	@Id
	@Column(name="edrpou_fk", updatable = false, nullable = false)
	private Integer edrpou_fk;

	@Id
	@Column(name="prod_article_fk", updatable = false, nullable = false)
	private String prod_article_fk;

	public Delivery() {

	}

	public Delivery(Integer edrpou_fk, String prod_article_fk) {
		super();
		this.edrpou_fk = edrpou_fk;
		this.prod_article_fk = prod_article_fk;
	}

	@Override
	public String toString() {
		return "Delivery [edrpou_fk=" + edrpou_fk + ", prod_article_fk=" + prod_article_fk + "]";
	}
	
	
	
	
}
