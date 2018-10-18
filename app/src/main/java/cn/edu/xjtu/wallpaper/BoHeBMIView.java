package cn.edu.xjtu.wallpaper;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class BoHeBMIView extends View {
    private String title;
    private int minVal, minNormal, maxNormal, maxVal;
    private float currVal;
    private int leftColor, midColor, rightColor;
    private int upDrawable = R.drawable.ic_up, downDrawable = R.drawable.ic_down;

    private Paint paint;
    private Path path;
    private int width, height;
    public BoHeBMIView(Context context) {
        super(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    public BoHeBMIView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
