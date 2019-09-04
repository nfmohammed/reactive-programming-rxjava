package chapter01;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class example09SingleObservable {
    public static void main(String[] args) {
        //Creating SingleObservable
        //"first" produces SingleObservable, hence only 1 is passed to it's subscriber
        Observable.just("1", "2", "3").first("").subscribe(System.out::println);

        //Creating SingleObserver
        //Single Observer only listen to a single emission, hence there is no onNext method
        SingleObserver<String> singleObserver = new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) { }

            @Override
            public void onSuccess(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable e) { }
        };
        //Observable has to be singleObservable to apply singleObserver
        Observable.just("4", "5", "6").first("").subscribe(singleObserver);


    }
}
