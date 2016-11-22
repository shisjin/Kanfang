package kanfang.qf.com.kanfang.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import kanfang.qf.com.kanfang.R;
import kanfang.qf.com.kanfang.fragment.SplashFragment;
import kanfang.qf.com.kanfang.utils.L;

/**
 * Created by Administrator on 2016/11/21.
 */
public class SplashActivity extends AppCompatActivity {

    ViewPager viewPager;
    List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setupViews();
    }

    //点击跳转，跳转到主页
    public void startMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void setupViews() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        //初始化数据源
        fragments = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            SplashFragment sf = new SplashFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(SplashFragment.INDEX_KEY, i);
            sf.setArguments(bundle);
            //添加到集合
            fragments.add(sf);
        }
        //适配器
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };

        viewPager.setAdapter(adapter);


        //设置ViewPager切换动画
        viewPager.setPageTransformer(true, new MyTrans());
        //对viewPager设置切换监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //根据state进行判断
                //如果是手指滑动状态，并滑动界面是最后一页
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING: {
                        //手指滑动,
                        L.d("手指滑动");
                        isLeftScroll = false;

                    }
                    break;
                    case ViewPager.SCROLL_STATE_IDLE: {
                        //停止状态
                        L.d("停止状态");
                        //如果是最后一页，且没有惯性滑动时，跳转

                        if (!isLeftScroll && viewPager.getCurrentItem() == (viewPager.getAdapter().getCount() - 1)) {
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        isLeftScroll = false;
                    }
                    break;
                    case ViewPager.SCROLL_STATE_SETTLING: {
                        //惯性滑动,不切换
                        L.d("惯性滑动,不切换");
                        isLeftScroll = true;
                    }
                    break;
                }
            }
        });

    }

    boolean isLeftScroll = true;

    //做动画的View的id数组
    int[] viewsId = {
            R.id.iv1, R.id.iv2, R.id.iv3
    };

    //实现ViewPager的切换动画
    class MyTrans implements ViewPager.PageTransformer {


        /**
         * @param page     Fragment的界面，是一个ViewGroup
         * @param position page的移动偏移量
         */
        @Override
        public void transformPage(View page, float position) {
            //把page-->ViewGrop
            //遍历page，拿到三个做动画的ImageView
            //根据position设置View的属性
            float transx = page.getWidth() * position;
            for (int i = 0; i < viewsId.length; i++) {
                View view = page.findViewById(viewsId[i]);
//                L.d("position=" + position);
                if (view != null) {
//                    L.d("View不为null");
                    view.setTranslationX(transx);
                }
                //下一个控件的x偏移量
                transx *= 10f;
            }

        }
    }

}
