package au.com.nab.icommerce.order.proxy.rest.proxy;

import au.com.nab.icommerce.base.dto.BaseDTO;
import au.com.nab.icommerce.base.dto.JsonResponse;
import au.com.nab.icommerce.order.dto.BasketDTO;
import au.com.nab.icommerce.order.proxy.rest.client.InventoryProductClient;
import org.springframework.stereotype.Component;

@Component
public class InventoryProductServiceProxy {


    private final InventoryProductClient inventoryProductClient;

    public InventoryProductServiceProxy(InventoryProductClient inventoryProductClient) {
        this.inventoryProductClient = inventoryProductClient;
    }

    public JsonResponse<Object> doOrder(BasketDTO basketDTO) {
        return inventoryProductClient.doOrder(basketDTO);
    }
}
