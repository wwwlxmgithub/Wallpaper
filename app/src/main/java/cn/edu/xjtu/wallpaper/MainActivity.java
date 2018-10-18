package cn.edu.xjtu.wallpaper;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CAMERA = 100;
    private Context context;
    static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSelfPermission();
            }
        });

        findViewById(R.id.viewPagerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewPagerActivity.class));
            }
        });
        findViewById(R.id.circleTextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CircleTextActivity.class));
            }
        });
        findViewById(R.id.stackViewButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StackViewActivity.class));
            }
        });
    }


    private void checkSelfPermission() {
        // 取得拍照权限
        if (ContextCompat.checkSelfPermission(context, PERMISSION_CAMERA) != PackageManager.PERMISSION_GRANTED)  {
            ActivityCompat.requestPermissions(this, new String[]{PERMISSION_CAMERA}, PERMISSION_REQUEST_CAMERA);
        } else {
            startWallpaper();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CAMERA:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startWallpaper();
                } else {
                    Toast.makeText(context, "没拍照权限！", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void startWallpaper() {
        final Intent pickWallpaper = new Intent(Intent.ACTION_SET_WALLPAPER);
        Intent chooser = Intent.createChooser(pickWallpaper, "选择壁纸");
        startActivity(chooser);
    }
}

































