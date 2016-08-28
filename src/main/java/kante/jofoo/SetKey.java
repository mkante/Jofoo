package kante.jofoo;

import kante.jofoo.util.Json;
import kante.jofoo.util.Jsonnable;
import redis.clients.jedis.Jedis;

import java.util.*;
import static kante.jofoo.util.ArrayHelpers.* ;

/**
 * Created by moh on 8/8/16.
 */
public class SetKey extends RedisKey
{
    public SetKey(Jedis con) {
        super(con);
    }

    public Long sadd(Object... objs) {
        return getJedis().sadd(key, arrayObjectToString(objs));
    }

    public Long sadd(Jsonnable... objs) {
        return getJedis().sadd(key, jsonnableObjectToString(objs));
    }

    public Long scard() {
        return getJedis().scard(key);
    }

    public Set<String> sdiff(String... objs) {
        return getJedis().sdiff(objs);
    }

    public Set<String> sdiff(SetKey... objs) {

        return getJedis().sdiff(collectKeys(objs));
    }

    public Set<String> sinter(String... objs) {
        return getJedis().sinter(objs);
    }

    public Set<String> sinter(SetKey... objs) {

        return getJedis().sinter(collectKeys(objs));
    }

    public boolean sismember(String value) {

        return getJedis().sismember(key, value);
    }

    public Set<String> smembers() {

        return getJedis().smembers(key);
    }

    public <T> Set<T> smembers(Class<T> clzz) {

        return new HashSet( Json.parseList(smembers(), clzz) );
    }

    public Long smove(String desKey, Object val) {

        return getJedis().smove(getKey(), desKey, val+"");
    }

    public Long smove(SetKey desKey, Object val) {

        return getJedis().smove(getKey(), desKey.getKey(), val+"");
    }

    public Set<String> sunion(Object... keys) {

        Object[] lstKey = merge(keys, getKey());

        return getJedis().sunion(arrayObjectToString(lstKey));
    }

    public Long srem(Object... vals) {
        return getJedis().srem(getKey(), arrayObjectToString(vals));
    }

    public Long srem(Jsonnable... vals) {
        return getJedis().srem(getKey(), jsonnableObjectToString(vals));
    }

    public String srandmember() {
        return getJedis().srandmember(getKey());
    }

    public <T> T srandmemberObject(Class<T> clzz) {
        return Json.parse(srandmember(), clzz);
    }

    /**
     * WARNING: SPOP Removes and returns one or more random
     * elements from the set value store at key.
     *
     * @return
     */
    public String spop() {
        return getJedis().spop(getKey());
    }

    public <T> T spopObject(Class<T> clzz) {
        return Json.parse(spop(), clzz);
    }

    public Set<String> spop(long count) {

        return getJedis().spop(getKey(), count);
    }

    public <T> Set<T> spopObject(long count, Class<T> clzz) {

        return new HashSet(Json.parseList(spop(count), clzz));
    }

    protected String[] collectKeys(SetKey... objs) {
        String[] keyLst = new String[objs.length];

        for (int i=0; i < objs.length; i++) {
            keyLst[i] = objs[i].getKey();
        }
        return keyLst;
    }
}
