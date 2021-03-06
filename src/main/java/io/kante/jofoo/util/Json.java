package io.kante.jofoo.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * Created by moh on 8/6/16.
 */
public abstract class Json
{
    protected static ObjectMapper mapper = new ObjectMapper();
    protected static Logger log = LoggerFactory.getLogger(Json.class);

    public static Map parse(String json){
        return parse(json, LinkedHashMap.class);
    }

    public static <T> T parse(String json, Class<T> clzz){
        log.debug("Parsing json: "+json);
        T obj= null;
        try {
            obj = mapper.readValue(json, clzz);
        }
        catch (IOException e){
            log.error(e.getMessage(), e);
        }

        return obj;
    }

    static public <T> List<T> parseList(Collection<String> list, Class<T> clzz) {
        List objs= new ArrayList();
        for (String json: list) {
            objs.add(parse(json, clzz));
        }

        return objs;
    }

    public static String stringify(Object obj){

        String json = null;
        try {
            json = mapper.writeValueAsString(obj);
        }
        catch (JsonProcessingException e){
            log.error(e.getMessage(), e);
        }
        return json;
    }

    public static String prettyPrint(Object obj) {

        String json = null;
        try {
            json =
                    mapper.writerWithDefaultPrettyPrinter()
                            .writeValueAsString(obj);
        }
        catch (JsonProcessingException e){
            log.error(e.getMessage(), e);
        }
        return json;
    }

}
