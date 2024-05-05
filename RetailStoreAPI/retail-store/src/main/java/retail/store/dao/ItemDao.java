package retail.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import retail.store.entities.Item;

public interface ItemDao extends JpaRepository<Item, Long> {

}
