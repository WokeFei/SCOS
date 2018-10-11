package activity.code.source.es.scos;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import es.source.code.model.Food;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>{

    private List<Food> mFoodList;

    static class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView foodImage;
        TextView foodPrice;
        TextView foodName;
        View foodView;
        TextView food_btn;

        public ViewHolder(View view){
            super(view);
            foodView = view;
            foodImage = (ImageView) view.findViewById(R.id.food_image);
            foodName = (TextView) view.findViewById(R.id.food_name);
            foodPrice = (TextView) view.findViewById(R.id.food_price);
            food_btn = (TextView) view.findViewById(R.id.food_btn);
        }
    }

    public FoodAdapter(FragmentActivity activity, List<Food> food){
        mFoodList = food;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_items,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
         // 点击其他区域时的响应事件
        holder.foodView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posotion = holder.getAdapterPosition();
                Food food = mFoodList.get(posotion);
            }
        });
        // 点击图片时的响应事件
        holder.foodImage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int postion = holder.getAdapterPosition();
                Food food = mFoodList.get(postion);
                Toast.makeText(v.getContext(),"you clicked view" + food.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        // 点击点菜按钮时的响应事件
        holder.food_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int postion = holder.getAdapterPosition();
                Food food = mFoodList.get(postion);
                Toast.makeText(v.getContext(),"点菜成功",Toast.LENGTH_SHORT).show();
                holder.food_btn.setText("退点");
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int i) {
        Food food = mFoodList.get(i);
        viewHolder.foodImage.setImageResource(food.getImageView());
        viewHolder.foodName.setText(food.getName());
        viewHolder.foodPrice.setText(food.getPrice());
    }

    @Override
    public int getItemCount() {
        return mFoodList.size();
    }
}
