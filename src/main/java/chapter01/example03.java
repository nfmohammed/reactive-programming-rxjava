package chapter01;

import io.reactivex.Observable;

public class example03 {
    public static void main(String[] args) {
        Observable<String> dataSource = Observable.just("first", "second", "third");
        //instead of implementing override methods, we could use this shortcut to do onNext, onError and onComplete functions
        dataSource.subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("printing complete"));
    }
}
