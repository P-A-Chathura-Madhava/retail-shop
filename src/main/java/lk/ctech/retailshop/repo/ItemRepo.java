package lk.ctech.retailshop.repo;

import lk.ctech.retailshop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Integer> {
}
