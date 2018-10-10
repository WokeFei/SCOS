package activity.code.source.es.scos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import es.source.code.model.User;

public class MainScreen extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // 定义两个数组
    private int[] image = {R.drawable.ic_dish,R.drawable.ic_order,R.drawable.ic_user,R.drawable.ic_help};
    private String[] text = {"点菜","订单","登录/注册","帮助"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        // 接收活动传递的值
        String data = intent.getStringExtra("extra_data");
        User user = (User) intent.getSerializableExtra("current_user");
        setContentView(R.layout.activity_main_screen);
        switch (data){
            case "RegisterSuccess":
                Toast.makeText(this,"欢迎您成为SCOS新客户",Toast.LENGTH_SHORT).show();
                gridShow();
                break;
            case "FromEntry" :
                gridShow();
            case "LoginSuccess":
                gridShow();
            default:
                break;
            }
        }

    // 定义gridShow（）函数，使得grid导航栏显示出来
    private  void gridShow(){
        //显示GridView界面
        GridView gridView = (GridView) findViewById(R.id.gridView);
        ArrayList<HashMap<String,Object>> imagelist = new ArrayList<HashMap<String, Object>>();
        //使用HashMap将图片添加到一个数组中
        for(int i = 0;i < image.length;i++){
            HashMap<String,Object> map = new HashMap<String, Object>();
            map.put("image",image[i]);
            map.put("txt",text[i]);
            imagelist.add(map);
        }
        // 使用simpleAdapter封装数据，将图片显现出来
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,imagelist,
                R.layout.items,new String[]{"image","txt"},new int[]{R.id.image_item,R.id.text_title});
        //设置GridView的适配器为新建的simpleAdapter
        gridView.setAdapter(simpleAdapter);
        //设置监听器
        gridView.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                Toast.makeText(this,"this",Toast.LENGTH_SHORT).show();
        }
    }
}
