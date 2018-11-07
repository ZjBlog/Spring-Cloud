package eureka.client.user.Service;

/**
 * @author : ZJ
 * @date : 18-11-7 下午3:54
 */

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 实现FallbackFactory方法 DbServiceFeign2 为@Feign注解标注的类型
 * 可以知道为什么产生了降级,
 */
@Component
@Slf4j
public class DbServiceFeign2Facktory implements FallbackFactory<DbServiceFeign2> {

    @Override
    public DbServiceFeign2 create(Throwable throwable) {
        return new DbServiceFeign2(){
            @Override
            public String str(){
                log.error("error",throwable);
                return "ddddd";
            }
            @Override
            public String str1() {
                return throwable.getMessage();
            }
        };
    }
}
