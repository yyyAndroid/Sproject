package com.abe.dwwd.sporjectone.ui.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.abe.dwwd.sporjectone.R;
import com.abe.dwwd.sporjectone.ui.adapter.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class ViewPageActivity extends BaseActivity {
    ViewPager viewPager;
    private int[] mImageids = new int[]{R.mipmap.one,R.mipmap.two,R.mipmap.three};
    private List<ImageView> imgs = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        viewPager = (ViewPager) findViewById(R.id.viewpage);
        initData();
        initViewPage();
    }

    private void initViewPage(){

       int pagerWidth = (int) (getResources().getDisplayMetrics().widthPixels * 8.5f / 10.0f);
        viewPager.measure(0, 0);
        ViewGroup.LayoutParams lp = viewPager.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(pagerWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
        } else {
            lp.width = pagerWidth;
        }
        viewPager.setLayoutParams(lp);
        viewPager.setPageMargin(20);
        viewPager.setCurrentItem(4);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager.setAdapter(new PagerAdapter() {

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(imgs.get(position));
                return imgs.get(position);
            }

            @Override
            public int getCount() {
                return mImageids.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(imgs.get(position));
            }
        });
    }

    private void initData(){
        for (int imgid : mImageids){
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageResource(imgid);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(50,50));
            imgs.add(imageView);
        }
    }
}
