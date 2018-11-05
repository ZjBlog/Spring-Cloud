package eurekadb.db.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : ZJ
 * @date : 18-11-1 下午6:14
 */
@RestController
@Slf4j
public class TestController {

    @Value("${server.port}")
    String port;

    @GetMapping("/db")
    public String test(HttpServletRequest request) {

        log.info(request.getHeader("token"));
        if("9088".equals(port)) {
            try {
                Thread.sleep(5000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return "Hi,I am " + port;
    }
}
