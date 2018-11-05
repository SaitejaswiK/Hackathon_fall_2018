package com.sawapps.baymaxhealthcare.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sawapps.baymaxhealthcare.Network.GetDiet.Meal;
import com.sawapps.baymaxhealthcare.R;

import java.util.List;

/**
 * Created by SaiTejaswi Koppuravuri @ BayMaxHealthCare.
 */

public class MealRecyclerViewAdapter extends RecyclerView.Adapter<MealRecyclerViewAdapter.MealsViewHolder> {

    List<Meal> data;

    public MealRecyclerViewAdapter(List<Meal> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MealsViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.doctor_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MealsViewHolder holder, int position) {
        try {
            Meal current = data.get(position);
            if (current != null) {


                holder.name.setText(current.title);
                holder.bio.setText("Ready in minutes: " + current.readyInMinutes + "\nServings: " + current.servings);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class MealsViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView bio;

        public MealsViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            bio = itemView.findViewById(R.id.bio);
        }
    }
}
