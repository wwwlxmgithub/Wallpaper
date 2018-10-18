package cn.edu.xjtu.wallpaper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Locale;

public class BoHeBMIView extends View {
    private String title, normalText;
    private int minVal, minNormal, maxNormal, maxVal;
    private float currVal;
    private int leftColor, midColor, rightColor, textColor;
    private int upDrawable = R.drawable.ic_up, downDrawable = R.drawable.ic_down;

    private float titleSize, textSize;
    private Paint textPaint, linePaint;
    private Bitmap bitmapUp, bitmapDown;
    private int width, height, stokeWidth, smallCircleRadius, circleStokeWidth;
    public BoHeBMIView(Context context) {
        super(context);
        init();
    }

    public BoHeBMIView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BoHeBMIView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BoHeBMIView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        System.out.println("BoHeBMIView.init");
        title = "BMI";
        minVal = 0;
        minNormal = 316;
        maxNormal = 354;
        maxVal = 400;
        leftColor = getResources().getColor(R.color.yellow);
        midColor = getResources().getColor(R.color.green);
        rightColor = getResources().getColor(R.color.gray);

        textColor = Color.GRAY;
        currVal = 200;
        titleSize = 50;
        textSize = 44;
        stokeWidth = 10;
        smallCircleRadius = 20;
        circleStokeWidth = 10;

        normalText = "正常";

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.GRAY);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(titleSize);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(leftColor);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(stokeWidth);

        bitmapUp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_up);
        bitmapDown = BitmapFactory.decodeResource(getResources(), R.drawable.ic_down);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    public void setCurrVal(float currVal) {
        this.currVal = currVal;
        invalidate();
    }

    public void setTitle(String title) {
        this.title = title;
        invalidate();
    }

    public void setRightColor(int rightColor) {
        this.rightColor = rightColor;
        invalidate();
    }

    public void setLeftColor(int leftColor) {
        this.leftColor = leftColor;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(title, 20, 100, textPaint);

        int offsetX = 0,offsetY = height / 3, window = width / 3;

        // 绘制线条
        linePaint.setColor(leftColor);
        canvas.drawLine(offsetX, offsetY + height / 3, offsetX += window, offsetY + height / 3, linePaint);
        linePaint.setColor(midColor);
        canvas.drawLine(offsetX, offsetY + height / 3, offsetX += window, offsetY + height / 3, linePaint);
        linePaint.setColor(rightColor);
        canvas.drawLine(offsetX, offsetY + height / 3, offsetX + window, offsetY  + height / 3, linePaint);


        // 绘制当前值
        if (currVal < minNormal) {
            // 在左边部分
            int x = (int) ((currVal / (double)minNormal) * width / 3) - smallCircleRadius;
            int y = offsetY + height / 3 - stokeWidth / 2;
            if (x < smallCircleRadius) {
                x = smallCircleRadius;
            }
            linePaint.setColor(Color.WHITE);
            linePaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(x, y, smallCircleRadius, linePaint);

            linePaint.setStyle(Paint.Style.STROKE);
            linePaint.setStrokeWidth(circleStokeWidth);
            linePaint.setColor(leftColor);
            canvas.drawCircle(x, y + stokeWidth / 2, smallCircleRadius, linePaint);

            String currStr = String.format(Locale.getDefault(),"%.2f", currVal);
            textPaint.setTextSize(textSize);
            textPaint.setColor(leftColor);
            float strWidth = textPaint.measureText(currStr);
            x -= (int)strWidth / 2;
            if (x < (int)strWidth / 2) {
                x = (int)strWidth / 2;
            }
            if (bitmapDown != null) {
                canvas.drawBitmap(bitmapDown, y - textSize, x - textSize, null);
            } else if (bitmapUp != null) {
                canvas.drawBitmap(bitmapUp, y - textSize, x - textSize, null);
            }
            canvas.drawText(currStr, x, y - textSize, textPaint);
        } else if (currVal > maxNormal) {
            // 当前值在右边部分
            int x = width / 3 * 2 + (int) (((currVal - maxNormal) / (double)(maxVal - maxNormal)) * width / 3) - smallCircleRadius;
            int y = offsetY + height / 3 - stokeWidth / 2;
            if (x + smallCircleRadius > width) {
                x = width - smallCircleRadius;
            }
            linePaint.setColor(Color.WHITE);
            linePaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(x, y, smallCircleRadius, linePaint);

            linePaint.setStyle(Paint.Style.STROKE);
            linePaint.setStrokeWidth(circleStokeWidth);
            linePaint.setColor(rightColor);
            canvas.drawCircle(x, y + stokeWidth / 2, smallCircleRadius, linePaint);

            String currStr = String.format(Locale.getDefault(),"%.2f", currVal);
            textPaint.setTextSize(textSize);
            textPaint.setColor(rightColor);
            float strWidth = textPaint.measureText(currStr);
            x -= (int)strWidth / 2;
            if (x + (int)strWidth / 2 > width) {
                x = width - (int)strWidth / 2;
            }
            canvas.drawText(currStr, x, y - textSize, textPaint);
        } else {
            // 当前值在中间部分
            int x = width / 3 + (int) ((currVal - minNormal) / (double)(maxNormal - minNormal) * (width / 3)) - smallCircleRadius;
            int y = offsetY + height / 3 - stokeWidth / 2;
            linePaint.setColor(Color.WHITE);
            linePaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(x, y, smallCircleRadius, linePaint);

            linePaint.setStyle(Paint.Style.STROKE);
            linePaint.setStrokeWidth(circleStokeWidth);
            linePaint.setColor(midColor);
            canvas.drawCircle(x, y + stokeWidth / 2, smallCircleRadius, linePaint);

            String currStr = String.format(Locale.getDefault(),"%.2f", currVal);
            textPaint.setTextSize(textSize);
            textPaint.setColor(midColor);
            float strWidth = textPaint.measureText(currStr);
            x -= (int)strWidth / 2;
            canvas.drawText(currStr, x, y - textSize, textPaint);
        }


        // 绘制坐标的正常、最小值和最大值
        textPaint.setTextSize(textSize);
        textPaint.setColor(Color.GRAY);
        float textWidth = textPaint.measureText(normalText);
        canvas.drawText(normalText, (width - textWidth) / 2, offsetY + height / 3 + stokeWidth * 3 + textSize / 2, textPaint);

        textWidth = textPaint.measureText(String.valueOf(minNormal));
        canvas.drawText(String.valueOf(minNormal), width / 3 - textWidth / 2, offsetY + height / 3  + stokeWidth * 3 + textSize / 2, textPaint);

        textWidth = textPaint.measureText(String.valueOf(maxNormal));
        canvas.drawText(String.valueOf(maxNormal), width / 3 * 2 - textWidth / 2, offsetY + height / 3 + stokeWidth * 3 + textSize / 2, textPaint);
    }
}
