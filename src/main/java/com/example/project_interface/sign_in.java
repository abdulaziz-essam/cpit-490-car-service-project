package com.example.project_interface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sign_in extends AppCompatActivity {
Button button;
    FirebaseAuth mAuth;
    String em,pass;
EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        button=findViewById(R.id.in);
        mAuth = FirebaseAuth.getInstance();

        password=findViewById(R.id.pass);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=(EditText) findViewById(R.id.em);
                password=(EditText) findViewById(R.id.pass);
                final String em=email.getText().toString();
final String pass=password.getText().toString();

                mAuth.signInWithEmailAndPassword(em, pass)
                        .addOnCompleteListener(sign_in.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                             Intent intent=new Intent(sign_in.this,home.class);
                             intent.putExtra("email",em);
                             intent.putExtra("password",pass);
                             startActivity(intent);
                                } else {


                                }


                            }


                        });
            }
        });

    }
}