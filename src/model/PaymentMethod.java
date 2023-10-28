package model;

public enum PaymentMethod {

	CREDIT ("CREDIT","Tarjeta de Cr\u00E9dito"),
	DEBIT ("DEBIT","Tarjeta de D\u00E9bito"),
	CASH ("CASH","Efectivo");
	
	private final String name;
	private final String spanishName;
	
	private PaymentMethod(String name, String spanishName) {
		this.name=name;
		this.spanishName=spanishName;
	}
	
	public String getName(){
		return name;
	}
	
	public static PaymentMethod conversionSpanishToEglish(String spanishname) {
		for (PaymentMethod paymentMethod:PaymentMethod.values()) {
			if(paymentMethod.spanishName.equalsIgnoreCase(spanishname)) {
		
				return paymentMethod;
			}
		}
        throw new IllegalArgumentException("No PaymentMethod found");
	}
	
	@Override
	public String toString() {
		return spanishName;
	}
	
	
}

//public class PaymentMethodDTO {
//
//	private static final PaymentMethodDTO CREDIT = new PaymentMethodDTO("CREDITO");
//	private static final PaymentMethodDTO DEBIT = new PaymentMethodDTO("DEBITO");
//	private static final PaymentMethodDTO CASH = new PaymentMethodDTO("EFECTIVO");
//	
//	private static final Set<PaymentMethodDTO> VALUES = Set.of(CREDIT, DEBIT, CASH);
//	
//	private final String name;
//	
//	private PaymentMethodDTO(String name) {
//		this.name=name;
//	}
//	
//	public PaymentMethodDTO getByName(String name) {
//		for (PaymentMethodDTO mp: VALUES) {
//			if (mp.name.equals(name)) {
//				return mp;
//			}
//		}
//		return null;
//	}
//}



//public enum PaymentMethodDTO {
//	
//	CREDIT("Credito"),
//	DEBIT("Debito"),
//	CASH ("Efectivo");
//
//	String name;
//	
//	private PaymentMethodDTO(String name) {
//		this.name=name;
//	}
//	private static String getPaymentMethod(String name) {
//		for (PaymentMethodDTO message:PaymentMethodDTO.values()) {
//			if(message.name.equals(name)) {
//				return message.name;
//			}
//		}
//		return null;
//	}
//}