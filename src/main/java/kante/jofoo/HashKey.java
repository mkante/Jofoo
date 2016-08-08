package kante.jofoo;

import kante.jofoo.RedisKey;
import kante.jofoo.util.Json;
import kante.jofoo.util.Jsonnable;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by moh on 8/8/16.
 */
public class HashKey extends RedisKey
{

    public HashKey(Jedis con) {
        super(con);
    }

    public Long hset(String field, Object val) {
        return getJedis().hset(key, field, val + "");
    }

    public Long hsetnx(String field, Object val) {
        return getJedis().hsetnx(key, field, val + "");
    }

    public String hmset(String field, Map map) {
        return getJedis().hmset(key, map);
    }

    public Long hset(String field, Jsonnable val) {
        return getJedis().hset(key, field, val.toJson());
    }

    public String hget(String field) {
        return getJedis().hget(key, field);
    }

    public Integer hgetInt(String field) {
        return parseInt(hget(field));
    }
    public Double hgetDouble(String field) {
        return parseDouble(hget(field));
    }

    public Float hgetFloat(String field) {
        return parseFloat(hget(field));
    }

    public BigDecimal hgetBigDecimal(String field) {
        return parseBigDecimal(hget(field));
    }

    public <T> T hgetObject(String field, Class<T> clzz) {
        String data = hget(field);
        return Json.parse(data, clzz);
    }

    public Long hdel(String field) {
        return getJedis().hdel(key, field);
    }

    public Boolean hexists(String field) {
        return getJedis().hexists(key, field);
    }

    public Map<String,String> hgetAll() {
        return getJedis().hgetAll(key);
    }

    public Long hincrBy(String field, Long val) {
        return getJedis().hincrBy(key, field, val);
    }

    public Double hincrByFloat(String field, double val) {
        return getJedis().hincrByFloat(key, field, val);
    }

    public Set<String> hkeys() {
        return getJedis().hkeys(key);
    }

    public Long hlen() {
        return getJedis().hlen(key);
    }

    public Long hstrlen() {
        return hlen();
    }

    public List<String> hvals() {
        return getJedis().hvals(key);
    }

    public List<Map> hvalsObject() {
        return hvalsObject(Map.class);
    }

    public<T> List<T> hvalsObject(Class<T> clzz) {

        List<T> list = new ArrayList();
        for (String json: hvals()) {
            list.add(Json.parse(json, clzz));
        }

        return list;
    }

    public List<String> hmget(String... fields) {
        return getJedis().hmget(key, fields);
    }

    public List<LinkedHashMap> hmgetObject(String... fields) {
        return hmgetObject(LinkedHashMap.class, fields);
    }

    public <T> List<T> hmgetObject(Class<T> clzz, String... fields) {

        List<String> data = getJedis().hmget(key, fields);
        List<T> rsData = new ArrayList();

        for (String item: data) {
            rsData.add(Json.parse(item, clzz));
        }
        return rsData;
    }
}
