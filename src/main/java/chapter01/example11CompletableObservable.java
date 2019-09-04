package chapter01;

import io.reactivex.Completable;

public class example11CompletableObservable {
    public static void main(String[] args) {
        //This is probably used for unit testing. No usage in main code.
        //Execute the function defined inside fromRunnable and then execute observer's onComplete
        Completable.fromRunnable(() -> System.out.println("calling on complete"))
        .subscribe( () -> System.out.println("done"));
    }
}
