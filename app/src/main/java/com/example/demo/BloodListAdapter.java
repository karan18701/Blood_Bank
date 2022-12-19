package com.example.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRequest;

import java.util.ArrayList;

public class BloodListAdapter extends RecyclerView.Adapter<BloodListAdapter.BloodListViewHolder>{
    Context context;
    ArrayList<UserRequest> userArrayList;

    public BloodListAdapter(Context context, ArrayList<UserRequest> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public BloodListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BloodListViewHolder(LayoutInflater.from(context).inflate(R.layout.llist_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BloodListViewHolder holder, int position) {
        UserRequest userRequest = userArrayList.get(position);
        holder.bgroup.setText(userRequest.getName());
        holder.name.setText(userRequest.getName());
        holder.contact.setText(userRequest.getContact());
        holder.city.setText(userRequest.getCity());
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class BloodListViewHolder extends RecyclerView.ViewHolder{
        TextView bgroup,contact,name,city;
        public BloodListViewHolder(@NonNull View itemView) {
            super(itemView);
            bgroup = itemView.findViewById(R.id.targetBG);
            contact=itemView.findViewById(R.id.targetCN);
            name=itemView.findViewById(R.id.reqstUser);
            city=itemView.findViewById(R.id.reqstLocation);

        }

    }


}
