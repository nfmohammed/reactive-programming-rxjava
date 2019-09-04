package chapter01;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class example07 {
    public static void main(String[] args) throws InterruptedException {

        //"interval" factory example:: emit numbers from 0 every 100 ms
        Observable.interval(100, TimeUnit.MILLISECONDS).subscribe(System.out::println);
        Thread.sleep(2000); //make the main thread sleep so that observer can execute

        //"range" facotry example
        Observable.range(0, 10).subscribe(System.out::println);

        //"empty" factory only emits onComplete event
        Observable.empty().subscribe(
                (e) -> System.out.println("onNext is not called for empty"),
                Throwable::printStackTrace,
                () -> System.out.println("only onComplete is called on empty observable")
        );

        //"never" factory never emits events to it's subscriber
        Observable.never().subscribe(
                (e) -> System.out.println("subscriber never called"),
                Throwable::printStackTrace,
                () -> System.out.println("subscriber never called")
        );

        //"error" factory emits error to it's observer
        Observable.error(new Exception("CustomException")).subscribe(
                (e) -> System.out.println("onNext is never called"),
                Throwable::printStackTrace,
                () -> System.out.println("OnComplete is never called because of exception")
        );
    }
}
