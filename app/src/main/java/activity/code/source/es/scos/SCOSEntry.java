package activity.code.source.es.scos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class SCOSEntry extends AppCompatActivity implements View.OnTouchListener,GestureDetector.OnGestureListener {

    private RelativeLayout view;
    private GestureDetector gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);
        view = (RelativeLayout) findViewById(R.id.entry_layout);
        view.setOnTouchListener(this);
        view.setLongClickable(true);
        gd = new GestureDetector((GestureDetector.OnGestureListener)this);

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
        final int FLING_MIN_DISTANCE=100;
        final int FLING_MIN_VELOCITY=200;

        //向左的手势
        if(e1.getX() - e2.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            // 跳转到下一个Avtivity去，并且传递一个String值FromEntry
            Intent intent = new Intent(SCOSEntry.this,MainScreen.class);
            intent.putExtra("extra_data","FromEntry");
            startActivity(intent);
        }
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gd.onTouchEvent(event);
    }
}
