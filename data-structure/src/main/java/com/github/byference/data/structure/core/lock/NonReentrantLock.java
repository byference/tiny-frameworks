package com.github.byference.data.structure.core.lock;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Non ReentrantLock
 *
 * @author byference
 * @since 2019-09-01
 */
public class NonReentrantLock implements Lock, Serializable {

    private static final long serialVersionUID = 3335035269038672470L;

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    private static class Sync extends AbstractQueuedSynchronizer {

        private static final long serialVersionUID = 3006495456362565093L;

        /**
         * 锁是否已被持有
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        /**
         * 尝试获取锁
         */
        @Override
        protected boolean tryAcquire(int arg) {

            if (arg != 1) {
                throw new IllegalArgumentException("expect 1 but found " + arg);
            }

            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 尝试释放锁
         */
        @Override
        protected boolean tryRelease(int arg) {

            if (arg != 1) {
                throw new IllegalArgumentException("expect 1 but found " + arg);
            }

            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }

            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /**
         * 条件变量
         */
        Condition newCondition() {
            return new ConditionObject();
        }

    }

}
