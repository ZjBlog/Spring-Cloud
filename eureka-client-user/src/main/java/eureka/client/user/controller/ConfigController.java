package eureka.client.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : ZJ
 * @date : 18-12-7 下午5:13
 */

/**
 * @RefreshScope 动态修改配置
 * 当配置更改时，标有@RefreshScope的Spring @Bean将得到特殊处理。
 * 这解决了状态bean在初始化时只注入配置的问题。
 * 例如，如果通过Environment更改数据库URL时DataSource有开放连接，
 * 那么我们可能希望这些连接的持有人能够完成他们正在做的工作。
 * 然后下一次有人从游泳池借用一个连接，他得到一个新的URL。
 */

/**
 * 改变之前调用http://127.0.0.1:8088/actuator/refresh 刷新下
 * 刷新配置路径management:
 *   endpoints:
 *     web:
 *       exposure:
 *         include: info,health,refresh
 */
@RestController
@RefreshScope
public class ConfigController {

//    @Value("${spring-config}")
//    private String hello;


    @RequestMapping("/hello")
    public String from() {
        return "hello";
    }
}
