package au.com.nab.icommerce.order.controller.rest;

import au.com.nab.icommerce.base.dto.JsonResponse;
import au.com.nab.icommerce.base.util.HeaderUtil;
import au.com.nab.icommerce.order.dto.OrderDTO;
import au.com.nab.icommerce.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * All REST APIs of Order.
 */
@RestController
@RequestMapping(path = "order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    private final static String ENTITY_NAME = "order";

    public OrderController(OrderService orderService) {

        this.orderService = orderService;
    }

    /**
     * Get order by Id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<JsonResponse<OrderDTO>> getBasketById(@PathVariable Long id) throws Exception {
        log.info("REST request to get order: {}", id);
        return new ResponseEntity<>(new JsonResponse<>(this.orderService.getOrderById(id)), HttpStatus.OK);
    }

    /**
     * Get order by orderDTO
     * @param orderDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<JsonResponse<OrderDTO>> getBasketById(@RequestBody OrderDTO orderDTO) throws Exception {
        //TODO need order Validator here
        log.info("REST request to create order: {}", orderDTO);
        OrderDTO result = this.orderService.createOrder(orderDTO);
        return ResponseEntity.created(new URI("/order/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(new JsonResponse<>(result));
    }

    /**
     * update order
     * @param orderDTO
     * @param bindingResult
     * @return
     * @throws URISyntaxException
     */
    @PutMapping()
    public ResponseEntity<JsonResponse<OrderDTO>> updateBasketItem(@Valid @RequestBody OrderDTO orderDTO, BindingResult bindingResult) throws URISyntaxException {
        //TODO need order Validator here
        log.info("REST request to update basket Detail: {}", orderDTO);
        OrderDTO result = this.orderService.updateOrder(orderDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
                .body(new JsonResponse<>(result));
    }
}
