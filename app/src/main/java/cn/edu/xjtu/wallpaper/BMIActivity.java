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
        view1.setTitle("view1");
        BoHeBMIView view2 = findViewById(R.id.boHeBMIView2);
        view2.setCurrVal(320);
        view2.setTitle("view2");
        view2.setLeftColor(getResources().getColor(R.color.gray));
        BoHeBMIView view3 = findViewById(R.id.boHeBMIView3);
        view3.setCurrVal(370);
        view3.setTitle("view3");
        view3.setRightColor(Color.RED);
    }
}
