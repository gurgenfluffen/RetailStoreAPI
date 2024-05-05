package retail.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import retail.store.entities.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
