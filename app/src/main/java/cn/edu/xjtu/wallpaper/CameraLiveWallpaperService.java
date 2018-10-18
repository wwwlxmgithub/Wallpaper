
package cn.edu.xjtu.wallpaper;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.hardware.Camera;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class CameraLiveWallpaperService extends WallpaperService {
    // 实现WallpaperService必须实现的效果
    @Override
    public Engine onCreateEngine() {
        // 返回自定义的CameraEngine
        return new CameraEngine();
    }
    class CameraEngine extends Engine implements Camera.PreviewCallback {

        private Camera camera;
        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);

            startPreview();
            // 设置处理触摸事件
            setTouchEventsEnabled(true);
        }

        @Override
        public void onTouchEvent(MotionEvent event) {
            super.onTouchEvent(event);
            // 事件处理：点击拍照，长按拍照
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            stopPreview();
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            if (visible) {
                startPreview();
            } else {
                stopPreview();
            }
        }

        private void stopPreview() {
            if (camera != null) {
                try{
                    camera.stopPreview();
                    camera.setPreviewCallback(null);
                    camera.release();
                }catch (Exception e) {
                    e.printStackTrace();
                }
                camera = null;
            }
        }

        private void startPreview() {
            camera = Camera.open();
            camera.setDisplayOrientation(90);

            try {
                camera.setPreviewDisplay(getSurfaceHolder());
            }catch (Exception e) {
                e.printStackTrace();
            }
            camera.startPreview();
        }

        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {
            camera.addCallbackBuffer(data);
        }
    }
}


















































