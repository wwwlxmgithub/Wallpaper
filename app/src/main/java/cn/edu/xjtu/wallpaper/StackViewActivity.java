package cn.edu.xjtu.wallpaper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.StackView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class StackViewActivity extends AppCompatActivity {

    private StackView stackView;
    private int[] imageIds = new int[]{R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5};
    private List<Integer> images = new ArrayList<>();
    private ImageAdapter imageAdapter;
    private Timer timerDown, timerUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack_view);
        stackView = findViewById(R.id.stackView);

        for (int i = 0; i < imageIds.length; i++) {
            images.add(imageIds[i]);
        }

        imageAdapter = new ImageAdapter();

        stackView.setAdapter(imageAdapter);
        stackView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(StackViewActivity.this, "positon " + position, Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.downButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerUp != null) timerUp.cancel();
                timerDown = new Timer();
                timerDown.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                stackView.showNext();
                            }
                        });
                    }
                }, 0, 1000);
            }
        });
        findViewById(R.id.upButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerDown != null) timerDown.cancel();
                timerUp = new Timer();
                timerUp.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                stackView.showPrevious();
                            }
                        });
                    }
                }, 0, 1000);
            }
        });
    }
    class ImageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Object getItem(int position) {
            return images.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(StackViewActivity.this);
            imageView.setImageResource(images.get(position));
            return imageView;
        }
    }
}
