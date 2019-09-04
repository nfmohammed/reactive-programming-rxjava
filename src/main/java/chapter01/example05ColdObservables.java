package chapter01;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class example05ColdObservables {
    public static void main(String[] args) {
        //Code observables can be created from arraylist
        List<String> numberList = Arrays.asList("1", "2", "3", "4", "5");

        Observable<String> dataSource = Observable.fromIterable(numberList);

        //since dataSource is cold observable, all events are first passed to Observer1 and then to Observer2
        dataSource.subscribe(e -> System.out.println("observer1 - " + e), Throwable::printStackTrace, () -> System.out.println("observer1 complete"));
        dataSource.subscribe(e -> System.out.println("observer2 = " + e), Throwable::printStackTrace, () -> System.out.println("observer2 complete"));

    }
}
