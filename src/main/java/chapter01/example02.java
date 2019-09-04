package chapter01;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class example02 {
    public static void main(String[] args) {
        //Instead of using emitter lambda in chapter01.example01, this example uses "just" factory
        Observable<String> dataSource = Observable.just("first", "second", "third");
        Observer<String> dataReceiver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("the printing is complete");
            }
        };
        dataSource.subscribe(dataReceiver);
    }
}
