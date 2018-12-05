package eureka.client.user.Config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import lombok.Builder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author : ZJ
 * @date : 18-11-1 下午6:15
 */
@Configuration
public class AppConfig {
    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }


    /**
     * HystrixRequestContext 上下文设置
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean registrationBean=new FilterRegistrationBean(new HystrixRequestContextServletFilter());
        registrationBean.addUrlPatterns("/");
        return registrationBean;
    }

    /**
     * hystrix.stream 配置 Unable to connect to Command Metric Stream.
     * @return
     */
    /**
     * Hystrix Stream 线程池隔离的方法执行方法时有线程池项 信号没有
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
