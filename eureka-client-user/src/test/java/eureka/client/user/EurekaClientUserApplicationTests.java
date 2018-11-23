package eureka.client.user;

import eureka.client.user.Service.Hystrix.CommandHelloWorld;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rx.Observable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class EurekaClientUserApplicationTests {

    //同步执行
    //@Test
    public void contextLoads() {
        System.out.println(new CommandHelloWorld().execute());
    }

    //异步执行
    //@Test
    public void testAsynchronous() throws ExecutionException, InterruptedException {
        Future<String> fWorld = new CommandHelloWorld().queue();
        System.out.println(fWorld.get());  //一步执行用get()来获取结果
    }


    @Test
    public void test(){
        Observable<String> h=new CommandHelloWorld().observe();
    }

}
