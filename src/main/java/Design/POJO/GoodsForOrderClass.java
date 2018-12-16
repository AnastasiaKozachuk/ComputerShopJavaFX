package Design.POJO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class GoodsForOrderClass {

    private String product_article;
    private String product_name;
    private Integer prod_quantity;
    private BigDecimal price_per_unit;
    private BigDecimal whole_price;

    public GoodsForOrderClass(){

    }


    public GoodsForOrderClass(String product_article, String product_name, Integer prod_quantity, BigDecimal price_per_unit, BigDecimal whole_price) {
        this.product_article = product_article;
        this.product_name = product_name;
        this.prod_quantity = prod_quantity;
        this.price_per_unit = price_per_unit;
        this.whole_price = whole_price;
    }
}
