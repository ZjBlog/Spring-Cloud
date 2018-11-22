package eureka.client.user.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import eureka.client.user.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * @author : ZJ
 * @date : 18-11-5 上午11:00
 */

@Service
public class HystrixService {

    @Autowired
    private FeignService feignService;

    @Autowired
    private RestTemplate restTemplate;


    /**
     * 基于Hystrix的方式进行降级
     * 注解形式
     * 方法上设置超时时间
     * 开启或关闭超时时间
     * @return
     */
    /**
     * 配置文件中配置 或者用注解形式配置
     * @return
     */
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.timeout.enabled", value = "true"),
            @HystrixProperty(name ="execution.isolation.thread.timeoutInMilliseconds",value = "7000")
    }, threadPoolProperties = {@HystrixProperty(name="coreSize",value = "10")},fallbackMethod = "error")
    public String test(){
        return restTemplate.getForObject("http://db-service/db",String.class);
    }

    @HystrixCommand(fallbackMethod = "error")
    public String test1(){
        return feignService.str();
    }

    //https://blog.csdn.net/xiao_jun_0820/article/details/78423985
    //@HystrixCollapser()
    public List<User> test2(){

        Collections.emptyList();
        return null;
    }

//    @HystrixCommand(fallbackMethod = "error")
//    public User test2(){
//
//    }

    public String error() {
        return "sorry,error!";
    }


}
