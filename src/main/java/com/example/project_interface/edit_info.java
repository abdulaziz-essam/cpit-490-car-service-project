package com.example.project_interface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.database.sqlite.SQLiteBindOrColumnIndexOutOfRangeException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.SQLOutput;

public class edit_info extends AppCompatActivity {
    Button button;
    FirebaseAuth mAuth;
    FirebaseUser users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        button=findViewById(R.id.confirmBT);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(edit_info.this,sign_in.class);
                Intent k=getIntent();
                String key=k.getStringExtra("key");

                EditText email=(EditText) findViewById(R.id.email);
                EditText pass=(EditText) findViewById(R.id.password);
                EditText oldemail=(EditText) findViewById(R.id.oldEmail);
                EditText oldpass=(EditText) findViewById(R.id.oldpassword);
                String pas,em,oem,opas;

                em=email.getText().toString();
                pas= pass.getText().toString();
                oem = oldemail.getText().toString();
                opas = oldpass.getText().toString();


                mAuth = FirebaseAuth.getInstance();

                mAuth.signInWithEmailAndPassword(oem,opas);
                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                user.delete();


                mAuth.createUserWithEmailAndPassword(em,pas);



                //   }else {



                //    }



                startActivity(intent);
                Toast.makeText(edit_info.this,"Your Information Has been Updated",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void updateData(Module module,String key){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference MYREF=database.getReference("database");
        MYREF.child("users").child(key).setValue(module)

                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });

    }
}