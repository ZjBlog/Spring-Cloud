package eureka.client.user.Service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import eureka.client.user.Config.FeignConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

/**
 * @author : ZJ
 * @date : 18-11-7 下午4:39
 */
@Service
// 多个方法默认 降级错误方法
@DefaultProperties(defaultFallback = "error")
public class DBServiceMult {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand
    public String test1(){
        return restTemplate.getForObject("http://db-service/db",String.class);
    }


    @HystrixCommand
    public String test(){

        return restTemplate.getForObject("http://db-service/db",String.class);
    }

    public String error() {
        return "sorry,error!";
    }

    //特定方法指定错误回调
    @HystrixCommand(fallbackMethod = "error1")
    public String test2(){

        return restTemplate.getForObject("http://db-service/db",String.class);
    }
    public String error1() {
        return "sorry,error!";
    }


}
