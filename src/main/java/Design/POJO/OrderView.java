package Design.POJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderView {

        private Integer orderCode;
        private String login;

        public OrderView(){

        }

    public OrderView(Integer orderCode, String login) {
        this.orderCode = orderCode;
        this.login = login;
    }
}
