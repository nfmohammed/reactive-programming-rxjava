package chapter01;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class example13CompositeDisposable {
    public static final CompositeDisposable disposables = new CompositeDisposable();

    public static void main(String[] args) throws InterruptedException {
        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);
        Disposable disposable1 = source.subscribe(number -> System.out.println("Observer 1: " + number));
        Disposable disposable2 = source.subscribe(number -> System.out.println("Observer 2: " + number));
        Thread.sleep(5000); //you should see outputs from two observers.
        disposables.addAll(disposable1, disposable2);
        disposables.dispose(); //the two observers will stop printing here
        Thread.sleep(5000); //the application waits for 5  seconds without printing anything.
        System.out.println("done");
    }
}
