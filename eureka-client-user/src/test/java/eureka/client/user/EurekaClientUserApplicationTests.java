package eureka.client.user;

import eureka.client.user.Service.Hystrix.CommandHelloWorld;
import eureka.client.user.Service.Hystrix.CommandHelloWorld1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.observables.BlockingObservable;

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


    //@Test
    public void test() {
        /**
         *
         *           返回的是Hot Observable,HotObservable,不论 “事件源” 是否有“订阅者”
         *           都会在创建后对事件进行发布。所以对于Hot Observable的每一个“订阅者”都有
         *          可能从“事件源”的中途开始的，并可能只是看到了整个操作的局部过程
         *          hot Obervavle 是共享数据，对于所有的订阅者同一时刻收到的数据是相同的
         *          通过publish() 把cold  --》hot
         */
        Observable<String> h = new CommandHelloWorld().observe();
        try {
            //异步执行,要看到数据暂停下
            Thread.sleep(3000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        h.subscribe(new Observer<String>() {
//            @Override
//            public void onCompleted() {
//                System.out.println("Completed");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onNext(String s) {
//                System.out.println("=========onNext: " + s);
//            }
//        });
        h.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("==================call:" + s);
            }
        });
    }

    /**
     *
     *           Cold Observable在没有 “订阅者” 的时候并不会发布时间，
     *           而是进行等待，知道有 “订阅者” 之后才发布事件，所以对于
     *           Cold Observable的订阅者，它可以保证从一开始看到整个操作的全部过程。
     *
     *
             */
   // @Test
    public void  test2(){
        Observable<String> co = new CommandHelloWorld().toObservable();
//        BlockingObservable<String> stringBlockingObservable = co.toBlocking();
//        String s = stringBlockingObservable.toFuture().get();
        System.out.println(co.toBlocking().single());
    }

    @Test
    public void test3(){
        CommandHelloWorld1 helloWorld1=new CommandHelloWorld1();
        System.out.println(helloWorld1.getUserId("d"));
        try {
            System.out.println(helloWorld1.getUserName(1l).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
