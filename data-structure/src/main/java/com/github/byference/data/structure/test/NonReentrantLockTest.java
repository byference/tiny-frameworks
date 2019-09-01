package com.github.byference.data.structure.test;

import com.github.byference.data.structure.core.Action;
import com.github.byference.data.structure.core.lock.NonReentrantLock;
import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;

/**
 * {@link NonReentrantLock} Test
 *
 * @author byference
 * @since 2019-09-01
 */
public class NonReentrantLockTest {

    private final static NonReentrantLock lock = new NonReentrantLock();
    private final static Condition notFull = lock.newCondition();
    private final static Condition notEmpty = lock.newCondition();

    private final static Queue<String> queue = new LinkedBlockingQueue<>();
    private final static int queueSize = 10;

    private final static ExecutorService service = Executors.newFixedThreadPool(2);

    /**
     * not use int because of variables used in lambda should be final or effectively final
     */
    final static AtomicInteger atomicInteger = new AtomicInteger(1);


    @Test
    public void testNonReentrantLock() {

        for (int i = 0; i < 20; i++) {

            // 生产线程
            execute(() -> {

                // 如果队列满了则等待
                if (queue.size() > queueSize) {
                    notEmpty.await();
                }

                // 往队列中添加元素
                int element = atomicInteger.getAndIncrement();
                System.out.printf("Thread [ %s ] - add: %s\n", Thread.currentThread().getName(), element);
                queue.add("element: " + element);

                // 唤醒等待的消费线程
                notFull.signalAll();
            });


            // 消费线程
            execute(() -> {

                // 如果队列为空则等待
                if (queue.size() < 1) {
                    notFull.await();
                }

                // 消费队列中的线程
                String message = queue.poll();
                System.out.printf("Thread [ %s ] - poll: %s\n", Thread.currentThread().getName(), message);

                // 唤醒等待的生产线程
                notEmpty.signalAll();

            });
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        service.shutdown();
    }


    private void execute(Action action) {

        service.execute(() -> {

            lock.lock();
            try {
                action.execute();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
    }

}
