package com.example.roomdatabaseproject.utils;

import android.content.Context;

import java.io.InputStream;

public class Utils {

    public static String getAssetJsonData(Context context){
        String json;
        try{
            InputStream is = context.getAssets().open("menu.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return json;
    }
}
