package eureka.client.user.Service;

import eureka.client.user.Config.FeignConfig;
import eureka.client.user.Entity.User;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author : ZJ
 * @date : 18-11-2 下午5:02
 */
@FeignClient(value = "db-service", configuration = FeignConfig.class)
public interface FeignService {
    // @RequestLine("GET /db")
    @RequestMapping(value = "/db", method = RequestMethod.GET)
    String str();

    @GetMapping("/db")
        //@RequestLine("GET /db")
    String str1();


    /**
     * get方法传参
     * @param id
     * @return
     */
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    String userName(@RequestParam("id") String id);

    @RequestMapping(value = "/names", method = RequestMethod.GET)
    String userNames(@RequestParam("ids") String id);

    @RequestMapping(value = "/names?ids={ids}", method = RequestMethod.GET)
    List<String> userNames1(@RequestParam("ids") String id);

    @RequestMapping(value = "/name/{id}", method = RequestMethod.GET)
    String userNamesd(@PathVariable("id") String id);
    /**
     * 多个参数
     * @param
     * @return
     */
    @RequestMapping(value = "/name/id",method =RequestMethod.GET)
    String userNameId(@RequestParam("id") String id,@RequestParam("name") String name);

    @RequestMapping(value = "/name/id",method =RequestMethod.GET)
    String userNameId2(@RequestParam Map<String, String> map);

    /**
     * post方法传参
     * @return
     */
    @RequestMapping(value = "/add",method =RequestMethod.POST)
    String userAdd(@RequestBody User user);
}
