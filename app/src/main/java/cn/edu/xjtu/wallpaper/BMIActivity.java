package cn.edu.xjtu.wallpaper;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BMIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        BoHeBMIView view1 = findViewById(R.id.boHeBMIView1);
        BoHeBMIView view2 = findViewById(R.id.boHeBMIView2);
        view2.setLeftColor(getResources().getColor(R.color.gray));
        BoHeBMIView view3 = findViewById(R.id.boHeBMIView3);
    }
}
