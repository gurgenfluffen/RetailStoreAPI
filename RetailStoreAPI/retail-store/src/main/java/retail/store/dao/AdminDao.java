package retail.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import retail.store.entities.Admin;

public interface AdminDao extends JpaRepository<Admin, Long>{

}
