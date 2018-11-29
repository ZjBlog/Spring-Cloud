package eureka.client.user.Service.HystrixCollapser;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import eureka.client.user.Service.FeignService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : ZJ
 * @date : 18-11-29 下午3:12
 */
@Service
@Slf4j
public class HystrixCollapserDemo {

    @Autowired
    private FeignService feignService;

    /**
     * 请求合并
     * @return
     */
    /**
     * 合并请求的时间窗口为100ms,窗口时间内最多合并200个请求
     * @param id
     * @return
     */
    @HystrixCollapser(batchMethod ="getStr",scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds",value = "100"),
            @HystrixProperty(name = "maxRequestsInBatch",value = "200")
    })
    public String test(String id){
        return null;
    }

    @HystrixCommand(fallbackMethod = "getStrBack")
    public List<String> getStr(List<String> ids){
        String join = StringUtils.join(ids, ",");
        return  feignService.userNames1(join);
    }

    public List<String> getStrBack(List<String> ids){
        log.error("出错了");
        return ids;
    }
}
