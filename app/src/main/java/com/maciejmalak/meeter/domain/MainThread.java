package com.maciejmalak.meeter.domain;

/*TODO Move entire DOMAIN package as a standalone java module*/
public interface MainThread {
  void post(Runnable runnable);
}
