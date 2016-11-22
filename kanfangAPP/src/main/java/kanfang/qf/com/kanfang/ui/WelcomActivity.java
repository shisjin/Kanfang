package kanfang.qf.com.kanfang.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import kanfang.qf.com.kanfang.R;
import kanfang.qf.com.kanfang.utils.SharedUtils;

public class WelcomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // 延迟2s跳转
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //判断是否是第一次启动，如果是第一次，跳转到引导界面
                if (SharedUtils.isFirstRun(WelcomActivity.this)) {
                    //测试，跳转到引导界面
                    Intent intent = new Intent(WelcomActivity.this, SplashActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    //如果不是第一次启动，跳转到主界面
                    //测试，跳转到引导界面
                    Intent intent = new Intent(WelcomActivity.this, CityChoiceActivity.class);
                    startActivity(intent);
                    finish();

                }


            }
        }, 2000);


    }
}
