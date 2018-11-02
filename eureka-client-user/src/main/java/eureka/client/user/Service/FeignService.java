package eureka.client.user.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author : ZJ
 * @date : 18-11-2 下午5:02
 */
@FeignClient("db-service")
public interface FeignService {

    @RequestMapping(value = "/db",method = RequestMethod.GET)
    String str();

    @GetMapping("/db")
    String str1();
}
