package com.github.serializer;

/**
 * Created by bs.yang on 11/27/2018.
 */
public interface BaseSerializer {

    /**
     * 序列化
     *
     * @param obj
     * @param <T>
     * @return
     */
    <T> String serialize(T obj);

    /**
     * 反序列化
     * @param data
     * @param clazz
     * @param
     * @return
     */
    Object deserialize(String data, Class clazz);
}
