package sm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.function.Predicate;

public class RecordSubscriber implements Flow.Subscriber<Object> {
    private final Predicate<Object> predicate;
    private Flow.Subscription subscription;
    private List<Object> list = new ArrayList<>();

    public RecordSubscriber(Predicate<Object> predicate) {
        this.predicate = predicate;
    }

    public List<Object> getList() {
        return list;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Object item) {
        if(predicate.test(item)) {
            list.add(item);
        }
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        subscription.cancel();
    }
}