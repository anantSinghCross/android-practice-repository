// Important File to understand the following concepts
// Concepts used:
// 1. Custom Adapter for RecyclerView
// 2. OnItemClickListener for RecyclerView (attaching ClickListeners to views inside the recyclerView's items)
// Also see IngredientsFragment.java for more information

package com.crossbox.basilfoodlistapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private List<Ingredient> ingredientsList;

    private OnItemClickListener clickListener;

    // creating an interface for itemClickListener
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener){
        clickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView text1;
        public TextView smallText1;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.text1);
            smallText1 = itemView.findViewById(R.id.smallText1);

//            text1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(clickListener!=null){
//                        clickListener.onItemClick(v,getAdapterPosition());
//                    }
//                }
//            });

            smallText1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(clickListener!=null){
                        clickListener.onItemClick(v,getAdapterPosition());
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(clickListener!=null){
                        clickListener.onItemClick(v,getAdapterPosition());
                    }
                }
            });
        }

    }

    public IngredientsAdapter(List<Ingredient> ingredients){
        ingredientsList = ingredients;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_ingredients_fragment,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredient ingredient = ingredientsList.get(position);

        TextView textView = holder.text1;
        textView.setText(ingredient.getName());

        TextView smallText = holder.smallText1;
        smallText.setText(ingredient.getQuantity());
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }
}
