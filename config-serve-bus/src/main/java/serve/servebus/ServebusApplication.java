package serve.servebus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 127.0.0.1:5088/user-service/dev
 * 127.0.0.1:5088/user-service.yml
 * 127.0.0.1:5088/user-service-dev.yml
 * 查看git上的配置信息
 */

/**
 * 当开启bus之后,配置文件有改动的时候发送post请求 bus-refresh 会自动刷新配置
 *  类或方法上加@RefreshScope这个注解,那么类或方法中的配置信息会自动刷新
 *
 * 可以在git上配置gitwook 当有配置改变时自动请求配置config serve
 * curl -X POST http://localhost:5088/actuator/bus-refresh/
 * bus-refresh是全部刷新
 * 局部刷新
 * /actuator/bus-refresh/{destination}端点的 destination 参数来定位要刷新的应用程序。
 *  curl -X POST http://localhost:5088/actuator/bus-refresh/order-service:**
 *  只刷新所有order-service服务
 *  http://localhost:5088/actuator/bus-refresh/user-service:**
 *  只刷新所有user-service服务
 *  http://localhost:5088/actuator/bus-refresh/user-service:8000
 *  只刷新user-service:8000这个服务
 *
 */

/**
 * 开启消息追踪http://127.0.0.1:5088/actuator/httptrace
 * 配置中暴露路径     include: '*'  暴露所有路径
 */
@SpringBootApplication
@EnableConfigServer
/**
 * 开启configsever
 */
public class ServebusApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServebusApplication.class, args);
    }

}

