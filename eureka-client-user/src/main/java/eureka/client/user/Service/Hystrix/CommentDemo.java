package eureka.client.user.Service.Hystrix;

/**
 * @author : ZJ
 * @date : 18-11-23 下午3:51
 */

import com.netflix.hystrix.*;

/**
 * hystrixcommand配置可以在配置文件中也可以在代码中 也可以在注解中
 */
public class CommentDemo extends HystrixCommand<String> {


    public CommentDemo() {
        super(
                Setter
                        // 配置全局唯一标识服务分组的名称 当我们进行监控时，相同分组的服务会聚合在一起，必填项
                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey("group-product"))
                        // command key ，如果不进行配置，默认为类的 SimpleName，最好唯一
                        .andCommandKey(HystrixCommandKey.Factory.asKey("command-selectOne(String)"))
                        // 配置命令的一些参数
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
                                        // 是否启动超时处理  默认为 true
                                        .withExecutionTimeoutEnabled(true)
                                        // 超时时间为 10s
                                        .withExecutionTimeoutInMilliseconds(10000)
                                        // 使用线程池进行隔离
                                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                                        // 隔离测试为 THREAD 时，执行线程执行超时时，是否进行中断处理 默认为 true
                                        .withExecutionIsolationThreadInterruptOnTimeout(true)
                                        // 当 Future#cancel(true) 是否进行中断处理  默认为 false
                                        .withExecutionIsolationThreadInterruptOnFutureCancel(true)

                                        // 打开短路器
                                        .withCircuitBreakerEnabled(true)
                                        // 如果在一个采样时间窗口内，失败率超过该配置，则自动打开熔断开关实现降级处理，即快速失败。默认配置下采样周期为10s，失败率为50%
                                        .withCircuitBreakerErrorThresholdPercentage(50)
                                        // 在短路器闭合情况下，在进行失败率判断之前，一个采样周期内必须进行至少N个请求才能进行采样统计，目的是有足够的采样使得失败率计算正确，默认为20
                                        .withCircuitBreakerRequestVolumeThreshold(60)
                                        // 短路器闭合的重试时间窗口，且在该时间窗口内只允许一次重试。即在熔断开关打开后，在该时间窗口允许有一次重试，如果重试成功，则将重置Health采样统计并闭合断路器开关实现快速恢复，否则断路器开关还是打开状态，执行快速失败。
                                        .withCircuitBreakerSleepWindowInMilliseconds(10000)
                                        // 是否强制关闭断路器，如果强制关闭则请求不会进行 fallback 处理
                                        .withCircuitBreakerForceClosed(false)
                                        // 是否强制打开短路器，如果打开了那么将会直接进行降级处理
                                        .withCircuitBreakerForceOpen(false)

                                        // 是否启用降级处理 默认是 true
                                        .withFallbackEnabled(true)
                                        // fallback方法的信号量配置，配置getFallback方法并发请求的信号量，如果请求超过了并发信号量限制，则不再尝试调用getFallback方法，而是快速失败，默认信号量为10
                                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(100)

                        )
                        // 配置全局唯一标识线程池的名称 相同名称的线程池是同一个  如果不进行配置， 默认是 分组的名称
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("select-product-thread-pool"))
                        // 线程池属性配置
                        .andThreadPoolPropertiesDefaults(
                                HystrixThreadPoolProperties.Setter()
                                        // 配置核心线程数的大小
                                        .withCoreSize(5)
                                        // 配置最大的线程数大小
                                        .withMaximumSize(10)
                                        // 允许最大线程数超过核心线程数，默认是 false ,如果这个值不为 true ,则上方配置的 withMaximumSize(10) 不会生效
                                        .withAllowMaximumSizeToDivergeFromCoreSize(true)
                                        // 线程池中空闲线程的生存时间
                                        .withKeepAliveTimeMinutes(5)
                                        // 配置线程池队列的最大大小
                                        .withMaxQueueSize(500)
                                        // 限制当前队列的大小，通过改变这个参数，可以实现动态改变队列的大小  当队列的大小超过 这个值 时会fallback
                                        .withQueueSizeRejectionThreshold(490)
                        )
        );
    }

    @Override
    protected String run() throws Exception {
        return "Demo";
    }
}
