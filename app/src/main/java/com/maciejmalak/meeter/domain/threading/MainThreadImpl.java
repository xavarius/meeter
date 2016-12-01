package com.maciejmalak.meeter.domain.threading;

import android.os.Handler;
import android.os.Looper;
import com.maciejmalak.meeter.domain.MainThread;

public class MainThreadImpl implements MainThread {
  private static MainThread mMainThread;
  private Handler mHandler;

  @Override public void post(Runnable runnable) {
    mHandler.post(runnable);
  }

  private MainThreadImpl() {
    mHandler = new Handler(Looper.getMainLooper());
  }

  public static MainThread getInstance() {
    if(mMainThread == null) {
      mMainThread = new MainThreadImpl();
    }

    return mMainThread;
  }
}
