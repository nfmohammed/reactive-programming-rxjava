package chapter02;

import io.reactivex.Observable;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class example01 {
    public static void main(String[] args) throws InterruptedException {

        Observable<String> source = Observable.just("alpha", "beta", "zeta", "omega");
        Observable<String> source2 = Observable.just("alpha", "alpha", "beta", "zeta", "omega");
        Observable<Integer> source3 = Observable.just(1,5,9,7,6,0);
        Observable<String> source4 = Observable.just("a", "b", "c");
        Observable<String> source5 = Observable.just("a", "b", "c", "ab");
        Observable<Integer> source6 = Observable.just(1, 8, 6, 7, 0, 9, 3);

        //Filter Operator
        System.out.println("Filter....");
        source.filter( e -> e.length() > 4).subscribe(System.out::println); //output: alpha omega

        //Take Operator
        System.out.println("Take.....");
        source.take(2).subscribe(System.out::println); //output: alpha beta
        //take first 2 emissions

        //Skip Operator
        System.out.println("Skip....");
        source.skip(2).subscribe(System.out::println); //output: zeta omega
        //skip first two emissions

        //First Operator
        System.out.println("First....");
        source.first("default").subscribe(System.out::println); //output: alpha

        //TakeWhile Operator
        System.out.println("TakeWhile....");
        source.takeWhile( e -> e.length() > 4).subscribe(System.out::println); //output: alpha
        //condition fails at "beta" so takeWhile will skip rest of the elements.

        //SkipWhile Operator
        System.out.println("SkipWhile...");
        source.skipWhile( e -> e.length() > 4).subscribe(System.out::println); //output: beta zeta omega
        //skipWhile skips all the values that satisfy the condition, once the condition is false at "beta", all the remaining values are emitted.

        //distinct operator
        System.out.println("Distinct.....");
        source2.distinct().subscribe(System.out::println); //output: alpha beta zeta omega

        //distinct with lambda
        System.out.println("Distince with lambda.....");
        source2.distinct(String::length).subscribe(System.out::println); //output: alpha beta
        //select strings with distinct lengths

        //elementAt operator
        System.out.println("ElementAt....");
        source2.elementAt(2).subscribe(System.out::println); //output: beta

        //map operator
        System.out.println("Map......");
        source2.map(e -> e.length()).subscribe(System.out::println);
        //map converts Observable<String> into Observable<Integer>

        //cast operator
        System.out.println("Cast....");
        source2.cast(Object.class).subscribe(System.out::println);

        //startWith operator
        System.out.println("StartWith....");
        source2.startWith("This is executed before emissions").subscribe(System.out::println);

        //defaultIfEmpty operator
        System.out.println("DefaultIfEmpty....");
        Observable.empty().defaultIfEmpty("default value").subscribe(System.out::println);
        //defaults to a value if observable is empty

        //switchIfEmpty operator
        System.out.println("SwitchIfEmpty....");
        Observable.empty().switchIfEmpty(source2).subscribe(System.out::println);
        //Switch observable source if original observable emits empty

        //sort operator
        System.out.println("Sort.....");
        source3.sorted().subscribe(System.out::println);

        //reverse sort
        System.out.println("ReverseSort...");
        source3.sorted(Comparator.reverseOrder()).subscribe(System.out::println);

        //repeat operator
        System.out.println("Repeat....");
        source3.repeat(2).subscribe(System.out::println);
        //values will be emitted twice

        //scan operator
        System.out.println("Scan.....");
        source3.scan( (total, next) -> total + next).subscribe(System.out::println);
        //this calculates the rolling sum of the numbers

        //reduce operator
        System.out.println("Reduce...");
        source4.reduce( (a,b) -> a + (b.equals("") ? "" : ","+b) ).subscribe(System.out::println);

        //all operator
        System.out.println("All....");
        source4.all( e -> e.length() == 1).subscribe(System.out::println); //output: true
        source4.all( e -> e.length() == 2).subscribe(System.out::println); //output: false

        //any operator
        System.out.println("Any....");
        source4.any(e -> e.length() == 2).subscribe(System.out::println); //output: false
        source5.any(e -> e.length() == 2).subscribe(System.out::println); //output: true

        //count operator
        System.out.println("Count....");
        source4.count().subscribe(System.out::println); //output: 3
        source5.count().subscribe(System.out::println); //output: 4

        //contains operator
        System.out.println("Contain....");
        source4.contains("a").subscribe(System.out::println); //output: true

        //toList operator
        System.out.println("ToList....");
        source4.toList().subscribe(System.out::println); //output: [a, b, c]

        //toSortedList operator
        System.out.println("ToSortedList....");
        source5.toSortedList().subscribe(System.out::println); //output: [a, ab, b, c]

        //toMap operator
        System.out.println("ToMap....");
        source5.toMap(String::length).subscribe(System.out::println); //output: {1 = c, 2 = ab }

        //toMultiMap operator
        System.out.println("ToMultiMap....");
        source5.toMultimap(String::length).subscribe(System.out::println); //output: {1 = [a, b, c], 2 = ab }
        source5.toMultimap(e -> e.charAt(0), String::length).subscribe(System.out::println); //output: {a=[1, 2], b=[1], c=[1]}
        //source5.toMultimap(e -> e.charAt(0), String::length, HashMap::new).subscribe(System.out::println);

        //collect operator
        System.out.println("Collect....");
        source5.collect(HashSet::new, HashSet::add).subscribe(System.out::println); //output: [a, ab, b, c]

        //error operator
        System.out.println("Error.....");
        source6.map(e -> 5/e).onErrorReturnItem(-1).subscribe(System.out::println);//output: 5 0 0 0 -1
        //map throws error when we try to divide by 0, hence -1 is returned
        source6.map(e -> 5/e).onErrorResumeNext(Observable.just(5, 6, 7)).subscribe(System.out::println);//output: 5 0 0 0 5 6 7

        //retry operator
        System.out.println("Retry....");
        source6.map(e -> 5/e).retry(2).onErrorReturnItem(-1).subscribe(System.out::println);
        //output: 5 0 0 0 5 0 0 0 5 0 0 0 -1

        //action operators
        System.out.println("Action....");
        source6.doOnNext(e -> System.out.println("now serving element: " + e))
                .doOnComplete(() -> System.out.println("You are almost done"))
                .doOnError(e -> System.out.println("Error due to element: " + e))
                .map( e -> 5/e)
                .onErrorReturnItem(-1)
                .subscribe(System.out::println);


        //delay operator
        System.out.println("Delay....");
        System.out.println("Now waiting for 5 seconds.....");
        source3.delay(5, TimeUnit.SECONDS).subscribe(System.out::println);
        Thread.sleep(10000);

    }
}
