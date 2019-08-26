package eureka.client.user.controller;

import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : ZJ
 * @date : 19-8-23 上午10:45
 */
@RestController
public class TestController1 {


    @Value("${server.port}")
    private String str;

    @GetMapping("/zj/test")
    public String test5(){
        return  "Hello,port:"+str;
    }

    @GetMapping("/hehe")
    public String heh(){
        return  "Hello,hehe";
    }

    @GetMapping("/create1")
    public String v1(){
        return  "Hello,V1";
    }


    @GetMapping("/create2")
    public String v2(){
        return  "Hello,V2";
    }
}
