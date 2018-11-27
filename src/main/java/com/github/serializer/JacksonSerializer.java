package com.github.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.List;

/**
 * Created by bs.yang on 11/27/2018.
 */
public class JacksonSerializer implements BaseSerializer {

    private static ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> String serialize(T obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Can't-deserialize object", e);
        }
    }

    @Override
    public Object deserialize(String data, Class clazz) {
        try {
            CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return mapper.readValue(data, type);
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't-deserialize object", e);
        }
    }

}
