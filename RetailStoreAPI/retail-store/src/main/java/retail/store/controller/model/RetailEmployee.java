package retail.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import retail.store.entities.Employee;

@Data
@NoArgsConstructor
public class RetailEmployee {

	private Long employeeId;
	private String employeeFirstName;
	private String employeeLastName;
	private String employeePhone;
	
	public RetailEmployee(Employee employee) {
		
		employeeId = employee.getEmployeeId();
		employeeFirstName = employee.getEmployeeFirstName();
		employeeLastName = employee.getEmployeeLastName();
		employeePhone = employee.getEmployeePhone();
	}
}
