package lk.ctech.retailshop.repo;

import lk.ctech.retailshop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
}
