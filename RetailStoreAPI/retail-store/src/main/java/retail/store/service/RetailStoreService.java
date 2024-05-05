package retail.store.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import retail.store.controller.model.RetailAdmin;
import retail.store.controller.model.RetailCustomer;
import retail.store.controller.model.RetailEmployee;
import retail.store.controller.model.RetailStoreData;
import retail.store.controller.model.RetailStoreItem;
import retail.store.dao.AdminDao;
import retail.store.dao.CustomerDao;
import retail.store.dao.EmployeeDao;
import retail.store.dao.ItemDao;
import retail.store.dao.RetailStoreDao;
import retail.store.entities.Admin;
import retail.store.entities.Customer;
import retail.store.entities.Employee;
import retail.store.entities.Item;
import retail.store.entities.RetailStore;

@Service
public class RetailStoreService {

	@Autowired
	private RetailStoreDao retailStoreDao;

	@Autowired
	private ItemDao retailItemDao;

	@Autowired
	private EmployeeDao retailEmployeeDao;

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private CustomerDao customerDao;

	@Transactional(readOnly = false)
	public RetailStoreData saveRetailStore(RetailStoreData retailStoreData) {
		Long retailStoreId = retailStoreData.getRetailStoreId();
		RetailStore retailStore = findOrCreateRetailStore(retailStoreId);

		copyRetailStoreFields(retailStore, retailStoreData);
		return new RetailStoreData(retailStoreDao.save(retailStore));
	}

	private void copyRetailStoreFields(RetailStore retailStore, RetailStoreData retailStoreData) {
		retailStore.setRetailStoreId(retailStoreData.getRetailStoreId());
		retailStore.setRetailStoreName(retailStoreData.getRetailStoreName());
		retailStore.setRetailStoreAddress(retailStoreData.getRetailStoreAddress());
		retailStore.setRetailStoreCity(retailStoreData.getRetailStoreCity());
		retailStore.setRetailStoreState(retailStoreData.getRetailStoreState());
		retailStore.setRetailStoreZip(retailStoreData.getRetailStoreZip());
		retailStore.setRetailStorePhone(retailStoreData.getRetailStorePhone());
		retailStore.setRetailStoreIg(retailStoreData.getRetailStoreIg());
	}

	private RetailStore findOrCreateRetailStore(Long retailStoreId) {
		if (Objects.isNull(retailStoreId)) {
			return new RetailStore();
		} else {
			return findRetailStoreById(retailStoreId);
		}
	}

	private RetailStore findRetailStoreById(Long retailStoreId) {
		return retailStoreDao.findById(retailStoreId)
				.orElseThrow(() -> new NoSuchElementException("Store with ID = " + retailStoreId + " was not found."));
	}

	@Transactional(readOnly = true)
	public List<RetailStoreData> retrieveAllRetailStores() {
		List<RetailStore> retailStores = retailStoreDao.findAll();
		List<RetailStoreData> result = new LinkedList<>();

		for (RetailStore retailStore : retailStores) {
			RetailStoreData rsd = new RetailStoreData(retailStore);

			rsd.getAdmins().clear();
			rsd.getCustomers().clear();
			rsd.getEmployees().clear();
			rsd.getItems().clear();

			result.add(rsd);
		}

		return result;
	}

	@Transactional(readOnly = true)
	public RetailStoreData retrieveRetailStoreById(Long retailStoreId) {
		return new RetailStoreData(findRetailStoreById(retailStoreId));
		// add message for when some dumb dumb tries to delete all of them
	}

	// research parity!

//is it possible to add a scanner for user input to compare the string to?
	// Also, does eclipse still take scanner input when spring is running?
	// Would the scanner have to be declared in each entity? Declared in the service
	// layer?
	// EDIT: anything regarding the scanner will be implemented after learning about
	// the front end

	// The following commented out mehtod will be implemented after completing a
	// front end course

//	@Transactional(readOnly = true)
//	public RetailStoreData retrieveRetailStoreByCity() {
//		
////		return retailStoreDao.findAll(retailStoreCity)
////				.orElseThrow(() -> new NoSuchElementException(
////						"Store in " + retailStoreCity + " was not found."));
//		//would like to use a .contains() method on scanner input
//		return null;
//	}
	@Transactional(readOnly = false)
	public void deleteRetailStoreById(Long retailStoreId) {
		RetailStore retailStore = findRetailStoreById(retailStoreId);
		retailStoreDao.delete(retailStore);
	}

	@Transactional(readOnly = false)
	public RetailStoreItem saveRetailStoreItem(RetailStoreItem retailStoreItem) {
		Long retailItemId = retailStoreItem.getItemId();
		Item retailItem = findOrCreateItem(retailItemId);

		copyRetailItemFields(retailItem, retailStoreItem);
		return new RetailStoreItem(retailItemDao.save(retailItem));
	}

	// filters security for permissions
	// look at spring security dependency? for permissions
	// baeldung spring resources! good stuff!

	private void copyRetailItemFields(Item retailItem, RetailStoreItem retailStoreItem) {
		retailItem.setItemId(retailStoreItem.getItemId());
		retailItem.setItemName(retailStoreItem.getItemName());
		retailItem.setItemBarcode(retailStoreItem.getItemBarcode());
		retailItem.setItemPrice(retailStoreItem.getItemPrice());
	}

	private Item findOrCreateItem(Long retailItemId) {
		if (Objects.isNull(retailItemId)) {
			return new Item();
		} else {
			return findStoreItemById(retailItemId);
		}
	}

	private Item findStoreItemById(Long retailItemId) {
		return retailItemDao.findById(retailItemId)
				.orElseThrow(() -> new NoSuchElementException("Item with ID = " + retailItemId + " was not found."));
	}

	@Transactional(readOnly = true)
	public List<RetailStoreItem> retrieveAllItems() {
		List<Item> retailItems = retailItemDao.findAll();
		List<RetailStoreItem> result = new LinkedList<>();

		for (Item retailItem : retailItems) {
			RetailStoreItem rsi = new RetailStoreItem(retailItem);
			result.add(rsi);
		}

		return result;
	}

	@Transactional(readOnly = true)
	public RetailStoreItem retrieveItemById(Long retailItemId) {
		return new RetailStoreItem(findStoreItemById(retailItemId));
	}

	// The following commented out methods will be added after finishing a front end
	// course

//	@Transactional(readOnly = true)
//	public RetailStoreItem retrieveItemByBarcode() {
//		// return retailItemDao.findAll(retailItemBarcode)
////		.orElseThrow(() -> new NoSuchElementException(
////		"Item with barcode = " + retailItemBarcode + " was not found."));
//		return null;
//	}
//
//	@Transactional(readOnly = true)
//	public RetailStoreItem retrieveItemByName() {
//		// return retailItemDao.findAll(retailItemName)
////		.orElseThrow(() -> new NoSuchElementException(
////		"Item called " + retailItemName + " was not found."));
//		return null;
//	}
//
	@Transactional(readOnly = false)
	public void deleteItemByID(Long retailItemId) {
		Item retailItem = findStoreItemById(retailItemId);
		retailItemDao.delete(retailItem);
		// add message for when some dumb dumb tries to delete all of them
	}

	@Transactional(readOnly = false)
	public RetailEmployee saveRetailStoreEmployee(Long retailStoreId, RetailEmployee retailStoreEmployee) {
		Long retailEmployeeId = retailStoreEmployee.getEmployeeId();
		Employee retailEmployee = findOrCreateEmployee(retailStoreId, retailEmployeeId);

		copyRetailEmployeeFields(retailEmployee, retailStoreEmployee);
		return new RetailEmployee(retailEmployeeDao.save(retailEmployee));
	}

	private void copyRetailEmployeeFields(Employee retailEmployee, RetailEmployee retailStoreEmployee) {
		retailEmployee.setEmployeeId(retailStoreEmployee.getEmployeeId());
		retailEmployee.setEmployeeFirstName(retailStoreEmployee.getEmployeeFirstName());
		retailEmployee.setEmployeeLastName(retailStoreEmployee.getEmployeeLastName());
		retailEmployee.setEmployeePhone(retailStoreEmployee.getEmployeePhone());
	}

	private Employee findOrCreateEmployee(Long retailStoreId, Long retailEmployeeId) {
		if (Objects.isNull(retailEmployeeId)) {
			return new Employee();
		} else {
			return findEmployeeById(retailStoreId, retailEmployeeId);
		}
	}

	private Employee findEmployeeById(Long retailStoreId, Long retailEmployeeId) {
		Employee employee = retailEmployeeDao.findById(retailEmployeeId).orElseThrow(
				() -> new NoSuchElementException("Employee with ID = " + retailEmployeeId + " was not found."));

		if (employee.getRetailStore().getRetailStoreId() != retailStoreId) {
			throw new IllegalArgumentException("The employee with ID = " + retailEmployeeId
					+ " does not work at the store with ID = " + retailStoreId);
		}
		return employee;
	}

	@Transactional(readOnly = true)
	public List<RetailEmployee> retrieveAllEmployees() {
		List<Employee> retailEmployees = retailEmployeeDao.findAll();
		List<RetailEmployee> result = new LinkedList<>();

		for (Employee retailEmployee : retailEmployees) {
			RetailEmployee re = new RetailEmployee(retailEmployee);
			result.add(re);
		}

		return result;
	}

	@Transactional(readOnly = true)
	public RetailEmployee retrieveEmployeeById(Long retailStoreId, Long retailEmployeeId) {
		return new RetailEmployee(findEmployeeById(retailStoreId, retailEmployeeId));
	}

	@Transactional(readOnly = false)
	public void deleteEmployeeByID(Long retailStoreId, Long retailEmployeeId) {
		Employee retailEmployee = findEmployeeById(retailStoreId, retailEmployeeId);
		retailEmployeeDao.delete(retailEmployee);

	}

	@Transactional(readOnly = false)
	public RetailAdmin saveRetailStoreAdmin(Long retailStoreId, RetailAdmin retailStoreAdmin) {
		RetailStore retailStore = findRetailStoreById(retailStoreId);
		Long adminId = retailStoreAdmin.getAdminId();
		Admin admin = findOrCreateAdmin(retailStoreId, adminId);

		copyAdminFields(admin, retailStoreAdmin);
		admin.getRetailStores().add(retailStore);
		retailStore.getAdmins().add(admin);

		Admin dbAdmin = adminDao.save(admin);
		return new RetailAdmin(dbAdmin);
	}

	private void copyAdminFields(Admin admin, RetailAdmin retailStoreAdmin) {
		admin.setAdminId(retailStoreAdmin.getAdminId());
		admin.setAdminFirstName(retailStoreAdmin.getAdminFirstName());
		admin.setAdminLastName(retailStoreAdmin.getAdminLastName());
		admin.setAdminPhone(retailStoreAdmin.getAdminPhone());

	}

	public Admin findOrCreateAdmin(Long retailStoreId, Long adminId) {
		if (Objects.isNull(adminId)) {
			return new Admin();
		} else {
			return findAdminById(retailStoreId, adminId);
		}
	}

	
	public Admin findAdminById(Long retailStoreId, Long adminId) {
		Admin admin = adminDao.findById(adminId)
				.orElseThrow(() -> new NoSuchElementException("Admin with ID = " + adminId + " was not found."));

		boolean foundAdmin = false;

		for (RetailStore retailStore : admin.getRetailStores()) {
			if (retailStore.getRetailStoreId() == retailStoreId) {
				foundAdmin = true;
				break;
			}
		}

		if (!foundAdmin) {
			throw new IllegalArgumentException(
					"The admin with ID = " + adminId 
					+ " does not exist at store with ID = " + retailStoreId);
		}
		return admin;
	}

	@Transactional(readOnly = true)
	public List<RetailAdmin> retrieveAllAdmins() {
		List<Admin> retailAdmin = adminDao.findAll();
		List<RetailAdmin> result = new LinkedList<>();

		for (Admin admin : retailAdmin) {
			RetailAdmin ra = new RetailAdmin(admin);
			result.add(ra);
		}

		return result;
	}
	
	@Transactional(readOnly = true)
	public RetailAdmin retrieveAdminById(Long retailStoreId, Long retailAdminId) {
		return new RetailAdmin(findAdminById(retailStoreId, retailAdminId));
	}
	
	@Transactional(readOnly = false)
	public void deleteAdminById(Long retailStoreId, Long retailAdminId) {
		Admin retailAdmin = findAdminById(retailStoreId, retailAdminId);
		adminDao.delete(retailAdmin);
	}

	@Transactional(readOnly = false)
	public RetailCustomer saveRetailStoreCustomer(Long retailStoreId, RetailCustomer retailStoreCustomer) {
		RetailStore retailStore = findRetailStoreById(retailStoreId);
		Long customerId = retailStoreCustomer.getCustomerId();
		Customer customer = findOrCreateCustomer(retailStoreId, customerId);

		copyCustomerFields(customer, retailStoreCustomer);
		customer.getRetailStores().add(retailStore);
		retailStore.getCustomers().add(customer);

		Customer dbCustomer = customerDao.save(customer);
		return new RetailCustomer(dbCustomer);
	}

	public Customer findOrCreateCustomer(Long retailStoreId, Long customerId) {
		if (Objects.isNull(customerId)) {
			return new Customer();
		} else {
			return findCustomerById(retailStoreId, customerId);
		}
	}

	private void copyCustomerFields(Customer customer, RetailCustomer retailStoreCustomer) {
		customer.setCustomerId(retailStoreCustomer.getCustomerId());
		customer.setCustomerFirstName(retailStoreCustomer.getCustomerFirstName());
		customer.setCustomerLastName(retailStoreCustomer.getCustomerLastName());
		customer.setCustomerEmail(retailStoreCustomer.getCustomerEmail());
	}

	@Transactional(readOnly = true)
	public List<RetailCustomer> retrieveAllCustomers() {
		List<Customer> retailCustomer = customerDao.findAll();
		List<RetailCustomer> result = new LinkedList<>();

		for (Customer customer : retailCustomer) {
			RetailCustomer rc = new RetailCustomer(customer);
			result.add(rc);
		}

		return result;
	}

	@Transactional(readOnly = true)
	public RetailCustomer retrieveCustomerById(Long retailStoreId, Long retailCustomerId) {
		return new RetailCustomer(findCustomerById(retailStoreId, retailCustomerId));
	}
	
	public Customer findCustomerById(Long retailStoreId, Long retailCustomerId) {
		Customer customer = customerDao.findById(retailCustomerId)
				.orElseThrow(() -> new NoSuchElementException("Customer with ID = " + retailCustomerId + " was not found."));

		boolean found = false;

		for (RetailStore retailStore : customer.getRetailStores()) {
			if (retailStore.getRetailStoreId() == retailStoreId) {
				found = true;
				break;
			}
		}

		if (!found) {
			throw new IllegalArgumentException(
					"The customer with ID = " + retailCustomerId 
					+ " does not exist at store with ID = " + retailStoreId);
		}
		return customer;
	}

	@Transactional(readOnly = false)
	public void deleteCustomerById(Long retailStoreId, Long retailCustomerId) {
		Customer retailCustomer = findCustomerById(retailStoreId, retailCustomerId);
		customerDao.delete(retailCustomer);
	}

	

}
