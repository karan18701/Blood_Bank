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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.demo.entity.UserRequest;

public class RequestPost extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText name,contact;
    Spinner bloodgroup,city;
    Button post;
    String[] cityList={"Surat","Ahemdabad","Rajkot","Gandhinagar"};
    String[] bloodGrouplist={"A+","A-","B+","B-","O+","O-","AB+","AB-"};
    String selectedBloodGroup,selectedCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_post);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.icon_img);
        getSupportActionBar().setTitle("  Request Blood");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        name=findViewById(R.id.name);
        contact=findViewById(R.id.contact);
        bloodgroup=findViewById(R.id.spinnerBlood);
        city=findViewById(R.id.spinnerCity);
        post=findViewById(R.id.post);
        ArrayAdapter bgadapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,bloodGrouplist);
        bloodgroup.setAdapter(bgadapter);

        ArrayAdapter cityadapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,cityList);
        city.setAdapter(cityadapter);
        bloodgroup.setOnItemSelectedListener(this);
        city.setOnItemSelectedListener(this);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestHelper requestHelper = new RequestHelper(getApplicationContext());
                UserRequest userRequest = new UserRequest();
                userRequest.setName(name.getText().toString());
                userRequest.setContact(contact.getText().toString());
                userRequest.setBloodgroup(selectedBloodGroup);
                userRequest.setCity(selectedCity);

                if (requestHelper.insertRequestdata(userRequest)) {
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    try {
                        startActivity(intent);
                    }
                    catch (Exception e) {
                    }
                    finish();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Error!");
                    builder.setMessage("Can't make Request!");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.spinnerBlood)
        {
            selectedBloodGroup = parent.getItemAtPosition(position).toString();
//            Toast.makeText(RequestPost.this,""+selectedBloodGroup,Toast.LENGTH_SHORT).show();
        }
        else if(parent.getId()==R.id.spinnerCity)
        {
            selectedCity= parent.getItemAtPosition(position).toString();
//            Toast.makeText(RequestPost.this,""+selectedCity,Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}