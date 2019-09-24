package chapter04;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class example02 {
    public static void main(String[] args) throws InterruptedException {

        //With autoConnect, when second observer subscribes at a later time, it won't receive emissions from the beginning.
        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS).publish().autoConnect();
        source.subscribe(e -> System.out.println("Observer 1: " + e));
        Thread.sleep(3000);
        source.subscribe(e -> System.out.println("Observer 2: " + e));
        Thread.sleep(3000);
        //output:
        //Observer 1: 0
        //Observer 1: 1
        //Observer 1: 2
        //Observer 1: 3
        //Observer 2: 3
        //Observer 1: 4
        //Observer 2: 4
        //Observer 1: 5
        //Observer 2: 5
    }
}
