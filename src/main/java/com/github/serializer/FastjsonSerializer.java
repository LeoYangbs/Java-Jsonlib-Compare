package com.github.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by bs.yang on 11/27/2018.
 */
@SuppressWarnings("all")
public class FastjsonSerializer implements BaseSerializer{

    @Override
    public <T> String serialize(T obj) {
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        return JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
    }

    @Override
    public Object deserialize(String data, Class clazz) {
        return JSON.parseArray(data, clazz);
    }
}
