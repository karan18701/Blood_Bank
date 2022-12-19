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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.demo.entity.User;

public class Register extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    Button signIn,signUp;
    CheckBox checkBox;
    EditText name,contact,email,password;
    Spinner bloodgroup,city;
    RadioGroup gender;
    String selectedgender = "Male";
    String selectedBloodGroup,selectedCity;
    String[] cityList={"Surat","Ahemdabad","Rajkot","Gandhinagar"};
    String[] bloodGrouplist={"A+","A-","B+","B-","O+","O-","AB+","AB-"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.icon_img);
        getSupportActionBar().setTitle("  Sign Up");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        signIn=findViewById(R.id.signin);
        signUp=findViewById(R.id.signup);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        contact=findViewById(R.id.contact);
        password=findViewById(R.id.password);
        gender=findViewById(R.id.genderRadioGroup);
        bloodgroup=findViewById(R.id.bgroup);
        city=findViewById(R.id.city);
        checkBox=findViewById(R.id.checkBox);
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton)group.findViewById(checkedId);
                selectedgender=radioButton.getText().toString();
            }
        });

        ArrayAdapter bgadapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,bloodGrouplist);
        bloodgroup.setAdapter(bgadapter);

        ArrayAdapter cityadapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,cityList);
        city.setAdapter(cityadapter);
        bloodgroup.setOnItemSelectedListener(this);
        city.setOnItemSelectedListener(this);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Register.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    UserHelper userHelper = new UserHelper(getApplicationContext());
                    User user = new User();
                    user.setName(name.getText().toString());
                    user.setContact(contact.getText().toString());
                    user.setEmail(email.getText().toString());
                    user.setPassword(password.getText().toString());
                    user.setGender(selectedgender);
                    user.setBloodgroup(selectedBloodGroup);
                    user.setCity(selectedCity);
                    int x;
                    if(checkBox.isChecked())
                        x=1;
                    else
                        x=0;
                    user.setDonor(x);

                    User temp = userHelper.checkemail(email.getText().toString());
                    if (temp == null) {
                        if (userHelper.insertuserdata(user)) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            try {
                                startActivity(intent);
                            }
                            catch (Exception e) {
                            }
                            finish();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                            builder.setTitle("Error!");
                            builder.setMessage("Can't Create account!");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            builder.show();
                        }
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("Error!");
                        builder.setMessage("User Exists!");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();
                    }
                }
                catch (Exception e)
                {
                    AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Error!");
                    builder.setMessage(e.getMessage());
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