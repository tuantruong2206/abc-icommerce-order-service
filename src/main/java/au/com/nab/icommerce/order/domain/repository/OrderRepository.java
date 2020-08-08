package au.com.nab.icommerce.order.domain.repository;

import au.com.nab.icommerce.order.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
