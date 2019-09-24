package chapter03;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class example03 {
    public static void main(String[] args) throws InterruptedException {

        Observable<Long> sourceA = Observable.interval(1, TimeUnit.SECONDS);
        Observable<Long> sourceB = Observable.interval(200, TimeUnit.MILLISECONDS);

        //combineLatest operator
        //this operator stores the value from slow-observable while fast-observables are executed.
        Observable.combineLatest(sourceA, sourceB, (e1, e2) -> "sourceA: "+e1+"; "+"sourceB: "+e2).subscribe(System.out::println);//output: sourceB emissions are printed
        Thread.sleep(5000);
    }
}
