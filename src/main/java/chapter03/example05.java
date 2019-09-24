package chapter03;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

public class example05 {
    public static void main(String[] args) {
        Observable<String> colors = Observable.just("black", "green", "yellow", "brown", "purple", "blue", "gray", "coral", "corn");
        Observable<GroupedObservable<Character, String>> groups = colors.groupBy(s -> s.charAt(0));
        groups.flatMapSingle(g -> g.toList()).subscribe(System.out::println);
        //output:
        //[purple]
        //[black, brown, blue]
        //[coral, corn]
        //[green, gray]
        //[yellow]

        groups.flatMapSingle( g -> g.reduce("", (x,y) -> x.equals("") ? y : x+","+y).map(s -> g.getKey()+":"+s))
                .subscribe(System.out::println);
        //output
        //p:purple
        //b:black,brown,blue
        //c:coral,corn
        //g:green,gray
        //y:yellow
    }
}
