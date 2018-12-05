package eureka.client.user.Service.HystrixCollapser;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import eureka.client.user.Service.FeignService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author : ZJ
 * @date : 18-11-29 下午3:12
 */
@Service
@Slf4j
public class HystrixCollapserDemo {

    @Autowired
    private FeignService feignService;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 请求合并
     * @return
     */
    /**
     * 合并请求的时间窗口为100ms,窗口时间内最多合并200个请求
     * @param id
     * @return
     */
    /**
     * 请求缓存必须有HystrixRequestContext 请求上下文
     * @param id
     * @return
     */
    @HystrixCollapser(batchMethod ="getStr",scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds",value = "5000"),
            @HystrixProperty(name = "maxRequestsInBatch",value = "200")
    })
    public Future<String> test(String id){
        return null;
    }

    @HystrixCommand(fallbackMethod = "getStrBack")
    public List<String> getStr(List<String> ids){
        log.info("合并操作线程 --> {} --> params --> {}", Thread.currentThread().getName(), ids.toString());
        log.info(ids.toString()+"====");
        String join = StringUtils.join(ids, ",");
        log.info("==="+ join);
        String[] forObject = restTemplate.getForObject("http://db-service/names?ids={1}", String[].class, join);
        return Arrays.asList(forObject);
       // return  feignService.userNames1(join);
    }

    public List<String> getStrBack(List<String> ids){
        log.info("================");
        log.error("出错了");
        return ids;
    }
}
