package io.github.byference.reactor

import java.util.concurrent.Flow
import java.util.function.Function

/**
 * FluxMap
 *
 * @author byference
 * @since 2020-08-15
 */
class FluxMap<T, R> extends Flux<R> {

    private Flux<? extends T> source
    private Function<T, R> mapper

    FluxMap(Flux<? extends T> source, Function<T, R> mapper) {
        this.source = source
        this.mapper = mapper
    }

    @Override
    void subscribe(Flow.Subscriber<? super R> actual) {
        source.subscribe(new MapSubscriber(actual, mapper))
    }

}
