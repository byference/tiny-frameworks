package io.github.byference.reactor

import java.util.concurrent.Flow
import java.util.function.Predicate

/**
 * FluxFilter
 *
 * @author byference
 * @since 2020-08-16
 */
class FluxFilter<T> extends Flux<T> {

    private Flow.Publisher<T> actual
    private Predicate<T> predicate

    FluxFilter(Flow.Publisher<T> actual, Predicate<T> predicate) {
        this.actual = actual
        this.predicate = predicate
    }

    @Override
    void subscribe(Flow.Subscriber<? super T> subscriber) {
        actual.subscribe(new FilterSubscriber<T>(subscriber, predicate))
    }

}
