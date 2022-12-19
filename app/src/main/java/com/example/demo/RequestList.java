package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.demo.entity.UserRequest;

import java.util.ArrayList;

public class RequestList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.icon_img);
        getSupportActionBar().setTitle("  Blood Request");
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        RequestHelper requestHelper = new RequestHelper(RequestList.this);
        ArrayList<UserRequest> userRequests = requestHelper.getdata();


        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        BloodListAdapter noteItemAdapter = new BloodListAdapter(getApplicationContext(), userRequests);
        recyclerView.setAdapter(noteItemAdapter);
    }
}