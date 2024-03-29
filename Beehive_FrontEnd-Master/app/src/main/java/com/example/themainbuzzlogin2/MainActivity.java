package com.example.themainbuzzlogin2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    DBHelper DB;
    //test

    static String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Get rid of Action bar and title, to my knowledge, this is the only way to do so and you must do it in every activity - JG
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB = new DBHelper(this);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})

        final EditText etUsername = (EditText) findViewById(R.id.etUsername1);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        final Button bLogin = (Button) findViewById(R.id.bLogin);

        final TextView registerLink = (TextView) findViewById(R.id.tRegister1);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUsername.getText().toString();
                String pass = etPassword.getText().toString();
                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
                    Toast.makeText(MainActivity.this, "All fields Required", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkUsernamePassword(user,pass);
                    if(checkuserpass == true){

                        currentUser = user;
                        Intent intent = new Intent(getApplicationContext(), UserAreaActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(registerIntent);

            }
        });
    }
}