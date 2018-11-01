package activity.code.source.es.scos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import es.source.code.model.Food;

public class FoodDetailed extends AppCompatActivity implements View.OnTouchListener,GestureDetector.OnGestureListener {

    private ArrayList<Food> foodArrayList;
    private int index;
    private ImageView detail_image;
    private TextView detail_name;
    private TextView detail_price;
    private EditText detail_info;
    private GestureDetector gd;
    LinearLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detailed);

        detail_image = (ImageView) findViewById(R.id.detail_image);
        detail_name = (TextView) findViewById(R.id.detail_name);
        detail_price = (TextView) findViewById(R.id.detail_price);
        detail_info = (EditText) findViewById(R.id.detail_info);

        Intent intent = getIntent();
        foodArrayList = (ArrayList<Food>) intent.getSerializableExtra("foodlist");
        index = (int) intent.getIntExtra("index", 0);
        detail_image.setImageResource(foodArrayList.get(index).getImageView());
        detail_name.setText("菜名：" + foodArrayList.get(index).getName());
        detail_price.setText("价格：" + foodArrayList.get(index).getPrice());

        view = (LinearLayout) findViewById(R.id.detail_view);
        view.setOnTouchListener(this);
        view.setLongClickable(true);
        gd = new GestureDetector((GestureDetector.OnGestureListener) this);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        //设置最小的手势移动距离和手势移动速度
        final int FLING_MIN_DISTANCE = 100;
        final int FLING_MIN_VELOCITY = 200;

        //向右的手势
        if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            if (index > 0) {
                index = index - 1;
                detail_image.setImageResource(foodArrayList.get(index).getImageView());
                detail_name.setText("菜名：" + foodArrayList.get(index).getName());
                detail_price.setText("价格：" + foodArrayList.get(index).getPrice());
            } else {
                detail_image.setImageResource(foodArrayList.get(0).getImageView());
                detail_name.setText("菜名：" + foodArrayList.get(0).getName());
                detail_price.setText("价格：" + foodArrayList.get(0).getPrice());
                Toast.makeText(this, "已翻到最开始", Toast.LENGTH_SHORT).show();
            }
        }
        // 向左的手势
        if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            int len = foodArrayList.size();
            if (index < len - 1) {
                index = index + 1;
                detail_image.setImageResource(foodArrayList.get(index).getImageView());
                detail_name.setText("菜名：" + foodArrayList.get(index).getName());
                detail_price.setText("价格：" + foodArrayList.get(index).getPrice());
            } else {
                detail_image.setImageResource(foodArrayList.get(len - 1).getImageView());
                detail_name.setText("菜名：" + foodArrayList.get(len - 1).getName());
                detail_price.setText("价格：" + foodArrayList.get(len - 1).getPrice());
                Toast.makeText(this, "已翻到最末", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gd.onTouchEvent(event);
    }
}