package io.github.byference.reactor

import java.util.concurrent.Flow

/**
 * FluxArray
 *
 * @author byference
 * @since 2020-08-02
 */
class FluxArray<T> extends Flux<T> {

    private T[] array

    FluxArray(T[] array) {
        this.array = Objects.requireNonNull(array, "array")
    }

    @Override
    void subscribe(Flow.Subscriber actual) {

        actual.onSubscribe(new ArraySubscription<>(actual, array))
    }

    static class ArraySubscription<T> implements Flow.Subscription {

        private volatile boolean cancelled

        private volatile int index

        private final Flow.Subscriber<? super T> actual

        private final T[] array

        ArraySubscription(Flow.Subscriber<? super T> actual, T[] array) {
            this.actual = actual
            this.array = array
        }

        @Override
        void request(long n) {
            if (cancelled) {
                return
            }

            long length = array.length
            for (int i = 0; i < n && index < length; i++) {
                actual.onNext(array[index++])
            }
            if (index == length) {
                actual.onComplete()
            }
        }

        @Override
        void cancel() {
            cancelled = true
        }
    }
}
