package cn.edu.xjtu.wallpaper;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class app extends Application {
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // 收到广播后做一些处理，比如销毁activity
            ((Activity)context).finish();
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                System.out.println(activity.getComponentName().getClassName() + "app.onActivityCreated");
                // 在这里判断是不是目标activity，如果是目标activity的话，可以给activity注册一个广播接收器，在收到广播后finish掉自己
                if (activity.getComponentName().equals("")) {
                    IntentFilter filter = new IntentFilter();
                    filter.addAction("custom action");
                    activity.registerReceiver(receiver, filter);
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                System.out.println(activity.getComponentName().getClassName() + "app.onActivityDestroyed");
                // 在这里判断是不是目标activity，如果是目标activity的话，activity解注册广播接收器
                if (activity.getComponentName().equals("myActivity")) {
                    activity.unregisterReceiver(receiver);
                }
            }
        });
    }
}
