package eureka.client.user.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author : ZJ
 * @date : 18-11-7 下午3:46
 */

/**
 * 基于Feign的fallback的降级 返回降级实现
 */
@Service
@FeignClient(name = "db-service",fallback = DbServiceFeignFallback.class)
public interface DbServiceFeign {

    @GetMapping("/db")
    String str();
}
