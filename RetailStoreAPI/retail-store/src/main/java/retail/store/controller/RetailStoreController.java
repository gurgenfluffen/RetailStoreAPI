package retail.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import retail.store.controller.model.RetailAdmin;
import retail.store.controller.model.RetailCustomer;
import retail.store.controller.model.RetailEmployee;
import retail.store.controller.model.RetailStoreData;
import retail.store.controller.model.RetailStoreItem;
import retail.store.entities.Customer;
import retail.store.service.RetailStoreService;

@RestController
@RequestMapping("/retail_store")
@Slf4j
public class RetailStoreController {

	@Autowired
	private RetailStoreService retailStoreService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public RetailStoreData insertRetailStore(@RequestBody RetailStoreData retailStoreData) {
		log.info("Creating store {}", retailStoreData);
		return retailStoreService.saveRetailStore(retailStoreData);
	}

	@PutMapping("/{retailStoreId}")
	public RetailStoreData updateRetailStore(@PathVariable Long retailStoreId,
			@RequestBody RetailStoreData retailStoreData) {
		retailStoreData.setRetailStoreId(retailStoreId);
		log.info("Updating store {}", retailStoreData);
		return retailStoreService.saveRetailStore(retailStoreData);
	}

	@GetMapping
	public List<RetailStoreData> retrieveRetailStores() {
		log.info("Listing all stores.");
		return retailStoreService.retrieveAllRetailStores();
	}

	@GetMapping("/retailStore/{retailStoreId}")
	public RetailStoreData retrieveRetailStoreById(@PathVariable Long retailStoreId) {
		log.info("Retrieving store by ID = {}", retailStoreId);
		return retailStoreService.retrieveRetailStoreById(retailStoreId);
	}

	// The following commented out mehtod will be implemented after completing a
	// front end course

//	@GetMapping("/{retailStoreCity}")
//	public RetailStoreData retrieveRetailStoreByCity(@RequestBody String retailStoreCity) {
//		log.info("Finding store in {}", retailStoreCity);
//		return retailStoreService.retrieveRetailStoreByCity();
//	}

	@DeleteMapping
	public void deleteAllStores() {
		log.info("Attempting to delete all stores.");
		throw new UnsupportedOperationException("Deleting all stores isn't allowed.");
	}
	
	@DeleteMapping("/retailStore/{retailStoreId}")
	public Map<String, String> deleteRetailStoreById(@PathVariable Long retailStoreId) {
		log.info("Deleting store with ID = {}", retailStoreId);
		retailStoreService.deleteRetailStoreById(retailStoreId);
		return Map.of("message", "Store with ID = " + retailStoreId + " has been deleted.");
	}

	@PostMapping("/retailStoreItem")
	@ResponseStatus(code = HttpStatus.CREATED)
	public RetailStoreItem insertStoreItem(@RequestBody RetailStoreItem retailStoreItem) {
		log.info("Creating item {}", retailStoreItem);
		return retailStoreService.saveRetailStoreItem(retailStoreItem);
	}

	@PutMapping("/retailStoreItem/{retailItemId}")
	public RetailStoreItem updateStoreItem(@PathVariable Long retailItemId,
			@RequestBody RetailStoreItem retailStoreItem) {
		retailStoreItem.setItemId(retailItemId);
		log.info("Updating item with ID = {}", retailItemId);
		return retailStoreService.saveRetailStoreItem(retailStoreItem);
	}

	@GetMapping("/retailStoreItem")
	public List<RetailStoreItem> retrieveAllItems() {
		log.info("Retrieving all items.");
		return retailStoreService.retrieveAllItems();
	}

	@GetMapping("/retailStoreItem/{retailItemId}")
	public RetailStoreItem retrieveItemById(@PathVariable Long retailItemId) {
		log.info("Retrieving item with ID = {}", retailItemId);
		return retailStoreService.retrieveItemById(retailItemId);
	}

	// The following commented out mehtods will be implemented after completing a
	// front end course

//	@GetMapping("/retailStoreItem/{retailItemBarcode}")
//	public RetailStoreItem retrieveItemByBarcode(@PathVariable String retailItemBarcode) {
//		log.info("Retrieving item with barcode {}", retailItemBarcode);
//		return retailStoreService.retrieveItemByBarcode();
//	}
//	
//	@GetMapping("/retailStoreItem/{retailItemName}")
//	public RetailStoreItem retrieveItemByName(@PathVariable String retailItemName) {
//		log.info("Retrieving item called {}", retailItemName);
//		return retailStoreService.retrieveItemByName();
//	}

	@DeleteMapping("/retailStoreItem")
	public void deleteAllItems() {
		log.info("Attempting to delete all items.");
		throw new UnsupportedOperationException("Deleting all items isn't allowed.");
	}
	
	@DeleteMapping("/retailStoreItem/{retailItemId}")
	public Map<String, String> deleteItemsById(@PathVariable Long retailItemId) {
		log.info("Deleting item with ID = {}", retailItemId);
		retailStoreService.deleteItemByID(retailItemId);
		return Map.of("message", "Item with ID = " + retailItemId + " has been deleted.");
	}

	// This method adds the employee as a record and maps it to a specific store.
	@PostMapping("/{retailStoreId}/retailStoreEmployee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public RetailEmployee insertStoreEmployee(@PathVariable Long retailStoreId,
			@RequestBody RetailEmployee retailStoreEmployee) {
		log.info("Creating employee {} at store with ID = {}", retailStoreEmployee, retailStoreId);
		return retailStoreService.saveRetailStoreEmployee(retailStoreId, retailStoreEmployee);
	}

	@PutMapping("/{retailStoreId}/retailStoreEmployee/{retailEmployeeId}")
	public RetailEmployee updateStoreEmployee(@PathVariable Long retailStoreId, 
			@PathVariable Long retailEmployeeId,
			@RequestBody RetailEmployee retailStoreEmployee) {
		log.info("Updating employee with ID = {}", retailEmployeeId);
		return retailStoreService.saveRetailStoreEmployee(retailStoreId, retailStoreEmployee);
	}

	@GetMapping("/{retailStoreId}/retailStoreEmployee")
	public List<RetailEmployee> retrieveAllEmployees() {
		log.info("Finding employees...");
		return retailStoreService.retrieveAllEmployees();
	}

	@GetMapping("/{retailStoreId}/retailStoreEmployee/{retailEmployeeId}")
	public RetailEmployee retrieveEmployeeById(@PathVariable Long retailStoreId, 
			@PathVariable Long retailEmployeeId) {
		log.info("Finding employee with ID = {}", retailEmployeeId);
		return retailStoreService.retrieveEmployeeById(retailStoreId, retailEmployeeId);
	}

	@DeleteMapping("/retailStoreEmployee")
	public void deleteAllEmployees() {
		log.info("Attempting to delete all employees.");
		throw new UnsupportedOperationException("Deleting all employees isn't allowed.");
	}
	
	@DeleteMapping("/{retailStoreId}/retailStoreEmployee/{retailEmployeeId}")
	public Map<String, String> deleteEmployeeById(@PathVariable Long retailStoreId,
			@PathVariable Long retailEmployeeId) {
		log.info("Deleting employee with ID = {}", retailEmployeeId);
		retailStoreService.deleteEmployeeByID(retailStoreId, retailEmployeeId);
		return Map.of("message", "Employee with ID = " + retailEmployeeId + " has been deleted.");
	}

	@PostMapping("/{retailStoreId}/retailStoreAdmin")
	@ResponseStatus(code = HttpStatus.CREATED)
	public RetailAdmin insertStoreAdmin(@PathVariable Long retailStoreId, 
			@RequestBody RetailAdmin retailStoreAdmin) {
		log.info("Creating admin {} at the store with ID = {}", retailStoreAdmin, retailStoreId);
		return retailStoreService.saveRetailStoreAdmin(retailStoreId, retailStoreAdmin);
	}

	@PutMapping("/{retailStoreId}/retailStoreAdmin/{retailAdminId}")
	public RetailAdmin updateAdmin(@PathVariable Long retailStoreId, 
			@PathVariable Long retailAdminId,
			@RequestBody RetailAdmin retailStoreAdmin) {
		retailStoreAdmin.setAdminId(retailAdminId);
		log.info("Updating admin with ID = {}", retailAdminId);
		return retailStoreService.saveRetailStoreAdmin(retailStoreId, retailStoreAdmin);
	}

	@GetMapping("/{retailStoreId}/retailStoreAdmin")
	public List<RetailAdmin> listAdmins() {
		log.info("Finding all admins...");
		return retailStoreService.retrieveAllAdmins();
	}

	@GetMapping("/{retailStoreId}/retailStoreAdmin/{retailAdminId}")
	public RetailAdmin listAdminsById(@PathVariable Long retailStoreId, 
			@PathVariable Long retailAdminId) {
		log.info("Finding employee with ID = {}", retailAdminId);
		return retailStoreService.retrieveAdminById(retailStoreId, retailAdminId);
	}

	@DeleteMapping("/retailStoreAdmin")
	public void deleteAllAdmins() {
		log.info("Attempting to delete all admins.");
		throw new UnsupportedOperationException("Deleting all admins isn't allowed.");
	}
	
	@DeleteMapping("/{retailStoreId}/retailStoreAdmin/{retailAdminId}")
	public Map<String, String> deleteAdminsById(@PathVariable Long retailStoreId, 
			@PathVariable Long retailAdminId) {
		log.info("Deleting admin with ID = {}", retailAdminId);
		retailStoreService.deleteAdminById(retailStoreId, retailAdminId);
		return Map.of("message", "Admin with ID = " + retailAdminId + " has been deleted.");
	}

	@PostMapping("/{retailStoreId}/retailStoreCustomer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public RetailCustomer insertCustomer(@PathVariable Long retailStoreId,
			@RequestBody RetailCustomer retailStoreCustomer) {
		log.info("Creating customer {} at the store with ID = {}", retailStoreCustomer, retailStoreId);
		return retailStoreService.saveRetailStoreCustomer(retailStoreId, retailStoreCustomer);
	}

	@PutMapping("/{retailStoreId}/retailStoreCustomer/{retailCustomerId}")
	public RetailCustomer updateCustomer(@PathVariable Long retailStoreId, 
			@PathVariable Long retailCustomerId, @RequestBody RetailCustomer retailStoreCustomer) {
		retailStoreCustomer.setCustomerId(retailCustomerId);
		log.info("Updating customer with ID = {}", retailCustomerId);
		return retailStoreService.saveRetailStoreCustomer(retailStoreId, retailStoreCustomer);
	}
	
	@GetMapping("/{retailStoreId}/retailStoreCustomer")
	public List<RetailCustomer>listCustomers(){
		log.info("Finding all customers...");
		return retailStoreService.retrieveAllCustomers();
	}
	
	@GetMapping("/{retailStoreId}/retailStoreCustomer/{retailCustomerId}")
	public Customer listCustomerById(
			@PathVariable Long retailStoreId, @PathVariable Long retailCustomerId) {
		log.info("Finding employee with ID = {}", retailCustomerId);
		return retailStoreService.findCustomerById(retailStoreId, retailCustomerId);
	}
	
	@DeleteMapping("/retailStoreCustomer")
	public void deleteAllCustomers() {
		log.info("Attempting to delete all customers.");
		throw new UnsupportedOperationException("Deleting all customers isn't allowed.");
	}
	
	@DeleteMapping("/{retailStoreId}/retailStoreCustomer/{retailCustomerId}")
	public Map<String, String> deleteCustomerById(
			@PathVariable Long retailStoreId, @PathVariable Long retailCustomerId) {
		log.info("Deleting customer with ID = {}", retailCustomerId);
		retailStoreService.deleteCustomerById(retailStoreId, retailCustomerId);
		return Map.of("message", "Customer with ID = " + retailCustomerId + " has been deleted.");
	}
}
