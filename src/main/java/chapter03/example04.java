package chapter03;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class example04 {
    public static void main(String[] args) throws InterruptedException {

        Observable<Long> source1 = Observable.interval(1, TimeUnit.SECONDS);
        Observable<Long> source2 = Observable.interval(200, TimeUnit.MILLISECONDS);

        //withLatestFrom operator
        //emissions are taken from each source and the values do not duplicate(or stored) as seen in previous example
        source1.withLatestFrom(source2, (e1, e2) -> e1+" - " + e2).subscribe(System.out::println);
        //output
        //0 - 4
        //1 - 8
        //2 - 13
        //3 - 18
        //4 - 23

        Thread.sleep(5000);
    }
}
