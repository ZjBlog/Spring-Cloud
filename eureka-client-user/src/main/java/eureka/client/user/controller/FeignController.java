package eureka.client.user.controller;

import eureka.client.user.Entity.User;
import eureka.client.user.Service.FeignService;
import eureka.client.user.Service.HystrixCollapser.HystrixCollapserDemo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : ZJ
 * @date : 18-11-29 下午3:50
 */
@RestController
@Slf4j
public class FeignController {

    @Autowired
    FeignService feignService;

    @Autowired
    HystrixCollapserDemo hystrixCollapserDemo;


    @GetMapping("/feign/{id}")
    public String test(@PathVariable("id") String id){
        return  feignService.userName(id);
    }

    @GetMapping("/feign1")
    public List<String> test7(@RequestParam("ids") String ids){
        return  feignService.userNames1(ids);
    }

    @GetMapping("/feign")
    public String test2(@RequestParam("ids") String ids){
        return  feignService.userNames(ids);
    }
    @GetMapping("/feign/id/name")
    public String test3(@RequestParam("id") String id,@RequestParam("name") String name){
        return  feignService.userNameId(id,name);
    }

    @GetMapping("/feign/ids")
    public String test6(@RequestParam("id") String id){
        return  feignService.userNamesd(id);
    }

    @GetMapping("/feign/id/names")
    public String test4(@RequestParam("id") String id,@RequestParam("name") String name){
        Map<String,String> map=new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        return  feignService.userNameId2(map);
    }
    @GetMapping("/feign/add")
    public String test4(){
        User user=new User();
        user.setName("哈哈");
        user.setPassword("dd");
        return  feignService.userAdd(user);
    }

    @GetMapping("/feign/merge")
    public String test5(){
        for (Integer i=0;i<8;i++){
            log.info("=== "+i+hystrixCollapserDemo.test(i + ""));
        }
        return "ok";
    }
}
