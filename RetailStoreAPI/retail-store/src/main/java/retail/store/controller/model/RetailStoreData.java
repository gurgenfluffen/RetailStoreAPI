package retail.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import retail.store.entities.Admin;
import retail.store.entities.Customer;
import retail.store.entities.Employee;
import retail.store.entities.Item;
import retail.store.entities.RetailStore;

@Data
@NoArgsConstructor
public class RetailStoreData {

	private Long retailStoreId;
	private String retailStoreName;
	private String retailStoreAddress;
	private String retailStoreCity;
	private String retailStoreState;
	private String retailStoreZip;
	private String retailStorePhone;
	private String retailStoreIg;
	private Set<RetailCustomer> customers = new HashSet<>();
	private Set<RetailEmployee> employees = new HashSet<>();
	private Set<RetailAdmin> admins = new HashSet<>();
	private Set<RetailStoreItem> items = new HashSet<>();

	public RetailStoreData(RetailStore retailStore) {
		retailStoreId = retailStore.getRetailStoreId();
		retailStoreName = retailStore.getRetailStoreName();
		retailStoreAddress = retailStore.getRetailStoreAddress();
		retailStoreCity = retailStore.getRetailStoreCity();
		retailStoreState = retailStore.getRetailStoreState();
		retailStoreZip = retailStore.getRetailStoreZip();
		retailStorePhone = retailStore.getRetailStorePhone();
		retailStoreIg = retailStore.getRetailStoreIg();
		
		for(Customer customer : retailStore.getCustomers()) {
			customers.add(new RetailCustomer(customer));
		}
		
		for(Employee employee : retailStore.getEmployees()) {
			employees.add(new RetailEmployee(employee));
		}
		
		for(Admin admin : retailStore.getAdmins()) {
			admins.add(new RetailAdmin(admin));
		}
		
		for(Item item : retailStore.getItems()) {
			items.add(new RetailStoreItem(item));
		}
	}
}
