package retail.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import retail.store.entities.Item;

@Data
@NoArgsConstructor
public class RetailStoreItem {
	
	private Long itemId;
	private String itemName;
	private String itemBarcode;
	private String itemPrice;
	
	public RetailStoreItem(Item item) {
		
		itemId = item.getItemId();
		itemName = item.getItemName();
		itemBarcode = item.getItemBarcode();
		itemPrice = item.getItemPrice();
	}
}
