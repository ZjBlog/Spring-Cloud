package cloud.gateway.config;

import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.config.LoadBalancerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : ZJ
 * @date : 19-8-23 下午3:24
 */
@Configuration
public class LoadBalancedBean {

    @Bean
    public UserLoadBalancerClientFilter userLoadBalanceClientFilter(LoadBalancerClient client, LoadBalancerProperties properties) {
        return new UserLoadBalancerClientFilter(client, properties);
    }

    @Bean
    public IRule ribbonRule() {
        //return new WeightedResponseTimeRule();
        // return new RandomRule();
        return new GameCenterBalanceRule();
    }
}
