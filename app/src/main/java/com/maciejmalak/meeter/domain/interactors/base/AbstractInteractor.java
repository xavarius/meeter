package com.maciejmalak.meeter.domain.interactors.base;

import com.maciejmalak.meeter.domain.MainThread;
import com.maciejmalak.meeter.domain.executor.ThreadExecutor;
import javax.inject.Inject;

/* TODO How to inject Executor with Dagger2? */
/*TODO Do we need here explicity implementation of Runnable */
public abstract class AbstractInteractor implements Interactor {
  @Inject protected MainThread mMainThread;
  @Inject ThreadExecutor mThreadExecutor;

  private volatile boolean mIsCanceled;
  private volatile boolean mIsRunning;

  /**
   * This method implementation will contain all business logic.
   * Do not call it directly - call execute() to being sure that
   * interactor is done at background thread.
   */
  @Override public abstract void run();

  public boolean isRunning() { return mIsRunning; }

  public void cancel() {
    mIsRunning = false;
    mIsCanceled = true;
  }

  public void onFinished() {
    mIsRunning = false;
    mIsCanceled = false;
  }

  @Override public void execute() {
    mIsRunning = true;
    mThreadExecutor.execute(this);
  }
}
