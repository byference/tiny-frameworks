package io.github.byference.reactor

import java.util.concurrent.Flow
import java.util.function.Predicate

/**
 * FilterSubscriber
 *
 * @author byference
 * @since 2020-08-16
 */
class FilterSubscriber<T> implements Flow.Subscriber<T>, Flow.Subscription {

    private Flow.Subscriber<? super T> actual
    private Predicate<T> predicate
    private Flow.Subscription subscription
    private volatile boolean done

    FilterSubscriber(Flow.Subscriber<? super T> actual, Predicate<T> predicate) {
        this.actual = actual
        this.predicate = predicate
    }

    @Override
    void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription
        this.actual.onSubscribe(this)
    }

    @Override
    void onNext(T item) {
        if (done || !predicate.test(item)) {
            return
        }
        actual.onNext(item)
    }

    @Override
    void onError(Throwable throwable) {
        if (done) {
            return
        }
        done = true
        actual.onError(throwable)
    }

    @Override
    void onComplete() {
        if (done) {
            return
        }
        done = true
        actual.onComplete()
    }

    @Override
    void request(long n) {
        subscription.request(n)
    }

    @Override
    void cancel() {
        subscription.cancel()
    }
}
