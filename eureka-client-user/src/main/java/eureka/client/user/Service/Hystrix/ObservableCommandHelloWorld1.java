package eureka.client.user.Service.Hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.observables.AsyncOnSubscribe;
import rx.observables.SyncOnSubscribe;

/**
 * @author : ZJ
 * @date : 18-11-26 下午4:48
 */
@Component
@Slf4j
public class ObservableCommandHelloWorld1 {
    /**
     *  EAGER参数表示使用observe()方式执行
     */
    @HystrixCommand(observableExecutionMode = ObservableExecutionMode.EAGER, fallbackMethod = "observFailed") //使用observe()执行方式
    public Observable<String> getUserById(String id) {

       return Observable.create(new SyncOnSubscribe<String, String>() {
            @Override
            protected String generateState() {
               // log.info("......1");
                return "1";
            }

            @Override
            protected String next(String state, Observer<? super String> observer) {
                observer.onNext(state);
                observer.onCompleted();
                return state;
            }
        });
    }

    private String observFailed(String id) {
        return "observFailed---->" + id;
    }

    /**
     * LAZY参数表示使用toObservable()方式执行
     */
    @HystrixCommand(observableExecutionMode = ObservableExecutionMode.LAZY, fallbackMethod = "toObserbableError") //表示使用toObservable()执行方式
    public Observable<String> getUserByName(final String name) {

        return Observable.create(new SyncOnSubscribe<String, String>() {
            @Override
            protected String generateState() {
                int i=1/0;
                return name;
            }

            @Override
            protected String next(String state, Observer<? super String> observer) {
                observer.onNext(state);
                observer.onCompleted();
                return state;
            }
        });
    }

    private String toObserbableError(String name) {
        return "toObserbableError--->" + name;
    }

    @HystrixCommand(observableExecutionMode = ObservableExecutionMode.LAZY, fallbackMethod = "toObserbableError") //表示使用toObservable()执行方式
    public Observable<String> getUserByName1(final String name) {
        return Observable.just("1","2");
    }

}
