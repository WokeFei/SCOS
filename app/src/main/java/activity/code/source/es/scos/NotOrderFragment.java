package activity.code.source.es.scos;

import android.annotation.SuppressLint;
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


public class NotOrderFragment extends Fragment {

    private View view;
    private List<Food> notorderedList = new ArrayList<>();
    private TextView notordered_count;
    private TextView notordered_price;
    private Button notordered_pay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notorder_list,container,false);
        // Inflate the layout for this fragment
        initFoods();

        RecyclerView recyclerView = view.findViewById(R.id.recycler_notordered);
        NotorderedAdapter notorderedAdapter = new NotorderedAdapter(getActivity(),notorderedList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(notorderedAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        notordered_count = (TextView) getActivity().findViewById(R.id.notordered_count);
        notordered_count.setText("已点菜数：" + notorderedList.size());
        notordered_price = (TextView) getActivity().findViewById(R.id.notordered_money);
        float price = 0;
        for (Food food : notorderedList){
            price = price + Integer.parseInt(food.getPrice());
        }
        notordered_price.setText("订单总价：" + price);
        notordered_pay = (Button) getActivity().findViewById(R.id.notordered_pay);
        notordered_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"已提交订单",Toast.LENGTH_SHORT).show();

            }
        });
    }

    class NotorderedAdapter extends RecyclerView.Adapter<NotorderedAdapter.ViewHolder>{

        private List<Food> mnotordered;

        class ViewHolder extends RecyclerView.ViewHolder{

            ImageView notorderedImage;
            TextView notorderedPrice;
            TextView notorderedName;
            View notorderedView;
            TextView notorderedNum;
            TextView notorderedMark;

            public ViewHolder(@NonNull View view) {
                super(view);
                notorderedView = view;
                notorderedImage = (ImageView) view.findViewById(R.id.notordered_image);
                notorderedName = (TextView) view.findViewById(R.id.notordered_name);
                notorderedPrice = (TextView) view.findViewById(R.id.notordered_price);
                notorderedNum = (TextView) view.findViewById(R.id.notordered_num);
                notorderedMark = (TextView) view.findViewById(R.id.notordered_mark);
            }
        }

        public NotorderedAdapter(FragmentActivity activity,List<Food> food){
            mnotordered = food;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notordered_items,viewGroup,false);
            final NotorderedAdapter.ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            Food food = mnotordered.get(i);
            viewHolder.notorderedImage.setImageResource(food.getImageView());
            viewHolder.notorderedName.setText(food.getName());
            viewHolder.notorderedPrice.setText(food.getPrice());
            viewHolder.notorderedNum.setText("数量：1");
        }

        @Override
        public int getItemCount() {
            return mnotordered.size();
        }
    }
    public void initFoods(){
        for (int i = 0;i < 4;i++){
            Food coldFood = new Food(R.drawable.ic_dish,"10","蔬菜");
            notorderedList.add(coldFood);
            Food coldFood_2 = new Food(R.drawable.ic_order,"20","筷子");
            notorderedList.add(coldFood_2);
        }
    }

}
