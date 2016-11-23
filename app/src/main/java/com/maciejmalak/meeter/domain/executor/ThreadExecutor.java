package com.maciejmalak.meeter.domain.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ThreadExecutor implements Executor {
  private static final int INITIAL_POOL_SIZE = 3;
  private static final int MAX_POOL_SIZE = 5;
  private static final int KEEP_ALIVE_TIME = 30;
  private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

  private final LinkedBlockingDeque<Runnable> mThreadQueue;
  private static ThreadPoolExecutor mThreadPool;

  @Inject
  public ThreadExecutor() {
    mThreadQueue = new LinkedBlockingDeque<>();
    mThreadPool = new ThreadPoolExecutor(INITIAL_POOL_SIZE, MAX_POOL_SIZE,
        KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, mThreadQueue);
  }

  @Override public void execute(Runnable runnable) {
    if (runnable == null) throw new IllegalArgumentException("Runnable canot be null");
    mThreadPool.execute(runnable);
  }
}
