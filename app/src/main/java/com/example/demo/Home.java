package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.demo.entity.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Home extends AppCompatActivity {
    Button request,donor,hospital;
    FloatingActionButton post;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.icon_img);
        getSupportActionBar().setTitle("  Blood Bank");
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        Intent intent=getIntent();
        user=(User)intent.getSerializableExtra("user");

        request=findViewById(R.id.request);
        donor=findViewById(R.id.donor);
        hospital=findViewById(R.id.hospital);
        post=findViewById(R.id.addNewButton);

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Home.this,RequestList.class);
                startActivity(intent1);

            }
        });

        donor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(Home.this,FindDonor.class);
                startActivity(intent2);
            }
        });

        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(Home.this,FindHospital.class);
                startActivity(intent3);

            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,RequestPost.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.update:
                Intent intent=new Intent(Home.this,UpdateProfile.class);
                intent.putExtra("user",user);
                startActivity(intent);
                finish();
                return true;
            case R.id.logout:
                Intent intent1=new Intent(Home.this,MainActivity.class);
                startActivity(intent1);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_item,menu);
        return true;
    }
}
