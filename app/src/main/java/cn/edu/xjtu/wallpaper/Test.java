package cn.edu.xjtu.wallpaper;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class Test {
    @Override
    public String toString() {
        return super.toString();
    }

    public static DisplayMetrics getDis(Context context){
        DisplayMetrics metrics = new DisplayMetrics();
        context.getApplicationContext().getDisplay().getMetrics(metrics);
        System.out.println("1: " + metrics);
        WindowManager manager = (WindowManager) context.getSystemService(Service.WINDOW_SERVICE);
        if (manager != null) {
            manager.getDefaultDisplay().getMetrics(metrics);
        }
        System.out.println("2: " + metrics);

       DisplayMetrics metrics1 = context.getResources().getDisplayMetrics();
      context.getDisplay().getMetrics(metrics);
        System.out.println("4: " + metrics);

        float density = metrics.density;
        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;

        float scaledDensity = metrics.scaledDensity;
        float xdpi = metrics.xdpi;
        float ydpi = metrics.ydpi;
        float desityDpi = metrics.densityDpi;
        System.out.println("scaled density = " + desityDpi);
        return null;
    }
}
