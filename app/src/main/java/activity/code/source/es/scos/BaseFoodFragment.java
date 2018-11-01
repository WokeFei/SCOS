package activity.code.source.es.scos;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.source.code.model.Food;


/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFoodFragment extends Fragment {

    private View view;
    public List<Food> foodlist = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_food_list,container,false);
        // Inflate the layout for this fragment
        initFoods();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_food);
        FoodAdapter foodAdapter = new FoodAdapter(getActivity(),foodlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(foodAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        return view;
    }

    public void initFoods(){
        for (int i = 0;i < 15;i++){
            Food coldFood = new Food(R.drawable.ic_dish,"10","蔬菜");
            foodlist.add(coldFood);
            Food coldFood_2 = new Food(R.drawable.ic_help,"20","筷子");
            foodlist.add(coldFood_2);
        }
    }

    class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>{

        private List<Food> mFoodList;

        class  ViewHolder extends RecyclerView.ViewHolder{
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
        public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_items,viewGroup,false);
            final FoodAdapter.ViewHolder holder = new ViewHolder(view);
            // 点击其他区域时的响应事件
            holder.foodView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FoodOrderView.actionstart(getActivity());
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
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
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

}
