package rs.ac.uns.ftn.xws.misc;

import rs.ac.uns.ftn.xws.domain.mpb.Mt102Ref;
import rs.ac.uns.ftn.xws.generated.mp.Mt102;
import rs.ac.uns.ftn.xws.generated.mp.Mt102Payment;

public class ObjectMapper {

	public static Mt102Ref getMt102Ref(Mt102 mt102){
		Mt102Ref ret = new Mt102Ref();
		
		ret.setMessageId(mt102.getMessageId());
		
		for (Mt102Payment payment : mt102.getPayments().getPayment()) {
			ret.getPaymentOrderId().add(payment.getPaymentOrderId());
		}
		
		return ret;
	}
}
