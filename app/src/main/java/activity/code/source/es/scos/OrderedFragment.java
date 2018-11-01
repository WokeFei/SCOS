package activity.code.source.es.scos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.source.code.model.Food;
import es.source.code.model.User;


public class OrderedFragment extends Fragment {

    private View view;
    private List<Food> orderedList = new ArrayList<>();
    private TextView ordered_count;
    private TextView ordered_price;
    private Button ordered_pay;
    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ordered_list,container,false);
        // Inflate the layout for this fragment
        initFoods();

        Intent intent = getActivity().getIntent();
        user = (User) intent.getSerializableExtra("currentUser");

        RecyclerView recyclerView = view.findViewById(R.id.recycler_ordered);
        OrderedAdapter orderedAdapter = new OrderedAdapter(getActivity(),orderedList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(orderedAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ordered_count = (TextView) getActivity().findViewById(R.id.ordered_count);
        ordered_count.setText("已点菜数：" + orderedList.size());
        ordered_price = (TextView) getActivity().findViewById(R.id.ordered_money);
        float price = 0;
        for (Food food : orderedList){
            price = price + Integer.parseInt(food.getPrice());
        }
        ordered_price.setText("订单总价：" + price);
        ordered_pay = (Button) getActivity().findViewById(R.id.ordered_pay);
        ordered_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user != null){
                    if (user.isOldUser())
                        Toast.makeText(getActivity(),"老顾客，您本次可享受七折优惠",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getActivity(),"下次再来就有优惠了",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getActivity(),"注册账户有优惠哦",Toast.LENGTH_SHORT).show();


            }
        });
    }

    class OrderedAdapter extends RecyclerView.Adapter<OrderedAdapter.ViewHolder>{
        private List<Food> mOrdered;

        class ViewHolder extends RecyclerView.ViewHolder{

            ImageView orderedImage;
            TextView orderedPrice;
            TextView orderedName;
            View orderedView;
            TextView orderedNum;
            TextView orderedMark;

            public ViewHolder(@NonNull View view) {
                super(view);
                orderedView = view;
                orderedImage = (ImageView) view.findViewById(R.id.ordered_image);
                orderedName = (TextView) view.findViewById(R.id.ordered_name);
                orderedPrice = (TextView) view.findViewById(R.id.ordered_price);
                orderedNum = (TextView) view.findViewById(R.id.ordered_num);
                orderedMark = (TextView) view.findViewById(R.id.ordered_mark);
            }
        }

        public OrderedAdapter(FragmentActivity activity,List<Food> food){
            mOrdered = food;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ordered_items,viewGroup,false);
            final OrderedAdapter.ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            Food food = mOrdered.get(i);
            viewHolder.orderedImage.setImageResource(food.getImageView());
            viewHolder.orderedName.setText(food.getName());
            viewHolder.orderedPrice.setText(food.getPrice());
            viewHolder.orderedNum.setText("数量：1");
        }

        @Override
        public int getItemCount() {
            return mOrdered.size();
        }
    }
    public void initFoods(){
        for (int i = 0;i < 3;i++){
            Food coldFood = new Food(R.drawable.ic_dish,"10","蔬菜");
            orderedList.add(coldFood);
            Food coldFood_2 = new Food(R.drawable.ic_help,"20","筷子");
            orderedList.add(coldFood_2);
        }
    }

}
