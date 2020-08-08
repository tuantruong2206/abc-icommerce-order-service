package au.com.nab.icommerce.order.proxy.rest.client;

import au.com.nab.icommerce.base.dto.JsonResponse;
import au.com.nab.icommerce.order.dto.BasketDTO;
import au.com.nab.icommerce.order.proxy.rest.fallback.ShoppingCartFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * this client helps to call rest to inventory service to gather product data
 */
@FeignClient(value = "nab-icommerce-shopping-cart-service", fallback = ShoppingCartFallback.class)
public interface ShoppingCartClient {

    @RequestMapping(method = RequestMethod.GET, value = "/cart/basket/{id}", consumes = "application/json")
    JsonResponse<BasketDTO> getBasketById(@PathVariable (value = "id") Long id);

}
