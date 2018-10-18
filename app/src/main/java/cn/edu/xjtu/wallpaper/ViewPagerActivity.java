package cn.edu.xjtu.wallpaper;

import android.database.DataSetObserver;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private final List<View> pageViews = new ArrayList<>();
    private final ImageView[] imageViews = new ImageView[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        LayoutInflater layoutInflater = getLayoutInflater();
        View item1 = layoutInflater.inflate(R.layout.layout_item0, null);
        View item2 = layoutInflater.inflate(R.layout.layout_item1, null);
        View item3 = layoutInflater.inflate(R.layout.layout_item2, null);
        pageViews.add(item1);
        pageViews.add(item2);
        pageViews.add(item3);

        LinearLayout ll = findViewById(R.id.indicatorLL);
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(layoutParams);
            if (i == 0) {
                imageView.setImageResource(R.drawable.ic_selected);
            } else {
                imageView.setImageResource(R.drawable.ic_unselect);
            }
            imageViews[i] = imageView;
            ll.addView(imageView);
        }
        PagerAdapter pagerAdapter = new PageAdapter();
        ViewPager pager = findViewById(R.id.guidePager);
        pager.setAdapter(pagerAdapter);
        pager.addOnPageChangeListener(new GuidePageChangeListener());
    }
    class PageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(pageViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(pageViews.get(position));
            return pageViews.get(position);
        }

        @Override
        public void startUpdate(ViewGroup container) {
            super.startUpdate(container);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
        }

        @Override
        public Parcelable saveState() {
            return super.saveState();
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
            super.restoreState(state, loader);
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {
            super.registerDataSetObserver(observer);
        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {
            super.unregisterDataSetObserver(observer);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(position);
        }

        @Override
        public float getPageWidth(int position) {
            return super.getPageWidth(position);
        }
    }

    class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < imageViews.length; i++) {
                imageViews[i].setImageResource(R.drawable.ic_unselect);
                if (position == i) {
                    imageViews[i].setImageResource(R.drawable.ic_selected);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
