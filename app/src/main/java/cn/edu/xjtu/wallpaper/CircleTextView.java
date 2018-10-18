package cn.edu.xjtu.wallpaper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CircleTextView extends View {
    private Paint paint;
    private Path path;
    private String text;
    private int width, height;
    public CircleTextView(Context context) {
        super(context);
    }

    public CircleTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleTextView);
        float textSize = array.getDimension(R.styleable.CircleTextView_textSize, 30);
        int textColor = array.getColor(R.styleable.CircleTextView_textColor, Color.LTGRAY);
        array.recycle();

        text = "hello world, hello, world.hello world, hello, world.";
        paint = new Paint();
        paint.setTextSize(textSize);
        paint.setStrokeWidth(3);
        paint.setColor(textColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    public void setText(String text) {
        this.text = text;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        float length = paint.measureText(text);
        int radius = (int) (length / (2 * Math.PI));

        path.addCircle(width / 2, height / 2, radius, Path.Direction.CW);
        // canvas.drawPath(path, paint);
        canvas.drawTextOnPath(text, path, 0, 0, paint);
    }
}
