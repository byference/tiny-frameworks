package io.github.byference.reactor

import java.util.concurrent.Flow
import java.util.function.Function

/**
 * MapSubscriber
 *
 * @author byference
 * @since 2020-08-15
 */
class MapSubscriber<T, R> implements Flow.Subscriber<T>, Flow.Subscription {

    private final Flow.Subscriber<? super R> actual
    private final Function<? super T, ? extends R> mapper
    Flow.Subscription subscriptionOfUpstream
    volatile boolean done

    MapSubscriber(Flow.Subscriber<? super R> actual, Function<? super T, ? extends R> mapper) {
        this.actual = actual
        this.mapper = mapper
    }

    @Override
    void onSubscribe(Flow.Subscription subscription) {
        this.subscriptionOfUpstream = subscription
        this.actual.onSubscribe(this)
    }

    @Override
    void onNext(T item) {
        if (done) {
            return
        }
        actual.onNext(mapper.apply(item))
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
        this.subscriptionOfUpstream.request(n)
    }

    @Override
    void cancel() {
        this.subscriptionOfUpstream.cancel()
    }
}
