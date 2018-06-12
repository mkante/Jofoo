package io.kante.jofoo;

import io.kante.jofoo.util.NumberHelpers;
import io.kante.jofoo.util.Json;
import io.kante.jofoo.util.Jsonnable;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by moh on 8/6/16.
 */
public class StringKey extends RedisKey
{

    public StringKey(Jedis con) {
        super(con);
    }

    public StringKey(Jedis con, String key) {
        super(con, key);
    }

    public void set(Object value) {
        getJedis().set(key, value+"");
    }

    public void set(Jsonnable value) {
        set(value.toJson()) ;
    }

    public String get() {
        return getJedis().get(key);
    }

    public Integer getInt() {
        return NumberHelpers.parseInt(get());
    }

    public Double getDouble() {
        return NumberHelpers.parseDouble(get());
    }

    public Float getFloat() {
        return NumberHelpers.parseFloat(get());
    }

    public BigDecimal getBigDecimal() {
        return NumberHelpers.parseBigDecimal(get());
    }

    public <T> T getObject(Class<T> clzz) {
        String data = get();
        return Json.parse(data, clzz);
    }

    public Long incr() {
        return getJedis().incr(key);
    }

    public Long incrBy(int val) {
        return getJedis().incrBy(key, val);
    }

    public Long decr() {
        return getJedis().decr(key);
    }

    public Long decrBy(int val) {
        return getJedis().decrBy(key, val);
    }

    public Double incrByFloat(double val) {
        return getJedis().incrByFloat(key, val);
    }

    public Long strlen() {
        return getJedis().strlen(key);
    }

    public Long append(Object str) {
        return getJedis().append(key, str+"");
    }

    public Long append(Jsonnable str) {
        return getJedis().append(key, str.toJson());
    }

    public Long bitcount() {
        return getJedis().bitcount(key);
    }

    public List<Long> bitfield(String...arguments){
        return getJedis().bitfield(key, arguments);
    }

    public Long setrange(long offset, String value) {
        return getJedis().setrange(key, offset, value);
    }

    public Long setnx(Object value) {
        return getJedis().setnx(key, value+"");
    }

    public Long setnx(Jsonnable value) {
        return getJedis().setnx(key, value.toJson());
    }

    public String setex(int expire, Object value) {
        return getJedis().setex(key, expire, value+"");
    }

    public String setex(int expire, Jsonnable value) {
        return getJedis().setex(key, expire, value.toJson());
    }

    public String psetex(int expire, Object value) {
        return getJedis().psetex(key, expire, value+"");
    }

    public String psetex(final int expire, final Jsonnable value) {
        return getJedis().psetex(key, expire, value.toJson());
    }

    public Boolean setbit(long offset, Object value) {
        return getJedis().setbit(key, offset, value+"");
    }

    public Boolean setbit(long offset, Jsonnable value) {
        return getJedis().setbit(key, offset, value.toJson());
    }
}
