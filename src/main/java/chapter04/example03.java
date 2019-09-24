package chapter04;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class example03 {
    public static void main(String[] args) throws InterruptedException {
        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS).publish().refCount();
        source.take(3).subscribe( e -> System.out.println("Observer 1: " + e));
        Thread.sleep(3000);
        source.subscribe(e -> System.out.println("Observer 2: " + e));
        Thread.sleep(5000);
        //output:
        //Observer 1: 0
        //Observer 1: 1
        //Observer 1: 2
        //Observer 2: 0
        //Observer 2: 1
        //Observer 2: 2
        //Observer 2: 3
        //Observer 2: 4
    }
}
