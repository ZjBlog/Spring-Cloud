package eureka.client.user.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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


    @HystrixCommand(fallbackMethod = "error")
    public String test1(){
        return restTemplate.getForObject("http://db-service/db",String.class);
    }


    //@HystrixCommand(fallbackMethod = "error")
    public String test(){

        return feignService.str();
    }

    public String error() {
        return "sorry,error!";
    }


}
