package chapter01;

import io.reactivex.Observable;

public class example04ColdObservables {

    public static void main(String[] args) {

        // "just" factory return cold observables
        Observable<String> dataSource = Observable.just("first", "second", "third");

        //since dataSource is cold observable, all events are first passed to Observer1 and then to Observer2
        dataSource.subscribe(e -> System.out.println("observer1 - " + e), Throwable::printStackTrace, () -> System.out.println("observer1 complete"));
        dataSource.subscribe(e -> System.out.println("observer2 = " + e), Throwable::printStackTrace, () -> System.out.println("observer2 complete"));

    }
}
