package eurekadb.db.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : ZJ
 * @date : 18-11-1 下午6:14
 */
@RestController
public class TestController {

    @Value("${server.port}")
    String port;

    @GetMapping("/db")
    public String test() {
        return "Hi,I am " + port;
    }
}