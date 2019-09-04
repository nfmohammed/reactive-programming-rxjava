package chapter01;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class example01 {

    public static void main(String[] args) {

        // This data source is emitting 3 strings
        Observable<String> dataSource = Observable.create(emitter -> {
            emitter.onNext("first");
            emitter.onNext("second");
            emitter.onNext("third");
            emitter.onComplete();
        });

        Observer<String> dataReceiver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                //TODO: we will look at this later
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
                System.out.println("the printing operation is complete");
            }
        };
        dataSource.subscribe(dataReceiver);
    }
}
