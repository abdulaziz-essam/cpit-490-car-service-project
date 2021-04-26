package com.example.project_interface;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class home extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3,button4,button7;
    String email,pass ,date,id ,company,time ;
    TextView textView;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        button1=findViewById(R.id.button6);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        button7=findViewById(R.id.button7);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        pass = intent.getStringExtra("password");
        date=intent.getStringExtra("date");
        id=intent.getStringExtra("id");
        company=intent.getStringExtra("company");
        time=intent.getStringExtra("time");
        final String user = intent.getStringExtra("username");

        mDatabase = FirebaseDatabase.getInstance().getReference("database").child("users");
        Module module=new Module(user,email,pass);
        mDatabase.push().setValue(module);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this,contact.class);

                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this,edit_info.class);
                intent.putExtra("email",email);
                intent.putExtra("pass",pass);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this,eval.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home.this,ap.class);
                intent.putExtra("username",user);
                intent.putExtra("password",pass);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this,MapsActivity.class);
                startActivity(intent);

            }
        });

    }
}