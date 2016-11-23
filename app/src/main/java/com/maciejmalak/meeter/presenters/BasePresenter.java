package com.maciejmalak.meeter.presenters;

import android.content.Intent;
import org.jetbrains.annotations.NotNull;

public interface BasePresenter {
   void onCreated();
   void onStarted();
   void onStopped();
   void onActivityResulted(int requestCode, boolean success, @NotNull Intent data);
}
