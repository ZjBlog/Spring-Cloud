package eureka.client.user.Service;

import eureka.client.user.Config.FeignConfig;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
