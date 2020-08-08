package au.com.nab.icommerce.order.service;

import au.com.nab.icommerce.order.dto.OrderDTO;

public interface OrderService {

    public OrderDTO getOrderById(Long id);
    public OrderDTO createOrder(OrderDTO orderDTO);
    public OrderDTO updateOrder(OrderDTO orderDTO);

}
