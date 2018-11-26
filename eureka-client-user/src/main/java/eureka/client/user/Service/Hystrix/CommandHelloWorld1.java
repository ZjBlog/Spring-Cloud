package eureka.client.user.Service.Hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author : ZJ
 * @date : 18-11-26 下午3:24
 */
@Service
public class CommandHelloWorld1 {

    /**
     * 同步的方式。
     * fallbackMethod定义降级
     */
    @HystrixCommand(fallbackMethod = "helloFallback")
    public String getUserId(String name) {

        return "你好:" + name;
    }

    public String helloFallback(String name) {
        return "error";}

    /**
     *              异步的执行 返回future
     */
    @HystrixCommand(fallbackMethod = "getUserNameError")
    public Future<String> getUserName(final Long id) {
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                return "小明:" + id;
            }
        };
    }

    public String getUserNameError(Long id) {
        return "faile";
    }
}
