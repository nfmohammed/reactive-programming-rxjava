#### Reactive Programming RxJava

- Reactive programming has `Push` approach compared to traditional `Pull` approach in Java.

- Earlier, application would worry about pulling the data from database/service but with reactive programming the observables allows us to push the data as notification.

- Reactive programming is event driven development. The application will update it's state using the event provided by the user.

- Scalability

- Fault Tolerant

- Responsive

- Observable

#### Observable interface

- OnNext: passdown emissions into the observer instance

- OnComplete: called when the Observable emissions are finished

- OnError: emitted when an error occurs in the OnNext method. Error is communicated to the Observer instance which typically is goind to handle it.