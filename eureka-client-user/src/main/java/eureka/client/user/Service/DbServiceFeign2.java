package eureka.client.user.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : ZJ
 * @date : 18-11-7 下午3:53
 */

/**
 * 基于fallbackFactory 实现
 */
@Service
@FeignClient(name = "db-service",fallbackFactory = DbServiceFeign2Facktory.class)
public interface DbServiceFeign2 {

    @GetMapping("/db")
    String str();
    @GetMapping("/db1")
    String str1();
}
