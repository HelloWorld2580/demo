package com.dhcc.ms.ims.utils;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class ListJsonPrettyFormatSerializer extends JsonSerializer<List<?>> {

    @Override
    public void serialize(List<?> value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        /*
        * json格式化
        * {"name":"我的","val":12,"status":true,"f":10.01,"time":"2015-04-07 10:30:22"}
        * 在使用setPrettyPrinting之后，打印的json为
        * {
        *   "name": "我的",
        *   "val": 12,
        *   "status": true,
        *   "f": 10.01,
        *   "time": "2015-04-07 10:30:43"
        * }
        * */
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        jgen.writeString(gson.toJson(value));
    }
}
