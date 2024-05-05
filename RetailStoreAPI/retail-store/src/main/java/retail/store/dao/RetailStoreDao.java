package retail.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import retail.store.entities.RetailStore;

public interface RetailStoreDao extends JpaRepository<RetailStore, Long>{

}