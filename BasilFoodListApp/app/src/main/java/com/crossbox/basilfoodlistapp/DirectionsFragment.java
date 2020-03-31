package com.crossbox.basilfoodlistapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DirectionsFragment extends Fragment {

    private List<Ingredient> mIngredients;
    private IngredientsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIngredients = new ArrayList<>();
        mIngredients.add(new Ingredient("Red Pepper Flakes","1 tbsp"));
        mIngredients.add(new Ingredient("Basil","6 tbsp"));
        mIngredients.add(new Ingredient("Pine Nuts","2 tbsp"));
        mIngredients.add(new Ingredient("Ricotta","4 cups"));
        mIngredients.add(new Ingredient("Gluten-free Spaghetti","2 cups"));
        mIngredients.add(new Ingredient("Kale","3 cups"));
        mIngredients.add(new Ingredient("Garlic","1 tbsp"));
        mIngredients.add(new Ingredient("Extra Virgin Olive Oil","1 tbsp"));
        mIngredients.add(new Ingredient("Salt","1 tbsp"));

        adapter = new IngredientsAdapter(mIngredients);

        // here we attach our own click listener to the adapter (different than the implementation in IngredientsFragment.java)
        adapter.setOnItemClickListener(new IngredientsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), mIngredients.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.directions_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycler1);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
