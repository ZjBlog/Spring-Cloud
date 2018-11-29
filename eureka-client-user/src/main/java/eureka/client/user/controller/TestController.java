package eureka.client.user.controller;

import eureka.client.user.Service.DbServiceFeign;
import eureka.client.user.Service.DbServiceFeign2;
import eureka.client.user.Service.FeignService;
import eureka.client.user.Service.Hystrix.ObservableCommandHelloWorld1;
import eureka.client.user.Service.HystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
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

    @Autowired
    ObservableCommandHelloWorld1 observableCommandHelloWorld1;


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


    @GetMapping("/user9")
    public List<String> test9(){
        List<String> list = new ArrayList<>();
        Observable<String> ddd = observableCommandHelloWorld1.getUserByName1("ddd");
        ddd.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                log.info("完成");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                list.add(s);
            }
        });
        return  list;
    }

}
