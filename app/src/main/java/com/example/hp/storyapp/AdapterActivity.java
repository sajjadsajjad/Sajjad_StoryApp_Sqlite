package com.example.hp.storyapp;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class AdapterActivity extends Activity {

public static final String LOG_TAG = "EXPLORECA";

public static final String STORY_NAME = "name";

    public static final String STORY_TYPE = "type";

    public static final String STORY_DESC = "description";

    public static final String STORY_IMAGE = "image";

    public static final int DETAIL_REQUEST_CODE = 1001;





    DBOpenHelper dbOpenHelper;

    List<Story> storyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);


        dbOpenHelper = new DBOpenHelper(this);

        storyList = dbOpenHelper.findAll2();
        ArrayAdapter<Story> courseArrayAdapter = new CursorListAdapter(this,R.layout.storyitemdetail,storyList);
        ListView listView = (ListView)findViewById(R.id.l);
        listView.setAdapter(courseArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Story story = storyList.get(position);
                displayDetail(story);
            }
        });


    }

    private void displayDetail(Story story) {
        Intent intent = new Intent(this, FinalActivity.class);

        intent.putExtra(STORY_NAME, story.getName());
        intent.putExtra(STORY_TYPE, story.getType());
        intent.putExtra(STORY_DESC, story.getDeccription());
        intent.putExtra(STORY_IMAGE,story.getImage());
        startActivityForResult(intent, DETAIL_REQUEST_CODE);


    }


    class CursorListAdapter extends ArrayAdapter<Story> {

        Context context;
        List<Story> story;


        public CursorListAdapter(Context context, int resource, List<Story> story) {
            super(context, resource, story);
            this.context = context;
            this.story = story;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutinflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View view = layoutinflater.inflate(R.layout.storyitemdetail, null);
            Story story1= storyList.get(position);

            TextView textView1 = (TextView)view.findViewById(R.id.textView5);
            textView1.setText(story1.getName());
            TextView textView2 = (TextView)view.findViewById(R.id.textView6);
            textView2.setText(story1.getType());

            String image = story1.getImage();
            Log.d("image",image);


            byte[] imageAsBytes = Base64.decode(image.getBytes(), Base64.DEFAULT);
            ImageView iv = (ImageView)view.findViewById(R.id.image_story);
            iv.setImageBitmap(
                    BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)
            );



            return view;
        }

    }

}
