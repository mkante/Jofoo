package io.kante.jofoo;

import io.kante.jofoo.util.ArrayHelpers;
import io.kante.jofoo.util.Json;
import io.kante.jofoo.util.NumberHelpers;
import io.kante.jofoo.util.Jsonnable;
import static redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by moh on 8/8/16.
 */
public class ListKey extends RedisKey
{

    public ListKey(Jedis con) {
        super(con);
    }

    public ListKey(Jedis con, String key) {
        super(con, key);
    }

    public Long lpush(Object... values) {

        return getJedis().lpush(key, ArrayHelpers.arrayObjectToString(values));
    }

    public Long lpush(Jsonnable... values) {

        return getJedis().lpush(key, ArrayHelpers.jsonnableObjectToString(values));
    }

    public Long rpush(Object... values) {

        return getJedis().rpush(key, ArrayHelpers.arrayObjectToString(values));
    }

    public Long rpush(Jsonnable... values) {

        return getJedis().rpush(key, ArrayHelpers.jsonnableObjectToString(values));
    }

    public Long rpushx(Object... values) {

        return getJedis().rpushx(key, ArrayHelpers.arrayObjectToString(values));
    }

    public Long rpushx(Jsonnable... values) {

        return getJedis().rpushx(key, ArrayHelpers.jsonnableObjectToString(values));
    }

    public Long lpushx(Object... values) {

        return getJedis().lpushx(key, ArrayHelpers.arrayObjectToString(values));
    }

    public Long lpushx(Jsonnable... values) {

        return getJedis().lpushx(key, ArrayHelpers.jsonnableObjectToString(values));
    }

    public String lindex(long idx) {
        return getJedis().lindex(key, idx);
    }

    public Integer lindexInt(long idx) {
        return NumberHelpers.parseInt(lindex(idx));
    }

    public Float lindexFloat(long idx) {
        return NumberHelpers.parseFloat(lindex(idx));
    }

    public Double lindexDouble(long idx) {
        return NumberHelpers.parseDouble(lindex(idx));
    }

    public BigDecimal lindexBigDecimal(long idx) {
        return NumberHelpers.parseBigDecimal(lindex(idx));
    }

    public <T> T lindexObject(long idx, Class<T> clzz) {
        return Json.parse(lindex(idx), clzz);
    }

    public Long linsert(LIST_POSITION pos, Object val1, Object val2) {
        return getJedis().linsert(key, pos, val1+"", val2+"");
    }

    public Long llen() {
        return getJedis().llen(key);
    }

    public String lpop() {
        return getJedis().lpop(key);
    }

    public Integer lpopInt() {
        return NumberHelpers.parseInt(lpop());
    }

    public Float lpopFloat() {
        return NumberHelpers.parseFloat(lpop());
    }

    public Double lpopDouble() {
        return NumberHelpers.parseDouble(lpop());
    }

    public BigDecimal lpopBigDecimal() {
        return NumberHelpers.parseBigDecimal(lpop());
    }

    public <T> T lpopObject(Class<T> clzz) {
        return Json.parse(lpop(), clzz);
    }

    public List<String> lrange(long start, long end) {
        return getJedis().lrange(key, start, end);
    }

    public <T> List<T> lrangeObject(long start, long end, Class<T> clzz) {
        List<String> jsons = lrange(start, end);
        return Json.parseList(jsons, clzz) ;
    }

    public Long lrem (long count, Object val) {
        return getJedis().lrem(key, count, val+"");
    }

    public Long lrem (long count, Jsonnable val) {
        return getJedis().lrem(key, count, val.toJson());
    }

    public String lset (long index, Object val) {
        return getJedis().lset(key, index, val+"");
    }

    public String lset (long index, Jsonnable val) {
        return getJedis().lset(key, index, val.toJson());
    }

    public String ltrim(long start, Long end) {
        return getJedis().ltrim(key, start, end);
    }

    public String rpop() {
        return getJedis().rpop(key);
    }

    public Integer rpopInt() {
        return NumberHelpers.parseInt(rpop());
    }

    public Double rpopDouble() {
        return NumberHelpers.parseDouble(rpop());
    }

    public BigDecimal rpopBigDecimal() {
        return NumberHelpers.parseBigDecimal(rpop());
    }

    public <T> T rpopObject(Class<T> clzz) {
        return Json.parse(rpop(), clzz);
    }
}
