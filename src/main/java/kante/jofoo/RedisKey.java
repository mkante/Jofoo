package kante.jofoo;

import kante.jofoo.util.Json;
import kante.jofoo.util.Jsonnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by moh on 8/6/16.
 */
public abstract class RedisKey
{
    protected String key = "jofoo";
    protected Logger log;
    private Map<String,Object> keyVars;
    private Jedis jediCon;

    public RedisKey(Jedis con) {

        if (con == null) {
            throw new JedisConnectionException("Jedis connection can't be null");
        }

        log = LoggerFactory.getLogger(this.getClass());
        jediCon = con;
        keyVars = new LinkedHashMap();
    }

    public RedisKey(Jedis con, String key) {
        this(con);
        this.key = key;
    }

    public final Jedis getJedis() {
        //jediCon.select(database);
        return jediCon;
    }

    public Long del() {
        return getJedis().del(key);
    }

    public String getKey() {
        return key;
    }

    public RedisKey setKeyVars(Map<String,Object> params) {

        keyVars = params;
        for (Map.Entry<String,Object> e: params.entrySet()) {
            String paramName = e.getKey();
            String paramVal = e.getValue()+"";
            key = key.replace("{"+paramName+"}", paramVal);
        }
        return this;
    }

    public void setKeyVars(Object... list) {
        setKeyVars(Arrays.asList(list));
    }


    public void setKeyVars(List list) {

        for (Object e: list) {
            key = key.replaceFirst("\\?", e+"");
        }
    }

    public boolean exists() {
        return getJedis().exists(key) ;
    }

    public ScanResult<String> scan(String cursor) {
        return getJedis().scan(cursor) ;
    }

    public void expire(int seconds) {
        jediCon.expire(key, seconds) ;
    }

    public Long ttl() {
        return jediCon.ttl(key) ;
    }

    protected Integer parseInt(String val) {
        return Integer.valueOf(val);
    }

    protected Double parseDouble(String val) {
        return Double.valueOf(val);
    }

    protected Float parseFloat(String val) {
        return Float.valueOf(val);
    }

    protected Long parseLong(String val) {
        return Long.valueOf(val);
    }

    protected BigDecimal parseBigDecimal(String val) {
        return BigDecimal.valueOf(parseDouble(val));
    }

    static protected <T> List<T> parseJsonArray(List<String> list, Class<T> clzz) {
        List objs= new ArrayList();
        for (String json: list) {
            objs.add(Json.parse(json, clzz));
        }

        return objs;
    }

    static protected String[] arrayObjectToString(Object... obj) {
        String[] strArray = new String[obj.length];

        for (int i=0; i < obj.length;i++) {
            strArray[i] = obj[i]+"";
        }
        return strArray;
    }

    static protected String[] jsonnableObjectToString(Jsonnable... obj) {
        String[] strArray = new String[obj.length];

        for (int i=0; i < obj.length;i++) {
            strArray[i] = obj[i].toJson();
        }
        return strArray;
    }
}
