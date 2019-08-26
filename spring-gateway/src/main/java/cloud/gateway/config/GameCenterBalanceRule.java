package cloud.gateway.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.apache.commons.lang.math.RandomUtils;

import java.util.List;

/**
 * @author : ZJ
 * @date : 19-8-23 下午3:19
 * 自定义的负载规则
 * 根据userId对服务进行负载均衡。同一个用户id的请求，都转发到同一个服务实例上面。
 */
public class GameCenterBalanceRule extends AbstractLoadBalancerRule {
    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        System.out.println(clientConfig);
    }

    /**
     * //这里的key就是过滤器中传过来的userId
     * @param key
     * @return
     */
    @Override
    public Server choose(Object key) {
        List<Server> servers = this.getLoadBalancer().getReachableServers();
        if (servers.isEmpty()) {
            return null;
        }
        if (servers.size() == 1) {
            return servers.get(0);
        }
        if (key == null) {
            return randomChoose(servers);
        }
        return hashKeyChoose(servers, key);
    }
    /**
     *
     * <p>Description:随机返回一个服务实例 </p>
     * @param servers
     * @return
     * @author wgs
     * @date  2019年3月15日 下午2:25:23
     *
     */
    private Server randomChoose(List<Server> servers) {
        int randomIndex = RandomUtils.nextInt(servers.size());
        return servers.get(randomIndex);
    }

    /**
     *
     * <p>Description:使用key的hash值，和服务实例数量求余，选择一个服务实例 </p>
     * @param servers
     * @param key
     * @return
     * @author wgs
     * @date  2019年3月15日 下午2:25:36
     *
     */
    private Server hashKeyChoose(List<Server> servers, Object key) {
        int hashCode = Math.abs(key.hashCode());
        if (hashCode < servers.size()) {
            return servers.get(hashCode);
        }
        int index = hashCode % servers.size();
        return servers.get(index);

    }
}
