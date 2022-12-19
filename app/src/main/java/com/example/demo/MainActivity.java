package com.example.demo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo.entity.User;

public class MainActivity extends AppCompatActivity {
Button signIn,signUp;
EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.icon_img);
        getSupportActionBar().setTitle("  Blood Bank");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        signIn=findViewById(R.id.signin);
        signUp=findViewById(R.id.signup);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Register.class);
                startActivity(intent);
                finish();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserHelper userHelper=new UserHelper(getApplicationContext());
                String uemail=email.getText().toString();
                String upassword=password.getText().toString();
                if(uemail.equals("") || upassword.equals(""))
                {
                    Toast.makeText(MainActivity.this,"Enter Email and Password!",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                else
                {
                    User user=userHelper.login(uemail,upassword);
                    if(user==null)
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                        builder.setTitle("Error!");
                        builder.setMessage("Invalid account!");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();

                    }
                    else
                    {
                        Intent intent=new Intent(MainActivity.this,Home.class);
                        intent.putExtra("user",user);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}