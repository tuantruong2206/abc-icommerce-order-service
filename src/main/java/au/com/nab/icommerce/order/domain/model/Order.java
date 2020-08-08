package au.com.nab.icommerce.order.domain.model;

import au.com.nab.icommerce.order.domain.enumeration.Status;
import au.com.nab.icommerce.order.dto.OrderDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "nab_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "userid")
    private String userid;

    @NotNull
    @Column(name = "basket_id")
    private Long basketId;

    @NotNull
    @Column(name = "amount")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @CreationTimestamp
    @Column(name = "updated_at", insertable = false)
    private Instant updatedAt;

    @PreUpdate
    public void onPreUpdate() {
        setUpdatedAt(Instant.now());
    }

    public static Order packingNewOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setUserid(orderDTO.getUserid());
        order.setBasketId(orderDTO.getBasketId());
        order.setAmount(orderDTO.getAmount());
        order.setStatus(Status.COMPLETED);
        return order;
    }

    public static void updateOrder(Order order, OrderDTO orderDTO) {
        order.setStatus(orderDTO.getStatus());
    }
}
