package chapter01;

import io.reactivex.Observable;

public class example08DeferObservable {
    static int a = 0, b = 5;
    static int c = 0, d = 5;
    public static void main(String[] args) {

        //Observable is created with start = 0 and end = 10
        Observable<Integer> source1 = Observable.range(a, b);
        source1.subscribe(e -> System.out.print(e + " ")); //01234
        System.out.println();

        b = 10; // change in static number does not have any impact on already created observable

        //since souce1 observable was crated with [0, 5]
        source1.subscribe(e -> System.out.print(e + " ")); //01234
        System.out.println();

        //"defer" factory helps to solve above problem

        Observable<Integer> source2 = Observable.defer(() -> Observable.range(c, d));
        //New Observable created (1st) upon subscribe
        source2.subscribe(e -> System.out.print(e + " ")); //01234
        System.out.println();

        d = 10; //change static value of d
        //New Observable created (2nd) upon subscribe
        source2.subscribe(e -> System.out.print(e + " ")); //0123456789
        System.out.println();
    }
}
