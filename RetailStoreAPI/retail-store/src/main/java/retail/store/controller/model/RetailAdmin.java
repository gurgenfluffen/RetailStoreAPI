package retail.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import retail.store.entities.Admin;

@Data
@NoArgsConstructor
public class RetailAdmin {

	private Long adminId;
	private String adminFirstName;
	private String adminLastName;
	private String adminPhone;
	
	public RetailAdmin(Admin admin) {
		
		adminId = admin.getAdminId();
		adminFirstName = admin.getAdminFirstName();
		adminLastName = admin.getAdminLastName();
		adminPhone = admin.getAdminPhone();
		
	}
}
