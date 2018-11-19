package eurekadb.db.controller;

import eurekadb.db.entity.User;
import eurekadb.db.repository.UserRepsoitory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private UserRepsoitory userRepsoitory;

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
    @GetMapping("/init")
    public void test1() {
        log.info("init");
        User user=null;
        for (Integer i=0;i<5;i++){
            user=new User();
            user.setName("" + i);
            user.setPassword("" + i);
            userRepsoitory.save(user);
        }
    }
}
