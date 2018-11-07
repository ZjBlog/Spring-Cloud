package eureka.client.user.controller;

import eureka.client.user.Service.DbServiceFeign;
import eureka.client.user.Service.DbServiceFeign2;
import eureka.client.user.Service.FeignService;
import eureka.client.user.Service.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    FeignService feignService;


    @Autowired
    HystrixService hystrixService;

    @Autowired
    DbServiceFeign dbServiceFeign;

    @Autowired
    DbServiceFeign2 dbServiceFeign2;


    @GetMapping("/user")
    public String test(){
        return  restTemplate.getForObject("http://db-service/db",String.class);
    }

    @GetMapping("/user2")
    public String test2(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("db-service");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/db";
        System.out.println(url);
        RestTemplate restTemplate1=new RestTemplate();
        return restTemplate1.getForObject(url, String.class);
    }

    @GetMapping("/user3")
    public String test3(){

        return  feignService.str();
    }

    @GetMapping("/user4")
    public String test4(){

        return  feignService.str1();
    }

    @GetMapping("/user5")
    public String test5(){
        return  hystrixService.test();
    }

    @GetMapping("/user6")
    public String test6(){
        return  hystrixService.test1();
    }

    @GetMapping("/user7")
    public String test7(){
        return  dbServiceFeign.str();
    }


    @GetMapping("/user8")
    public String test8(){
        return  dbServiceFeign2.str();
    }


}
