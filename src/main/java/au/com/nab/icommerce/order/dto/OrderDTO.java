package au.com.nab.icommerce.order.dto;

import au.com.nab.icommerce.order.domain.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long id;
    private String userid;
    private Long basketId;
    private Double amount;
    private Status status;
    private Instant createdAt;
    private Instant updatedAt;
}
