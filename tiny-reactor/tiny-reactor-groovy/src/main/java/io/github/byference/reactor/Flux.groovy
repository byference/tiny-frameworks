package io.github.byference.reactor

import java.util.concurrent.Flow
import java.util.function.Function
import java.util.function.Predicate

/**
 * Flux
 *
 * @author byference
 * @since 2020-08-02
 */
abstract class Flux<T> implements Flow.Publisher<T> {

    static <T> Flux<T> just(T... array) {
        return new FluxArray<T>(array)
    }

    Flux map(Function mapper) {
        return new FluxMap(this, mapper)
    }

    Flux<T> filter(Predicate<T> predicate) {
        return new FluxFilter(this, predicate)
    }
}
