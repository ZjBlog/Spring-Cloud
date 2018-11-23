package eureka.client.user.Service.Hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author : ZJ
 * @date : 18-11-23 下午3:32
 */

/**
 * Hystrix有两个请求命令 HystrixCommand、HystrixObservableCommand。
 *
 * 　　HystrixCommand用在依赖服务返回单个操作结果的时候。又两种执行方式
 *
 * 　　  -execute():同步执行。从依赖的服务返回一个单一的结果对象，或是在发生错误的时候抛出异常。
 *
 * 　　  -queue();异步执行。直接返回一个Future对象，其中包含了服务执行结束时要返回的单一结果对象。
 */
public class CommandHelloWorld extends HystrixCommand<String> {

    public CommandHelloWorld() {
        super(HystrixCommandGroupKey.Factory.asKey("Hello"));
    }

    @Override
    protected String run() throws Exception {
        return "Hello World";
    }

    @Override
    protected String getFallback() {
        return "faild";
    }
}
