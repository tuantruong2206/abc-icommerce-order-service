package au.com.nab.icommerce.order.service.impl;

import au.com.nab.icommerce.base.dto.JsonResponse;
import au.com.nab.icommerce.base.enumeration.MessageCodeEnum;
import au.com.nab.icommerce.base.exception.NABException;
import au.com.nab.icommerce.order.domain.model.Order;
import au.com.nab.icommerce.order.domain.repository.OrderRepository;
import au.com.nab.icommerce.order.dto.BasketDTO;
import au.com.nab.icommerce.order.dto.OrderDTO;
import au.com.nab.icommerce.order.dto.OrderMapper;
import au.com.nab.icommerce.order.proxy.rest.proxy.InventoryProductServiceProxy;
import au.com.nab.icommerce.order.proxy.rest.proxy.ShoppingCartServiceProxy;
import au.com.nab.icommerce.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ShoppingCartServiceProxy shoppingCartServiceProxy;

    private final OrderMapper orderMapper;

    private final InventoryProductServiceProxy inventoryProductServiceProxy;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper,
                            ShoppingCartServiceProxy shoppingCartServiceProxy, InventoryProductServiceProxy inventoryProductServiceProxy) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.shoppingCartServiceProxy = shoppingCartServiceProxy;
        this.inventoryProductServiceProxy = inventoryProductServiceProxy;
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Optional<Order> optOrder = this.orderRepository.findById(id);
        if (!optOrder.isPresent()) {
            throw new NABException(MessageCodeEnum.COMMON_ERROR_001, HttpStatus.BAD_REQUEST);
        }
        return this.orderMapper.toDto(optOrder.get());
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        BasketDTO basketDTO = this.shoppingCartServiceProxy.getBasketById(orderDTO.getBasketId()).getData();

        if (Objects.isNull(basketDTO))
            throw new NABException(MessageCodeEnum.COMMON_ERROR_001, HttpStatus.BAD_REQUEST);
        //TODO could do async here by send kafka message for order, but now is prototype for demo only
        //we do sync here by call feign client to inventory for do Order and status will be COMPLETED or CANCELED immediately
        JsonResponse<Object> response = this.inventoryProductServiceProxy.doOrder(basketDTO);
        if (!response.getSuccess()) {
            //TODO should return specified error code
            throw new NABException(MessageCodeEnum.COMMON_ERROR_001, HttpStatus.BAD_REQUEST);
        }

        basketDTO.setStatus(false);

        JsonResponse<BasketDTO> basketResult = this.shoppingCartServiceProxy.updateBasket(basketDTO);
        Order order = this.orderRepository.save(Order.packingNewOrder(orderDTO));
        return this.orderMapper.toDto(order);
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        Optional<Order> optOrder = this.orderRepository.findById(orderDTO.getId());
        if (!optOrder.isPresent()) {
            throw new NABException(MessageCodeEnum.COMMON_ERROR_001, HttpStatus.BAD_REQUEST);
        }
        Order order = optOrder.get();
        Order.updateOrder(order, orderDTO);
        Order result = this.orderRepository.save(order);
        return this.orderMapper.toDto(result);
    }
}
