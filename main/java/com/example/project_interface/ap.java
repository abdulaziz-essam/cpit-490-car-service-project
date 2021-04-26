package com.example.project_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class ap extends AppCompatActivity {
Button button;
CheckBox c1,c2,c3,c4;
    String c;
    String user,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ap);
        button=findViewById(R.id.con);
        c1=(CheckBox) findViewById(R.id.ch1);
        c2=(CheckBox) findViewById(R.id.c2);
        c3=(CheckBox) findViewById(R.id.ch3);
        c4=(CheckBox) findViewById(R.id.checkBox4);
Intent intent=getIntent();
user=intent.getStringExtra("username");
email=intent.getStringExtra("email");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             c1=(CheckBox) findViewById(R.id.ch1);
               c2=(CheckBox) findViewById(R.id.c2);
                c3=(CheckBox) findViewById(R.id.ch3);
                c4=(CheckBox) findViewById(R.id.checkBox4);


                 if((c1.isChecked())){

                     c=c1.getText().toString();


                 }
               else if((c2.isChecked())){
                   c=c2.getText().toString();

                 }
           else if((c3.isChecked())){
                   c=c3.getText().toString();

                 }
                else if(c4.isChecked()){
                     c=c4.getText().toString();

                 }


                Intent intent=new Intent(ap.this,company.class);
                intent.putExtra("time",c);
                intent.putExtra("username",user);
                intent.putExtra("email",email);
                startActivity(intent);


            }
        });
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c2.setChecked(false);
                c3.setChecked(false);
                c4.setChecked(false);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c1.setChecked(false);
                c3.setChecked(false);
                c4.setChecked(false);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c2.setChecked(false);
                c1.setChecked(false);
                c4.setChecked(false);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c2.setChecked(false);
                c3.setChecked(false);
                c1.setChecked(false);
            }
        });
    }
}