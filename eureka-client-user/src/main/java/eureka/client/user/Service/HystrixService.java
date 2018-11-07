package eureka.client.user.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.timeout.enabled", value = "true"),
            @HystrixProperty(name ="execution.isolation.thread.timeoutInMilliseconds",value = "7000")
    },fallbackMethod = "error")
    public String test1(){
        return restTemplate.getForObject("http://db-service/db",String.class);
    }


    @HystrixCommand(fallbackMethod = "error")
    public String test(){

        return feignService.str();
    }

    public String error() {
        return "sorry,error!";
    }


}
