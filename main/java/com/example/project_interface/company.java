package com.example.project_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class  company extends AppCompatActivity {
    Button button;
    CheckBox c1,c2,c3;
    TextView textView;
    String time;
    String c;
    String id;
    String user,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        textView=(TextView) findViewById(R.id.textView2);
        final Intent intent=getIntent();
        email=intent.getStringExtra("email");
        time=intent.getStringExtra("time");
        user=intent.getStringExtra("username");
        textView.setText("time : "+time);

        button=findViewById(R.id.button);
        c1=(CheckBox) findViewById(R.id.kia);
        c2=(CheckBox) findViewById(R.id.mazda);
        c3=(CheckBox) findViewById(R.id.ferrari);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          if(c1.isChecked()){
              c=c1.getText().toString();
              id="1";

          } else if (c2.isChecked()){
              c=c2.getText().toString();
              id="2";
          }
          else if (c3.isChecked()){
              c=c3.getText().toString();
              id="3";
          }

                Intent intent=new Intent(company.this,appList.class);
                 intent.putExtra("time",time);
                 intent.putExtra("company",c);
                 intent.putExtra("id",id);
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

            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c1.setChecked(false);
                c3.setChecked(false);

            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c2.setChecked(false);
                c1.setChecked(false);

            }
        });

            }

    }

