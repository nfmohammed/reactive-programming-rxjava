package chapter01;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;

public class example10MaybeObservable {
    public static void main(String[] args) {
        MaybeObserver<String> s = new MaybeObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(String s) {
                System.out.println("If observable emits single event, then onSuccess is invoked, then onComplete is invoked");
                System.out.println("If observable emits empty event, then onSuccess is not invoked.");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("If observable emits empty event, then onComplete is last invocation");
                System.out.println("If observable emits single event, even then onComplete is the only invocation");
            }
        };
    }
}
