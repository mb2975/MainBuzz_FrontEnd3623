package com.example.themainbuzzlogin2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class FriendsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Get rid of Action bar and title, to my knowledge, this is the only way to do so and you must do it in every activity - JG
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        final Button FrBtBack = (Button) findViewById(R.id.FrBtBack);



        FrBtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserAreaActivity.class);
                startActivity(intent);

            }
        });
    }
}