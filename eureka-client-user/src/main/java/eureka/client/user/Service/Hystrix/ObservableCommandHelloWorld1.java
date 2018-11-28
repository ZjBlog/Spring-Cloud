package eureka.client.user.Service.Hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.observables.AsyncOnSubscribe;
import rx.observables.SyncOnSubscribe;

/**
 * @author : ZJ
 * @date : 18-11-26 下午4:48
 */
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
                return id;
            }

            @Override
            protected String next(String state, Observer<? super String> observer) {
                observer.onNext(state);
                observer.onCompleted();
                return state;
            }
        });
    }

    private String observFailed(Long id) {
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

}
