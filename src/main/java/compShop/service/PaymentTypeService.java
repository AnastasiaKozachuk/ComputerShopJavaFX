package compShop.service;

import java.util.List;
import compShop.model.PaymentType;

public interface PaymentTypeService {

	void saveNewPaymentType(PaymentType paymentType);
	void updatePaymentType(PaymentType paymentType);
	void deletePaymentType(PaymentType paymentType);
	
	List<PaymentType> getAllPaymentType();
	PaymentType getOneById(Integer id);
}
