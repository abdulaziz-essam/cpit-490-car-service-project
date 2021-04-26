package com.example.project_interface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class sign_up_page extends AppCompatActivity {
EditText username,password,email;
    Button conBT;
    String em,pass,user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

         conBT= (Button) findViewById(R.id.confirmBT);
        mAuth = FirebaseAuth.getInstance();

         username=(EditText)findViewById(R.id.username);

        conBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                email=(EditText)findViewById(R.id.email);
                password=(EditText)findViewById(R.id.password);

                username=(EditText)findViewById(R.id.username);
               user=username.getText().toString().trim();
                em=email.getText().toString().trim();
                pass=password.getText().toString().trim();

          if(em.isEmpty()){
              email.setError("email is required");
              email.requestFocus();
          }
          if(pass.isEmpty()){
              password.setError("you should enter password");
              password.requestFocus();
          } else if(pass.length()<6) {
              password.setError("your password should be more than 5 latters or numbers ");
          }
if(!(pass.isEmpty()&&em.isEmpty())){
    mAuth.createUserWithEmailAndPassword(em,pass).addOnCompleteListener(sign_up_page.this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
             Intent intent=new Intent(sign_up_page.this,home.class);
           intent.putExtra("email",em);
                intent.putExtra("password",pass);
                intent.putExtra("username",user);
             startActivity(intent);
            }

        }
    });
}

if(!Patterns.EMAIL_ADDRESS.matcher(em).matches()){
    email.setError("wrong email");
    email.requestFocus();
}
                 }



        });



    }}
