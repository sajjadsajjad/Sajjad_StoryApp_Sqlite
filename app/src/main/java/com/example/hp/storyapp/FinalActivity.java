package com.example.hp.storyapp;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {

    protected String storyName;
    protected  String storyDescription;
    protected  String image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        storyName = getIntent().getStringExtra(AdapterActivity.STORY_NAME);
        TextView textView1 = (TextView)findViewById(R.id.textView4);
        textView1.setText(storyName);


        image = getIntent().getStringExtra(AdapterActivity.STORY_IMAGE);
        byte[] imageAsBytes = Base64.decode(image.getBytes(), Base64.DEFAULT);

        ImageView imageView1 = (ImageView)findViewById(R.id.imageView3);

        imageView1.setImageBitmap(
                BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)
        );



        storyDescription = getIntent().getStringExtra(AdapterActivity.STORY_DESC);
        TextView textView2 = (TextView)findViewById(R.id.final_tv);
        textView2.setText(storyDescription);







    }

}
