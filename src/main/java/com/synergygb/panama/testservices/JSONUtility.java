package com.synergygb.panama.testservices;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

/**
 *
 * @author Juan Garcia <juan.garcia@synergy-gb.com>
 */
public class JSONUtility {

    static public String jsonFromObject(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
    
    static public Object objectFromJsom(String str, Class c) throws JsonParseException {
        Gson gson = new Gson();
        return gson.fromJson(str, c);
    }
    
    static public Object objectFromJsom(String str, Type c) throws JsonParseException {
        Gson gson = new Gson();
        return gson.fromJson(str, c);
    }
}
