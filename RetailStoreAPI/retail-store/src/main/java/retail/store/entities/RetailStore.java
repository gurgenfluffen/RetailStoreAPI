package retail.store.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class RetailStore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long retailStoreId;
	
	private String retailStoreName;
	private String retailStoreAddress;
	private String retailStoreCity;
	private String retailStoreState;
	private String retailStoreZip;
	private String retailStorePhone;
	private String retailStoreIg;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "retail_store_customer",
	joinColumns = @JoinColumn(name = "retail_store_id"),
	inverseJoinColumns = @JoinColumn(name = "customer_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Customer> customers = new HashSet<>();
	
	@OneToMany(mappedBy = "retailStore", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Employee> employees = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "retail_store_admin",
	joinColumns = @JoinColumn(name = "retail_store_id"),
	inverseJoinColumns = @JoinColumn(name = "admin_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Admin> admins = new HashSet<>();
	
	@OneToMany(mappedBy = "retailStore", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Item> items = new HashSet<>();
}
