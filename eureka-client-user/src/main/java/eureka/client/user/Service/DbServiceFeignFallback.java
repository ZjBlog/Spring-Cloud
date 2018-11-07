package eureka.client.user.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : ZJ
 * @date : 18-11-7 下午3:47
 */
@Slf4j
@Component
public class DbServiceFeignFallback implements DbServiceFeign {
    @Override
    public String str() {
        log.info("服务降级.....");
        return "失败了";
    }
}
