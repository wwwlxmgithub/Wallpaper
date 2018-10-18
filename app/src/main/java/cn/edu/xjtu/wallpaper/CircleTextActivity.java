package cn.edu.xjtu.wallpaper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

public class CircleTextActivity extends AppCompatActivity {
    CircleTextView circleTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_text);
        circleTextView = findViewById(R.id.circleTextView);
        findViewById(R.id.changeTextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeText();
            }
        });
    }

    private void changeText() {
        circleTextView.setText(new String(Base64.encode( new String("start : " + System.currentTimeMillis() + " : end").getBytes(), Base64.DEFAULT)));
    }
}
