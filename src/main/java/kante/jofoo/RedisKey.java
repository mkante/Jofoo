package kante.jofoo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by moh on 8/6/16.
 */
public class RedisKey
{
    protected String key = "jofoo";
    protected int database = 0;
    protected Logger log;
    protected Map<String,Object> keyVars;
    private Jedis jediCon;

    public RedisKey(Jedis con) {
        log = LoggerFactory.getLogger(this.getClass());
        jediCon = con;
        keyVars = new LinkedHashMap();
    }

    public Jedis getJedis() {
        jediCon.select(database);
        return jediCon;
    }

    public int getDatabase() {
        return database;
    }

    public Long del() {
        return jediCon.del(key);
    }

    public void setKey(String val) {
        key = val;
    }

    public String getKey() {
        return key;
    }

    public void setKeyVar(String key, Object value) {
        keyVars.put(key, value);
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
