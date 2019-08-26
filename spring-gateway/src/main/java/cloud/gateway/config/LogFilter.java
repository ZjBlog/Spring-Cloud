package cloud.gateway.config;

import feign.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.pattern.PathPattern;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.URI_TEMPLATE_VARIABLES_ATTRIBUTE;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.addOriginalRequestUrl;

/**
 * @author : ZJ
 * @date : 19-4-10 下午5:49
 */

/**
 * 路径按规则重写
 */
@Component
@Slf4j
public class LogFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest req = exchange.getRequest();

        addOriginalRequestUrl(exchange, req.getURI());

        String userId = exchange.getRequest().getHeaders().getFirst("userId");
        String path = req.getURI().getRawPath();
        String newPath = path;
        if(userId!=null){
            newPath="/zj/create2";
        }

        ServerHttpRequest request = req.mutate().path(newPath).build();

        exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, request.getURI());

        return chain.filter(exchange.mutate().request(request).build());
    }

    /**
     *
     * @return
     */

    @Override
    public int getOrder() {
        return -1000;
    }
}
