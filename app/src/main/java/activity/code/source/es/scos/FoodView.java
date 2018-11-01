package activity.code.source.es.scos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import es.source.code.model.User;

public class FoodView extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<Fragment> list;
    private MyAdapter myadapter;
    private String[] titles= {"凉菜","热菜","海鲜","酒水"};

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_view);
        // 实例化
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        // 页面，数据源
        list = new ArrayList<>();
        list.add(new ColdFoodFragment());
        list.add(new HotFoodFragment());
        list.add(new SeaFoodFragment());
        list.add(new DrinkFoodFragment());
        // ViewPager的适配器
        myadapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myadapter);
        // 绑定
        tabLayout.setupWithViewPager(viewPager);

        //获取传递来的user对象
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("currentUser");

    }
    // 重写foodView活动上的菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.food_menu,menu);
        return true;
    }

    // 监听菜单上的事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.ordered:
                Intent intent = new Intent(this,FoodOrderView.class);
                intent.putExtra("currentUser",user);
                intent.putExtra("flag",0);
                startActivity(intent);
                break;
            case R.id.cheakOrder:
                Intent intent1 = new Intent(this,FoodOrderView.class);
                intent1.putExtra("currentUser",user);
                intent1.putExtra("flag",1);
                startActivity(intent1);
                break;
            case R.id.askForHelp:
                break;
        }
        return true;
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

        // 设置没个Tab的标题
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
