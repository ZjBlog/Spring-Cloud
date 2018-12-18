package enreka.order.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : ZJ
 * @date : 18-12-18 下午5:39
 */

@RestController
@RefreshScope
public class ConfigController {

    @Value("${info1}")
    private String info;

    @GetMapping("/test")
    public String test(){
        return info;
    }
}
