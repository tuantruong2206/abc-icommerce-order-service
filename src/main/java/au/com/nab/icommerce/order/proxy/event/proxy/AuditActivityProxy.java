package au.com.nab.icommerce.order.proxy.event.proxy;

import au.com.nab.icommerce.order.proxy.event.source.AuditActivitySource;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding({AuditActivitySource.class})
public class AuditActivityProxy {
}
