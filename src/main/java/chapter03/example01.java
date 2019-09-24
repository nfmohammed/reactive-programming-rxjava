package chapter03;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class example01 {
    public static void main(String[] args) throws InterruptedException {
        Observable<String> source1 = Observable.just("a", "b");
        Observable<String> source2 = Observable.just("c", "d");
        Observable<String> source3 = Observable.just("e", "f");
        Observable<String> source4 = Observable.just("g", "h");

        //merge supports 4 inputs
        //merge does not guarantee order of items. If order is required, see concat operator
        Observable.merge(source1, source2, source3, source4).subscribe(System.out::println);//output: a b c d e f g h

        //merging 2 observables
        source1.mergeWith(source2).subscribe(System.out::println);//output: a b c d

        //flatmap operator
        //Each character is converted into observable. Order is not guaranteed.
        Observable<String> source5 = Observable.just("Alpha", "Beta", "Gamma");
        source5.flatMap( e -> Observable.fromArray(e.split(""))).subscribe(System.out::println);
        //output: A l p h a B e t a G a m m a

        //concat operator
        //observables merged using concat operator will maintain their order
        Observable.concat(source1, source2, source3, source4).subscribe(System.out::println);//output: a b c d e f g h
        source1.concatWith(source2).subscribe(System.out::println);

        //concatMap operator
        source5.concatMap(e -> Observable.fromArray(e.split(""))).subscribe(System.out::println);
        //output: A l p h a B e t a G a m m a

        Observable<String> source6 = Observable.just("Alpha", "Beta", "Gamma", "Delta");
        Observable<String> source7 = Observable.just("Apple", "Ball");

        //zip operator
        //zip takes emissions from each observable and zip them in the format as described in zipper function
        Observable.zip(source6, source7, (e1, e2) -> e1 + " - " + e2).subscribe(System.out::println);
        //output: Alpha - Apple;  Beta - Ball
        //Gamma and Delta are ignored.
    }
}
