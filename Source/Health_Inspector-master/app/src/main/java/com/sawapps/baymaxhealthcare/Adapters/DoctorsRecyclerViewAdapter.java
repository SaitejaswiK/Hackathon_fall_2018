package com.sawapps.baymaxhealthcare.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sawapps.baymaxhealthcare.Network.Responses.DoctorSearchResponse.Doctor;
import com.sawapps.baymaxhealthcare.R;

import java.util.List;

/**
 * Created by SaiTejaswi Koppuravuri @ BayMaxHealthCare.
 */

public class DoctorsRecyclerViewAdapter extends RecyclerView.Adapter<DoctorsRecyclerViewAdapter.DoctorsViewHolder> {

    List<Doctor> data;

    public DoctorsRecyclerViewAdapter(List<Doctor> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public DoctorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DoctorsViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.doctor_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorsViewHolder holder, int position) {
        try {
            Doctor current = data.get(position);
            if (current != null) {


                holder.name.setText(current.profile.firstName + " " + current.profile.middleName + " " + current.profile.lastName);
                holder.bio.setText(current.profile.bio);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class DoctorsViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView bio;

        public DoctorsViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            bio = itemView.findViewById(R.id.bio);
        }
    }
}
