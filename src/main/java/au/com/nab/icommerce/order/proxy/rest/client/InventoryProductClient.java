package au.com.nab.icommerce.order.proxy.rest.client;

import au.com.nab.icommerce.base.dto.BaseDTO;
import au.com.nab.icommerce.base.dto.JsonResponse;
import au.com.nab.icommerce.order.dto.BasketDTO;
import au.com.nab.icommerce.order.proxy.rest.fallback.InventoryProductFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * this client helps to call rest to inventory service to gather product data
 */
@FeignClient(value = "nab-icommerce-inventory-service", fallback = InventoryProductFallback.class)
public interface InventoryProductClient {

    @RequestMapping(method = RequestMethod.POST, value = "/inventory/product/do-product-order", consumes = "application/json")
    JsonResponse<Object> doOrder(BasketDTO basketDTO);
}
