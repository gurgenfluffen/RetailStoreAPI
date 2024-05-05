package retail.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import retail.store.entities.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

}
