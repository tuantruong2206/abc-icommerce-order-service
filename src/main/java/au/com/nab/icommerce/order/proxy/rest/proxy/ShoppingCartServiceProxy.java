package au.com.nab.icommerce.order.proxy.rest.proxy;

import au.com.nab.icommerce.base.dto.JsonResponse;
import au.com.nab.icommerce.order.dto.BasketDTO;
import au.com.nab.icommerce.order.proxy.rest.client.ShoppingCartClient;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartServiceProxy {


    private final ShoppingCartClient shoppingCartClient;

    public ShoppingCartServiceProxy(ShoppingCartClient shoppingCartClient) {
        this.shoppingCartClient = shoppingCartClient;
    }

    public JsonResponse<BasketDTO> getBasketById(Long id) {
        return shoppingCartClient.getBasketById(id);
    }

    public JsonResponse<BasketDTO> updateBasket(BasketDTO basketDTO) {
        return shoppingCartClient.updateBasket(basketDTO);
    }
}
