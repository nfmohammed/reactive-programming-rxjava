package chapter01;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class example12Disposable {
    public static void main(String[] args) throws InterruptedException {
        //"interval" emits numbers every 1 seconds starting from 0
        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);
        //"subscribe" returns disposable object
        Disposable disposable = source.subscribe(System.out::println);
        //while main thread is asleep, the observer should be printing numbers every 1 second.
        Thread.sleep(5000);
        //getting rid of the observable hence the observer no longer prints
        disposable.dispose();
        //main thread should close after 5 seconds
        Thread.sleep(5000);
        System.out.println("done");
    }
}
