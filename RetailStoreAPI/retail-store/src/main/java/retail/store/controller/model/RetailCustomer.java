package retail.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import retail.store.entities.Customer;

@Data
@NoArgsConstructor
public class RetailCustomer {

	private Long customerId;
	private String customerFirstName;
	private String customerLastName;
	private String customerEmail;
	
	public RetailCustomer(Customer customer) {
		
		customerId = customer.getCustomerId();
		customerFirstName = customer.getCustomerFirstName();
		customerLastName = customer.getCustomerLastName();
		customerEmail = customer.getCustomerEmail();
	}
}
