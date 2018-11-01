package eurekadb.db.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : ZJ
 * @date : 18-11-1 下午6:14
 */
@RestController
public class TestController {

    @GetMapping("/db/test")
    public String test(){

        return "ok";
    }
}
