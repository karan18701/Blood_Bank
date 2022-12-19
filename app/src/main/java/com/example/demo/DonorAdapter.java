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

public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.DonorListViewHolder>{
    Context context;
    ArrayList<User> userArrayList;

    public DonorAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public DonorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DonorListViewHolder(LayoutInflater.from(context).inflate(R.layout.llist_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DonorListViewHolder holder, int position) {
        User user = userArrayList.get(position);
        holder.bgroup.setText(user.getBloodgroup());
        holder.name.setText(user.getName());
        holder.contact.setText(user.getContact());
        holder.city.setText(user.getCity());
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class DonorListViewHolder extends RecyclerView.ViewHolder{
        TextView bgroup,contact,name,city;
        public DonorListViewHolder(@NonNull View itemView) {
            super(itemView);
            bgroup = itemView.findViewById(R.id.targetBG);
            contact=itemView.findViewById(R.id.targetCN);
            name=itemView.findViewById(R.id.reqstUser);
            city=itemView.findViewById(R.id.reqstLocation);

        }

    }


}
