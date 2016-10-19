package com.example.hp.storyapp;

import android.graphics.Bitmap;

/**
 * Created by HP on 10/15/2016.
 */
public class Story {
    String name;
    String type;
    String deccription;
    String image;




    public String getName () {
         return name;
     }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeccription() {
        return deccription;
    }

    public void setDeccription(String deccription) {
        this.deccription = deccription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    @Override
    public String toString() {
        return name;
    }
}
