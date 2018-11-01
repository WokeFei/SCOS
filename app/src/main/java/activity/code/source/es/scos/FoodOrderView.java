package activity.code.source.es.scos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import es.source.code.model.User;

public class FoodOrderView extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<Fragment> list;
    private MyAdapter myAdapter;
    private String[] title = {"未下单菜","已下单菜"};
    private User user;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_order_view);
        //获取User对象
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("currentUser");
        flag = (int) intent.getIntExtra("flag",0);

        //实例化
        viewPager = (ViewPager) findViewById(R.id.food_order_viewPager);
        tabLayout = (TabLayout) findViewById(R.id.food_order_tabLayout);
        //页面，数据源
        list = new ArrayList<>();

        list.add(new NotOrderFragment());
        list.add(new OrderedFragment());

        //viewPager的适配器
        myAdapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myAdapter);
        viewPager.setCurrentItem(flag);
        tabLayout.setupWithViewPager(viewPager);


    }

    // 启动本活动
    public static void actionstart(Context context){
        Intent intent = new Intent(context,FoodOrderView.class);
        context.startActivity(intent);
    }

    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }
}
