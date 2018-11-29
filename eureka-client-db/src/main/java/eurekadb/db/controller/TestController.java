package eurekadb.db.controller;

import eurekadb.db.entity.User;
import eurekadb.db.repository.UserRepsoitory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    @PostMapping("/add")
    public String userAdd(@RequestBody User user){
        User save = userRepsoitory.save(user);
        return save.getId();
    };

    @GetMapping("/name/{id}")
    public String getNamed(@PathVariable("id") String id){
        Optional<User> user = userRepsoitory.findById(id);
        return user.get().getName();
    };

    @GetMapping("/name/id")
    public String getName(@RequestParam("id") String id,@RequestParam("name") String name){
        Optional<User> user = userRepsoitory.findById(id);
        return user.get().getName();
    };
    @GetMapping("/name")
    public String getName(@RequestParam("id") String id){
        Optional<User> user = userRepsoitory.findById(id);
        return user.get().getName();
    };

    @GetMapping("/names")
    public List<String> getNames(@RequestParam("ids") String id){
        List<User> byIdIN = userRepsoitory.findByIdIn(Arrays.asList(id.split(",")));
        return byIdIN.stream().map(s->s.getName()).collect(Collectors.toList());
    };

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
