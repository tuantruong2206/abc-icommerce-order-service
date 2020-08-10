package au.com.nab.icommerce.order.proxy.rest.fallback;

import au.com.nab.icommerce.base.dto.JsonResponse;
import au.com.nab.icommerce.order.dto.BasketDTO;
import au.com.nab.icommerce.order.dto.BasketDetailDTO;
import au.com.nab.icommerce.order.proxy.rest.client.ShoppingCartClient;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashSet;

@Component
public class ShoppingCartFallback implements ShoppingCartClient {

    @Override
    public JsonResponse<BasketDTO> getBasketById(Long id) {
        JsonResponse myObj = new JsonResponse<>(new BasketDTO(-1L, "fallback", false, Instant.now(), Instant.now(), new HashSet<BasketDetailDTO>()));
        myObj.setSuccess(false);
        return new JsonResponse<>(new BasketDTO(-1L, "fallback", false, Instant.now(), Instant.now(), new HashSet<BasketDetailDTO>()));
    }

    @Override
    public JsonResponse<BasketDTO> updateBasket(BasketDTO basketDTO) {
        JsonResponse myObj = new JsonResponse<>(new BasketDTO(-1L, "fallback", false, Instant.now(), Instant.now(), new HashSet<BasketDetailDTO>()));
        myObj.setSuccess(false);
        return new JsonResponse<>(new BasketDTO(-1L, "fallback", false, Instant.now(), Instant.now(), new HashSet<BasketDetailDTO>()));
    }
}
