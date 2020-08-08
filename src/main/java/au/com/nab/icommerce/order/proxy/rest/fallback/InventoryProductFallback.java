package au.com.nab.icommerce.order.proxy.rest.fallback;

import au.com.nab.icommerce.base.dto.JsonResponse;
import au.com.nab.icommerce.order.dto.BasketDTO;
import au.com.nab.icommerce.order.dto.BasketDetailDTO;
import au.com.nab.icommerce.order.proxy.rest.client.InventoryProductClient;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashSet;

@Component
public class InventoryProductFallback implements InventoryProductClient {


    @Override
    public JsonResponse<Object> doOrder(BasketDTO basketDTO) {
        JsonResponse<Object> jsonResponse = new JsonResponse<>(new BasketDTO(-1L, "fallback", false, Instant.now(), Instant.now(), new HashSet<BasketDetailDTO>()));
        jsonResponse.setSuccess(false);
        return jsonResponse;
    }
}
