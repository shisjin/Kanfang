package kanfang.qf.com.kanfang.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import kanfang.qf.com.kanfang.R;
import kanfang.qf.com.kanfang.adapter.CityChoiceAdapter;
import kanfang.qf.com.kanfang.bean.CityBean;
import kanfang.qf.com.kanfang.inter.CityChoiceInterface;
import kanfang.qf.com.kanfang.utils.ApiManager;
import kanfang.qf.com.kanfang.utils.CityJsonUtils;
import kanfang.qf.com.kanfang.utils.L;
import kanfang.qf.com.kanfang.utils.SharedUtils;
import kanfang.qf.com.kanfang.widget.SlideLetterView;
import kanfang.qf.com.kanfang.widget.SlideView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by Administrator on 2016/11/21.
 */
public class CityChoiceActivity extends BaseNoActionBarActivity implements SlideView.SlideClick {

    //控件声明
    StickyListHeadersListView lv;
    //数据源
    List<CityBean> data;
    //适配器
    CityChoiceAdapter adapter;


    SlideView slideView;
    SlideLetterView letterView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载界面
        setContentView(R.layout.activity_citychoice);
        //初始化
        setupViews();
        //初始化数据
        initData();
        //初始化Adapter
        adapter = new CityChoiceAdapter(this, data);
        //设置Adapter
        lv.setAdapter(adapter);
        //联网获取数据
        getCityData();
    }

    private void getCityData() {
        //retrofit联网加载数据
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        //创建接口对象
        CityChoiceInterface city = retrofit.create(CityChoiceInterface.class);
        //获取Call
        Call<String> call = city.getCity();
        //发异步请求，解析数据
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //得到下载数据
                String value = response.body();
                L.d(value);
                //通过CityJsonUtils解析，得到数据集合
                try {
                    List<CityBean> cities = CityJsonUtils.getCityByJson(value);
                    //把解析结果，添加到总集合中
                    data.clear();
                    data.addAll(cities);
                    //更新界面
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    private void initData() {
        data = new ArrayList<>();
    }

    private void setupViews() {
        lv = (StickyListHeadersListView) findViewById(R.id.lv);
        slideView = (SlideView) findViewById(R.id.slideView);
        letterView = (SlideLetterView) findViewById(R.id.letterView);


        //设置侧滑控件的监听器
        slideView.setOnSlideClick(this);

    }

    /**
     * @param postion 指的是字母在数组中的下标
     * @param str
     */
    @Override
    public void slideOnClick(int postion, String str) {
        //侧滑时回调方法
        //letterView显示出来
        //设置letterView的text
        letterView.setVisibility(View.VISIBLE);
        letterView.setText(str);
        //ListView滑动到指定位置
        //从data集合中，找到第一条数据：以str开头的数据的位置
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getLetter().equals(str)) {
                //把listview滑动到i的位置
                lv.setSelection(i);
                //返回
                return;
            }
        }

    }

    @Override
    public void slideUp() {
        //侧滑控件手指抬起时
        letterView.setVisibility(View.GONE);
    }
}
