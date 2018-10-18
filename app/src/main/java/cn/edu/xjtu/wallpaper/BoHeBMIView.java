package cn.edu.xjtu.wallpaper;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class BoHeBMIView extends View {
    private String title, normalText;
    private int minVal, minNormal, maxNormal, maxVal;
    private float currVal;
    private int leftColor, midColor, rightColor;
    private int upDrawable = R.drawable.ic_up, downDrawable = R.drawable.ic_down;

    private float titleSize, textSize;
    private Paint paint;
    private Path path;
    private int width, height;
    public BoHeBMIView(Context context) {
        super(context);
    }

    public BoHeBMIView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        title = "";
        minVal = 0;
        minNormal = 316;
        maxNormal = 354;
        maxVal = 400;
        leftColor = R.color.yellow;
        midColor = R.color.green;
        rightColor = R.color.gray;
        currVal = 357;
        titleSize = 44;
        textSize = 44;

        normalText = "正常";

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }
}
