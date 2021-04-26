package com.example.project_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Animation tAnimation,bAnimation;
    ImageView imageView;
  TextView text1;
Button signIn,signUp;
CheckBox cb1,cb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tAnimation= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bAnimation=AnimationUtils.loadAnimation(this,R.anim.buttom);


        imageView=findViewById(R.id.imageView);
signIn=findViewById(R.id.signIn);
signUp=findViewById(R.id.signUp);
text1=findViewById(R.id.com);
        imageView.setAnimation(tAnimation);

signIn.setAnimation(bAnimation);
signUp.setAnimation(bAnimation);
        text1.setAnimation(bAnimation);
signIn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(MainActivity.this,sign_in.class);
        startActivity(intent);
    }
});
signUp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

Intent intent=new Intent(MainActivity.this,sign_up_page.class);
startActivity(intent);


    }
});




            }
}