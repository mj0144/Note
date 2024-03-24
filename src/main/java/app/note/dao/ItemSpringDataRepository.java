package app.note.dao;

import app.note.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemSpringDataRepository extends JpaRepository<Item, Long> {

}
