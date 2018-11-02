package eureka.client.user.Config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * 配置ribbon的负载均衡的策略及规则
 * @author : ZJ
 * @date : 18-11-2 上午10:57
 */
public class RibbonConfiguration {
    @Autowired
    IClientConfig ribbonClientConfig;

    @Bean
    public IPing ribbonPing(IClientConfig config) {
        return new PingUrl();
    }

    /**
     * 修改城增加权重规则
     * 默认有轮训 随机等好几种规则
     * @param config
     * @return
     */
    @Bean
    public IRule ribbonRule(IClientConfig config) {
        //return new WeightedResponseTimeRule();
        return  new RandomRule();
    }

    /**
     * LoadBalancer接口实现类做了以下的一些事情：
     * 1.维护了存储服务实例Server对象的二个列表。一个用于存储所有服务实例的清单，一个用于存储正常服务的实例清单
     * 2.初始化得到可用的服务列表，启动定时任务去实时的检测服务列表中的服务的可用性，并且间断性的去更新服务列表，结合注册中心。
     * 3.选择可用的服务进行调用（这个一般交给IRule去实现，不同的轮询策略）
     * 可以换成其他策略
     * @param config
     * @return
     */
    @Bean
    public ILoadBalancer iLoadBalancer(IClientConfig config) {
        return new BaseLoadBalancer();
    }

}
