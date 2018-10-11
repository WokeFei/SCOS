package activity.code.source.es.scos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
            Food coldFood = new Food(R.drawable.ic_dish,"10","红烧肉");
            foodlist.add(coldFood);
            Food coldFood_2 = new Food(R.drawable.ic_help,"20","衣服");
            foodlist.add(coldFood_2);
        }
    }

}
