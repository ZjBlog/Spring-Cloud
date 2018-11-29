package eureka.client.user.Service.HystrixCollapser;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import eureka.client.user.Service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : ZJ
 * @date : 18-11-29 下午3:12
 */
@Service
public class HystrixCollapserDemo {

    @Autowired
    private FeignService feignService;

    /**
     * 请求合并
     * @return
     */
    @HystrixCollapser(batchMethod ="getStr" )
    public List<String> test(){
        return null;
    }

    public String getStr(){
        return feignService.str();
    }
}
