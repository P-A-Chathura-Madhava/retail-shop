package lk.ctech.retailshop.repo;

import lk.ctech.retailshop.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepo extends JpaRepository<Bill, Integer> {
}
