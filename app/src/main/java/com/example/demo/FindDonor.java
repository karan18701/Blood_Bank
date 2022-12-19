package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRequest;

import java.util.ArrayList;

public class FindDonor extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String[] cityList={"Surat","Ahemdabad","Rajkot","Gandhinagar"};
    String[] bloodGrouplist={"A+","A-","B+","B-","O+","O-","AB+","AB-"};
    String selectedBloodGroup="A+",selectedCity="Surat";
    Spinner bloodgroup,city;
    Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.icon_img);
        getSupportActionBar().setTitle("  Find Donor");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        setContentView(R.layout.activity_find_donor);
        bloodgroup=findViewById(R.id.getBloodGroup);
        city=findViewById(R.id.getCity);
        ArrayAdapter bgadapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,bloodGrouplist);
        bloodgroup.setAdapter(bgadapter);

        ArrayAdapter cityadapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,cityList);
        city.setAdapter(cityadapter);
        bloodgroup.setOnItemSelectedListener(this);
        city.setOnItemSelectedListener(this);

        search=findViewById(R.id.btnSearch);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserHelper userHelper = new UserHelper(FindDonor.this);
                ArrayList<User> users = userHelper.getdata(selectedBloodGroup,selectedCity);


                RecyclerView recyclerView = findViewById(R.id.showDonorList);

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                DonorAdapter noteItemAdapter = new DonorAdapter(getApplicationContext(), users);
                recyclerView.setAdapter(noteItemAdapter);
            }
        });

//       UserHelper userHelper = new UserHelper(FindDonor.this);
//        ArrayList<User> users = userHelper.getdata(selectedBloodGroup,selectedCity);
//
//
//        RecyclerView recyclerView = findViewById(R.id.showDonorList);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        DonorAdapter noteItemAdapter = new DonorAdapter(getApplicationContext(), users);
//        recyclerView.setAdapter(noteItemAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.getBloodGroup)
        {
            selectedBloodGroup = parent.getItemAtPosition(position).toString();
        }
        else if(parent.getId()==R.id.getCity)
        {
            selectedCity= parent.getItemAtPosition(position).toString();

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}