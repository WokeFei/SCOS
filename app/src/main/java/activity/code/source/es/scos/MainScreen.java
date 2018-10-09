package activity.code.source.es.scos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        // 接收活动传递的值
        String data = intent.getStringExtra("extra_data");
        // 判断是否和“FromEntry”相等;如果相等,则正常显示当前屏幕;如果不相等，则隐藏导航项
        // 或者若登陆成功，也可正常显示主屏幕
        if((data != null && data.equals("FromEntry")) || (data != null &&data.equals("LoginSuccess"))){
            setContentView(R.layout.activity_main_screen);
        }

    }
}
