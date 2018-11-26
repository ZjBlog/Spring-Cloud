package eureka.client.user.Service.Hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.observables.SyncOnSubscribe;

/**
 * @author : ZJ
 * @date : 18-11-26 下午4:48
 */
public class ObservableCommandHelloWorld1 {
    /**
     *  EAGER参数表示使用observe()方式执行
     */
    @HystrixCommand(observableExecutionMode = ObservableExecutionMode.EAGER, fallbackMethod = "observFailed") //使用observe()执行方式
    public Observable<String> getUserById(final Long id) {

        Observable.create(new SyncOnSubscribe<String, String>() {
            @Override
            protected String generateState() {
                return null;
            }

            @Override
            protected String next(String state, Observer<? super String> observer) {
                return null;
            }
        });

        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if(!subscriber.isUnsubscribed()) {
                        subscriber.onNext("张三的ID:");
                        int i = 1 / 0; //抛异常，模拟服务降级
                        subscriber.onNext(String.valueOf(id));
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
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
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if(!subscriber.isUnsubscribed()) {
                        subscriber.onNext("找到");
                        subscriber.onNext(name);
                        int i = 1/0; ////抛异常，模拟服务降级
                        subscriber.onNext("了");
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    private String toObserbableError(String name) {
        return "toObserbableError--->" + name;
    }

}
