package com.example.demo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.demo.entity.User;

import java.util.Arrays;

public class UpdateProfile extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    Button cancel;
    EditText name,contact,email,password;
    Spinner bloodgroup,city;
    RadioGroup gender;
    RadioButton male,female;
    User user;
    String selectedgender;
    String sbgrp,scity;
    String[] cityList={"Surat","Ahemdabad","Rajkot","Gandhinagar"};
    String[] bloodGrouplist={"A+","A-","B+","B-","O+","O-","AB+","AB-"};
    String selectedBloodGroup,selectedCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.icon_img);
        getSupportActionBar().setTitle("  View Profile");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        cancel=findViewById(R.id.cancel);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        contact=findViewById(R.id.contact);
        password=findViewById(R.id.password);
        gender=findViewById(R.id.genderRadioGroup);
        gender.setEnabled(false);
        bloodgroup=findViewById(R.id.bgroup);
        bloodgroup.setEnabled(false);
        city=findViewById(R.id.city);
        city.setEnabled(false);
        male=findViewById(R.id.male);
        male.setEnabled(false);
        female=findViewById(R.id.female);
        female.setEnabled(false);
        cancel=findViewById(R.id.cancel);
        Intent intent=getIntent();
        user=(User)intent.getSerializableExtra("user");
        ArrayAdapter bgadapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,bloodGrouplist);
        bloodgroup.setAdapter(bgadapter);

        ArrayAdapter cityadapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,cityList);
        city.setAdapter(cityadapter);

        bloodgroup.setOnItemSelectedListener(this);
        city.setOnItemSelectedListener(this);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UpdateProfile.this,Home.class);
                startActivity(intent);
                finish();
            }
        });


        loadData();
    }

    private void loadData()
    {

        Intent intent=getIntent();
        user=(User)intent.getSerializableExtra("user");
        name.setText(user.getName());
        email.setText(user.getEmail());
        contact.setText(user.getContact());
        password.setText(user.getPassword());
        selectedgender=user.getGender();
        sbgrp=user.getBloodgroup();
        Arrays.asList(bloodGrouplist).indexOf(sbgrp);
        bloodgroup.setSelection(Arrays.asList(bloodGrouplist).indexOf(sbgrp));

        scity=user.getCity();
        Arrays.asList(cityList).indexOf(scity);
        city.setSelection(Arrays.asList(cityList).indexOf(scity));
        if(selectedgender.equals("Male"))
            male.setChecked(true);
        if(selectedgender.equals("Female"))
            female.setChecked(true);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.bgroup)
        {
            selectedBloodGroup = parent.getItemAtPosition(position).toString();
        }
        else if(parent.getId()==R.id.city)
        {
            selectedCity= parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}