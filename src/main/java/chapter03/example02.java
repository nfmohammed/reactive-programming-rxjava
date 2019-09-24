package chapter03;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class example02 {
    public static void main(String[] args) throws InterruptedException {

        Observable<Long> sourceA = Observable.interval(1, TimeUnit.SECONDS);
        Observable<Long> sourceB = Observable.interval(200, TimeUnit.MILLISECONDS);

        //sourceB is faster than sourceA, so ambArray picks the fastest observable.
        Observable.ambArray(sourceA, sourceB).subscribe(System.out::println);//output: sourceB emissions are printed
        Thread.sleep(5000);
    }
}
