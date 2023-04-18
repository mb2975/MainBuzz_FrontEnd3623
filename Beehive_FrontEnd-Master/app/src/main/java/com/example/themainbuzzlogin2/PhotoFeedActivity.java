package com.example.themainbuzzlogin2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PhotoFeedActivity extends AppCompatActivity {

//    In-Progress - clicking on image goes fullscreen - JG
//    ImageView imageView;
//
//    boolean isImageFitToScreen;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photofeed);

        //Get rid of Action bar and title, to my knowledge, this is the only way to do so and you must do it in every activity - JG
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

          RecyclerView postFeed = findViewById(R.id.PostRecycler);

        //Arraylist that will hold all of the post cards and display them in the recyclerview - JG
        ArrayList<PostModel> postModelArrayList = new ArrayList<PostModel>();
        //postModelArrayList.add(new PostModel("TestingAccount", "Testing Cards in RecyclerView", R.drawable.friends__my_hive_));
        postModelArrayList.add(new PostModel("ILikeTrees", "Take a picture of a tree", R.drawable.tree));
        postModelArrayList.add(new PostModel("Jag04460", "Take a picture of a Cat", R.drawable.mozzie));

        PostAdapter postAdapter = new PostAdapter(this, postModelArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        postFeed.setLayoutManager(linearLayoutManager);
        postFeed.setAdapter(postAdapter);


//        In-Progress - Click (touch) picture in post, fullscreen, then vice versa - JG
//
//        imageView = (ImageView) findViewById(R.id.Post_Picture);
//
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(isImageFitToScreen) {
//                    isImageFitToScreen = false;
//                    imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//                    imageView.setAdjustViewBounds(true);
//                }
//                else{
//                    isImageFitToScreen = true;
//                    imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                }
//            }
//        });

    }
}