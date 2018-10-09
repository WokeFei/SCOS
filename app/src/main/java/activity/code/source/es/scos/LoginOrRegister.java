package activity.code.source.es.scos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import static android.view.View.VISIBLE;

public class LoginOrRegister extends AppCompatActivity implements View.OnClickListener {
    // 实例化各个组件
    private ProgressBar bar_load;
    private Button button_login;
    private Button button_back;
    private EditText edit_user;
    private EditText edit_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);
        button_login = (Button) findViewById(R.id.button_login);
        button_back = (Button) findViewById(R.id.button_back);
        bar_load = (ProgressBar) findViewById(R.id.bar_load);
        edit_user = (EditText) findViewById(R.id.edit_user);
        edit_password = (EditText) findViewById(R.id.edit_password);
        button_back.setOnClickListener(this);
        button_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login:
                // 想展示progressbar2秒，功能尚未实现
                bar_load.setVisibility(View.VISIBLE);
                while (bar_load.getMax() != bar_load.getProgress()){

                    try {
                        Thread.sleep(200);
                        int progress = bar_load.getProgress();
                        progress += 20;
                        bar_load.setProgress(progress);
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                bar_load.setProgress(0);
                bar_load.setVisibility(View.INVISIBLE);
                // 正则表达式，只匹配英文大小写字母和数字
                String regex = "^[a-z0-9A-Z]+$";
                // 判断输入框中的内容是否符合要求
                if(edit_user.getText().toString().matches(regex) && edit_password.getText().toString().matches(regex)){
                    // 屏幕跳转至 MainScreen，并向 MainScreen 类传 递一个数据 String 值为“LoginSuccess”
                    Intent intent = new Intent(LoginOrRegister.this,MainScreen.class);
                    intent.putExtra("extra_data","LoginSuccess");
                    startActivity(intent);
                }
                if (!edit_user.getText().toString().matches(regex)){
                    edit_user.setError("输入内容不符合规则");
                }
                if(!edit_password.getText().toString().matches(regex)){
                    edit_password.setError("输入内容不符合规则");
                }
                break;
            case R.id.button_back:
                Intent intent = new Intent(this,MainScreen.class);
                intent.putExtra("extra_data","Return");
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
