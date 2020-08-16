package io.github.byference.reactor

import org.junit.jupiter.api.Test
import java.util.concurrent.Flow

/**
 * FluxArray
 *
 * @author byference
 * @since 2020-08-02
 */
class FluxTest {

    @Test
    void fluxArrayTest() {

        Flux.just(1, 2, 3, 4, 5, 6)
                .filter(i -> i > 4)
                .map(i -> i * i)
                .subscribe(new Flow.Subscriber<Integer>() {
                    @Override
                    void onSubscribe(Flow.Subscription subscription) {
                        println ">> onSubscribe ..."
                        subscription.request(6)
                    }

                    @Override
                    void onNext(Integer item) {
                        println "onNext: $item"
                    }

                    @Override
                    void onError(Throwable throwable) {
                        println "onError: $throwable"
                    }

                    @Override
                    void onComplete() {
                        println ">> onComplete..."
                    }
                })
    }
}
