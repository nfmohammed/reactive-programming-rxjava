package chapter01;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.Arrays;
import java.util.List;

public class example06HotObservables {
    public static void main(String[] args) {

        List<String> numberList = Arrays.asList("1", "2", "3", "4", "5");

        //This is cold observable
        Observable<String> coldSource = Observable.fromIterable(numberList);

        //Converting cold observable to hot observable
        ConnectableObservable<String> hotSource = coldSource.publish();


        //since dataSource is cold observable, all events are first passed to Observer1 and then to Observer2
        System.out.println("-----Cold Observable below----------");
        coldSource.subscribe(e -> System.out.println("observerA - " + e), Throwable::printStackTrace, () -> System.out.println("observerA complete"));
        coldSource.subscribe(e -> System.out.println("observerB = " + e), Throwable::printStackTrace, () -> System.out.println("observerB complete"));

        System.out.println("------Hot Observable below-----------");
        hotSource.subscribe(e -> System.out.println("observerC - " + e), Throwable::printStackTrace, () -> System.out.println("observerC complete"));
        hotSource.subscribe(e -> System.out.println("observerD - " + e), Throwable::printStackTrace, () -> System.out.println("observerD complete"));
        hotSource.connect(); //connect nees to be called on hot observable for it to work
    }
}
