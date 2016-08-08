package kante.jofoo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        log = LoggerFactory.getLogger(this.getClass());
        jediCon = con;
        keyVars = new LinkedHashMap();
    }

    public RedisKey(Jedis con, String key) {
        this(con);
        this.key = key;
    }

    public Jedis getJedis() {
        //jediCon.select(database);
        return jediCon;
    }

    public Long del() {
        return jediCon.del(key);
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
        return jediCon.exists(key) ;
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
}
