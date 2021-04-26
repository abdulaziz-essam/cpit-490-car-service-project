package com.example.project_interface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class appList extends AppCompatActivity {
Button pre , home;
ListView currList;
    String app[];
    String time,company,email,app_id;
DatabaseReference databaseReference;
    DataSnapshot snapshot;
  private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        company = intent.getStringExtra("company");

        time = intent.getStringExtra("time");
        app_id = intent.getStringExtra("id");
Toast.makeText(this,"take screenshot to your apointment",Toast.LENGTH_SHORT).show(); ;
        mDatabase = FirebaseDatabase.getInstance().getReference("database").child("appointment");
        final appointment_info appointment = new appointment_info(email, company, time, app_id);
        mDatabase.push().setValue(appointment);
        int dateDay=(int) Math.random()*31+5;
        String d=dateDay+"";
final String date=d+"/12/2020";

home=findViewById(R.id.button5);
        pre = findViewById(R.id.pre);

        currList = (ListView) findViewById(R.id.list);
        app = new String[]{"1", "2", "3"};
        final String[] player = {appointment.getId(), appointment.getEmail(), appointment.getCompany(), appointment.getTime(),date};


//        appointment.getId().toString(),appointment.getCompany(),appointment.getTime()
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new previous();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment).commit();

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(appList.this, android.R.layout.select_dialog_item, player);
                currList.setAdapter(arrayAdapter);


            }
        });

home.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent1=new Intent(appList.this, com.example.project_interface.home.class);
                intent1.putExtra("username",appointment.getUsername());
        intent1.putExtra("company",appointment.getCompany());
        intent1.putExtra("id",appointment.getId());
        intent1.putExtra("time",appointment.getTime());
        intent1.putExtra("date",date);
        startActivity(intent1);


    }
});

    }


}