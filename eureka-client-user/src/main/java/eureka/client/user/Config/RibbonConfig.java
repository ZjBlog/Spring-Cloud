package eureka.client.user.Config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

/**
 * @author : ZJ
 * @date : 18-11-5 下午3:26
 */

/**
 * @RibbonClients(defaultConfiguration = RibbonConfiguration.class)
 * 全局默认
 */

/**
 * 特定一个
 */
@RibbonClient(name = "db-service",configuration = RibbonConfiguration.class)
public class RibbonConfig {
}
