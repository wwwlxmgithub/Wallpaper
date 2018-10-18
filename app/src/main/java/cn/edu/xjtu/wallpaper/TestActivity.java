package cn.edu.xjtu.wallpaper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        initViews();
    }


    private void initViews(){
        mLinearLayout=findViewById(R.id.LinearLayout_Test);
        int iImageBaseID=1000000;
        int iTextBaseID=1010000;

        for(int i=0;i<mItemS.length;i++){
            String sItem=mItemS[i];

            RelativeLayout relativeLayout=new RelativeLayout(this);
            // 这里relativeLayout的父元素是LinearLayout，所以它的LayoutParams 类型应该是 LinearLayout.LayoutParams
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,300
            );
            relativeLayout.setLayoutParams(layoutParams);

            RelativeLayout.LayoutParams layoutImageParams=new RelativeLayout.LayoutParams(200,200);
            layoutImageParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);//与父容器的左侧对齐
            layoutImageParams.addRule(RelativeLayout.CENTER_VERTICAL);//垂直居中

            ImageView imageView=new ImageView(this);
            imageView.setImageResource(R.drawable.ic_launcher_background);
            int iImageID=iImageBaseID+i;
            imageView.setId(iImageID);
            imageView.setLayoutParams(layoutImageParams);
            relativeLayout.addView(imageView);

            RelativeLayout.LayoutParams layoutTextParams=new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutTextParams.addRule(RelativeLayout.RIGHT_OF,iImageID); //在图片右边
            layoutTextParams.addRule(RelativeLayout.CENTER_VERTICAL);//垂直居中

            TextView textView=new TextView(this);
            textView.setTextSize(20);
            textView.setText(sItem);
            int iTextID=iTextBaseID+i;
            textView.setId(iTextID);
            textView.setLayoutParams(layoutTextParams);
            relativeLayout.addView(textView);

            mLinearLayout.addView(relativeLayout);
        }
    }

    private LinearLayout mLinearLayout;
    private String[] mItemS={"项目一","项目二"};
}
