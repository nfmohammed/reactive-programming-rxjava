package chapter04;

public class example06 {
    public static void main(String[] args) {
//        when you use replay, it will hold emissions until the new observer gets them and then it will dispose of itself. this is good for memory management, but if you want to hold all emissions indefinitly, then you can use cache() operator which does the same thing as replay() but persists on emissions.
//
//        Observable<Long> seconds =
//
//                Observable.interval(1, TimeUnit.SECONDS)
//                        .cashe()// hold on all emissions indefinitly
//                        .autoConnect();
//        If you have an infinite observable and you want to cache only a specific number of emissions, you can use:
//        cacheWithInitialCapacity(numberofemissions) operator
//        Try to not use cache with inifinite Observable because will will run into StackOverFlow Error

    }
}
