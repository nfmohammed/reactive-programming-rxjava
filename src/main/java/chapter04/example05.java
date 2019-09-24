package chapter04;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class example05 {
    public static void main(String[] args) throws InterruptedException {
        Observable<Long> source =Observable.interval(1, TimeUnit.SECONDS)
                .replay(3)// hold on the last three emissions
                .autoConnect(); // new observer will get all emissions from the last 4 element to the live ones
        source.subscribe(e -> System.out.println("Observer 1: " + e));
        Thread.sleep(5000);
        source.subscribe(e -> System.out.println("Observer 2: " + e));
        Thread.sleep(5000);
        //output:
        //Observer 1: 0
        //Observer 1: 1
        //Observer 1: 2
        //Observer 1: 3
        //Observer 1: 4
        //Observer 2: 2 (replaying value to new subscriber)
        //Observer 2: 3 (replaying value to new subscriber)
        //Observer 2: 4 (replaying value to new subscriber)
        //Observer 1: 5
        //Observer 2: 5
        //Observer 1: 6
        //Observer 2: 6
        //Observer 1: 7
        //Observer 2: 7
        //Observer 1: 8
        //Observer 2: 8
        //Observer 1: 9
        //Observer 2: 9
    }
}
