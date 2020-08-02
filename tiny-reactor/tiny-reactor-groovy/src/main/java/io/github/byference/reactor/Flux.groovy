package io.github.byference.reactor

import java.util.concurrent.Flow

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
}
