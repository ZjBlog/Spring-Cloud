package eureka.client.user.controller;

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

    @GetMapping("/user")
    public String test(){
        return  restTemplate.getForObject("http://db-service-1/db",String.class);
    }

    @GetMapping("/user2")
    public String test2(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("db-service-1");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/db";
        System.out.println(url);
        RestTemplate restTemplate1=new RestTemplate();
        return restTemplate1.getForObject(url, String.class);
    }
}
