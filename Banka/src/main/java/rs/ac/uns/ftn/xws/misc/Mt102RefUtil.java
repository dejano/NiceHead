package rs.ac.uns.ftn.xws.misc;

import java.util.List;

import rs.ac.uns.ftn.xws.domain.mpb.Mt102Ref;
import rs.ac.uns.ftn.xws.generated.mp.Mt102Payment;
import rs.ac.uns.ftn.xws.generated.po.PaymentOrder;

public class Mt102RefUtil {
	
	public static Mt102Ref createMt102Ref(List<Mt102Payment> mt102payemnts, String messageId) {
		Mt102Ref mt102ref = new Mt102Ref();
		
		mt102ref.setMessageId(messageId);
		
		for (Mt102Payment mt102Payment : mt102payemnts) {
			mt102ref.getPaymentOrderId().add(mt102Payment.getPaymentOrderId());
		}
		
//		for (PaymentOrder paymentOrder : paymentOrders) {
//			mt102ref.getPaymentOrderId().add(paymentOrder.getMessageId());
//		}
//		
		return mt102ref;
	}

}
