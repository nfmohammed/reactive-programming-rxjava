package chapter04;

import io.reactivex.Observable;

public class example01 {
    public static void main(String[] args) {
        //If we know that there will be 2 subscribers
        Observable<Integer> source = Observable.range(1, 3).map(i -> i+5).publish().autoConnect(2);

        source.subscribe(e -> System.out.println("Observer 1: " + e));
        source.subscribe(e -> System.out.println("Observer 2: " + e));
        //output:
        //Observer 1: 6
        //Observer 2: 6
        //Observer 1: 7
        //Observer 2: 7
        //Observer 1: 8
        //Observer 2: 8
    }
}
