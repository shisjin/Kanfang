package kanfang.qf.com.kanfang.ui;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import kanfang.qf.com.kanfang.R;
import kanfang.qf.com.kanfang.fragment.DiscoverFragment;
import kanfang.qf.com.kanfang.fragment.HomeFragment;
import kanfang.qf.com.kanfang.fragment.MessageFragment;
import kanfang.qf.com.kanfang.fragment.MineFragment;
import kanfang.qf.com.kanfang.utils.SharedUtils;

/**
 * Created by Administrator on 2016/11/21.
 */
public class MainActivity extends AppCompatActivity {

    //底部导航的文字数组
    private String[] tabTexts = {
            "首页", "发现", "消息", "我的"
    };
    //底部导航的图片id数组
    private int[] imgIds = {
            R.drawable.tab_home_sel,
            R.drawable.tab_discover_sel,
            R.drawable.tab_message_sel,
            R.drawable.tab_mine_sel
    };
    //TabHost使用的Fragment类数组
    Class[] fragments = {
            HomeFragment.class, DiscoverFragment.class, MessageFragment.class, MineFragment.class
    };

    //控件声明
    private FragmentTabHost tabHost;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //保存第一次启动数据
        SharedUtils.saveFirstRun(this);
        //初始化控件
        setupViews();
    }

    private void setupViews() {
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        //设置tabHost
        /*
        * 参数一：Context
        *
        * 参数二：FragmentManager
        *
        * 参数三：要显示的Framgnet的容器id
        *
        * */
        tabHost.setup(this, getSupportFragmentManager(), R.id.fragmentLayout);
        inflater = LayoutInflater.from(this);
        //给tabHost添加Tab
        for (int i = 0; i < tabTexts.length; i++) {
            //创建新的Tab
            TabHost.TabSpec tabItem = tabHost.newTabSpec(i + "");
            //给tabItem设置内容
            tabItem.setIndicator(getTabItemView(i));
            //把tabItem添加到TabHost中
            /*
            * 参数一：tab标签
            *
            * 参数二：tab内容的Fragmet类
            *
            * 参数三：Bundle    可以传值到Fragment
            * */
            tabHost.addTab(tabItem, fragments[i], null);
            //tabHost去边线
            tabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);
        }

    }

    LayoutInflater inflater;

    /**
     * 加载底部导航的四个Tab
     *
     * @param index
     * @return
     */
    private View getTabItemView(int index) {
        View view = inflater.inflate(R.layout.tab_item_layout, null);
        //拿到控件中的值
        ImageView iv = (ImageView) view.findViewById(R.id.tab_img);
        //设置图片
        iv.setImageResource(imgIds[index]);
        TextView tv = (TextView) view.findViewById(R.id.tab_tv);
        tv.setText(tabTexts[index]);
        return view;
    }

}
