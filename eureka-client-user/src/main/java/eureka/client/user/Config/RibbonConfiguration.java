package eureka.client.user.Config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 问题:修改其他的配置ru ipingurl 会出现问题 目前不知道怎么解决
 */
/**
 * 如果使用ribbon这样配置
 * 单独配置ribbon的负载均衡的策略及规则
 * @author : ZJ
 * @date : 18-11-2 上午10:57
 */

/**
 * feign集成ribbon来使用负载能力 同过配置ribbon 使feign有不同的策略
 */
@Configuration
@ExcudeAnnotation
public class RibbonConfiguration {

//
//    /**
//     * 修改城增加权重规则
//     * 默认有轮训 随机等好几种规则
//     * @param config
//     * @return
//     */
    @Bean
    public IRule ribbonRule() {
        //return new WeightedResponseTimeRule();
        //return  new RandomRule();
        return new RoundRobinRule();
    }
//
//    /**
//     * LoadBalancer接口实现类做了以下的一些事情：
//     * 1.维护了存储服务实例Server对象的二个列表。一个用于存储所有服务实例的清单，一个用于存储正常服务的实例清单
//     * 2.初始化得到可用的服务列表，启动定时任务去实时的检测服务列表中的服务的可用性，并且间断性的去更新服务列表，结合注册中心。
//     * 3.选择可用的服务进行调用（这个一般交给IRule去实现，不同的轮询策略）
//     * 可以换成其他策略
//     * @param config
//     * @return
//     */

}
