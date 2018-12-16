package compShop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.BitSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.descriptor.sql.BitTypeDescriptor;

@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@Table(name = "list_of_orders")
public class ItemInOrder implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "order_code_fk", nullable = false)
    private UsersOrder order_fk;

    @Id
    @ManyToOne
    @JoinColumn(name = "prod_article_fk", nullable = false)
    private Good good_fk;

    @Column(name = "order_quant_prod")
    private Integer order_quant_prod;

    public Integer getOrderCode() {
        return order_fk.getOrder_code();
    }

    public String getProductArticle() {
        return good_fk.getProduct_article();
    }

    public BigDecimal getTotalPrice(){
        return good_fk.getPrice().multiply(new BigDecimal(order_quant_prod));
    }

    public ItemInOrder() {

    }

    public ItemInOrder(UsersOrder order_fk, Good good_fk, Integer order_quant_prod) {
        this.order_fk = order_fk;
        this.good_fk = good_fk;
        this.order_quant_prod = order_quant_prod;
    }

    @Override
    public String toString() {
        return "ItemInOrder [order_fk=" + order_fk + ", good_fk=" + good_fk + ", order_quant_prod=" + order_quant_prod
                + "]";
    }


}
