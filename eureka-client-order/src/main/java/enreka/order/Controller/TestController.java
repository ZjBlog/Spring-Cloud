package enreka.order.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author : ZJ
 * @date : 18-11-5 下午4:16
 */

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/order")
    public String test(){
        return  restTemplate.getForObject("http://db-service/db",String.class);
    }
}
