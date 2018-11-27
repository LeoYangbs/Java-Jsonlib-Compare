package com.github.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * Created by bs.yang on 11/27/2018.
 */
@SuppressWarnings("all")
public class GsonSerializer implements BaseSerializer {
    private static Gson gson;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        gson = gsonBuilder.create();
    }

    @Override
    public <T> String serialize(T obj){
        return gson.toJson(obj);
    }

    @Override
    public Object deserialize(String data, Class clazz) {
        return gson.fromJson(data, ArrayList.class);
    }
}
