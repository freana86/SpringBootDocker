package com.example.pl.demopl.Utils;


import com.fasterxml.jackson.databind.ObjectMapper;



public class JsonConverter {

    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> T convert(String json, Class<T> clazz) throws Exception {
        return mapper.readValue(json, clazz);
    }

    public static String convert(Object object) throws Exception {
        return mapper.writeValueAsString(object);
    }

}
