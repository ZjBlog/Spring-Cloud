package eureka.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import javax.persistence.Cacheable;

/**
 * /{application}/{profile}[/{label}]
 * /{application}-{profile}.yml
 * /{label}/{application}-{profile}.yml
 * /{application}-{profile}.properties
 * /{label}/{application}-{profile}.properties
 * 其中“应用程序”作为SpringApplication中的spring.config.name注入
 * （即常规的Spring Boot应用程序中通常是“应用程序”），“配置文件”是活动配置文件
 * （或逗号分隔列表的属性），“label”是可选的git标签（默认为“master”）。
 * 路径访问可以看到配置信息
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }
}
