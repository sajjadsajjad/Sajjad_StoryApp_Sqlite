package com.example.hp.storyapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ThreadFactory;

public class StoryActivity extends AppCompatActivity {

    EditText text1, text2, text3;
    Button btn;
    private static int RESULT_LOAD_IMAGE = 1;
    private ArrayList storyList;
    SQLiteDatabase database;
    ImageView imageView;
    String encodeImage;
    DBOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        helper = new DBOpenHelper(this);
        text1 = (EditText) findViewById(R.id.edit_1);
        text2 = (EditText) findViewById(R.id.edit_2);
        text3 = (EditText) findViewById(R.id.edit_3);
        btn = (Button) findViewById(R.id.btn_browse);

        storyList = new ArrayList();

    }


    public void onClickListener1(View view) {

        String name = text1.getText().toString();
        String type = text2.getText().toString();
        String description = text3.getText().toString();
        String image = encodeImage;


        Story story5 = new Story();
        story5.setName(name);
        story5.setType(type);
        story5.setDeccription(description);
        story5.setImage(image);

        storyList.add(story5);
        if(helper.insertStoryDetail(name,type,description,image)){
            Toast.makeText(StoryActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(StoryActivity.this,"not Inserted",Toast.LENGTH_SHORT).show();
        }



    }









//    private void insertStoryDetail(Story story) {
//        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
//
//        database = dbOpenHelper.getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(DBOpenHelper.COLUMN_NAME3, story.getName());
//        contentValues.put(DBOpenHelper.COLUMN_TYPE3, story.getType());
//        contentValues.put(DBOpenHelper.COLUMN_DESC3, story.getDeccription());
//        contentValues.put(DBOpenHelper.COLUMN_IMAGE3, story.getImage());
//
//        long result = database.insert(DBOpenHelper.TABLE_NAME3, null, contentValues);
//
//        database.close();
//
//        Toast.makeText(this, "data has been inserted" + result, Toast.LENGTH_LONG).show();
//
//    }


    public void browsePic(View view) {

        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULT_LOAD_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            imageView = (ImageView) findViewById(R.id.imageView2);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            TextView textView = (TextView)findViewById(R.id.textView3);
            textView.setText(picturePath);

            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            ByteArrayOutputStream baos=new  ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte [] b= baos.toByteArray();
            encodeImage= Base64.encodeToString(b, Base64.DEFAULT);



            cursor.close();

        }
    }


    }

