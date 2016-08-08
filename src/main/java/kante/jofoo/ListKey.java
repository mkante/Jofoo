package kante.jofoo;

import kante.jofoo.util.Json;
import kante.jofoo.util.Jsonnable;
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

    public Long lpush(Object... values) {

        return getJedis().lpush(key, arrayObjectToString(values));
    }

    public Long lpush(Jsonnable... values) {

        return getJedis().lpush(key, jsonnableObjectToString(values));
    }

    public Long rpush(Object... values) {

        return getJedis().rpush(key, arrayObjectToString(values));
    }

    public Long rpush(Jsonnable... values) {

        return getJedis().rpush(key, jsonnableObjectToString(values));
    }

    public Long rpushx(Object... values) {

        return getJedis().rpushx(key, arrayObjectToString(values));
    }

    public Long rpushx(Jsonnable... values) {

        return getJedis().rpushx(key, jsonnableObjectToString(values));
    }

    public Long lpushx(Object... values) {

        return getJedis().lpushx(key, arrayObjectToString(values));
    }

    public Long lpushx(Jsonnable... values) {

        return getJedis().lpushx(key, jsonnableObjectToString(values));
    }

    public String lindex(Long idx) {
        return getJedis().lindex(key, idx);
    }

    public Integer lindexInt(Long idx) {
        return parseInt(lindex(idx));
    }

    public Float lindexFloat(Long idx) {
        return parseFloat(lindex(idx));
    }

    public Double lindexDouble(Long idx) {
        return parseDouble(lindex(idx));
    }

    public BigDecimal lindexBigDecimal(Long idx) {
        return parseBigDecimal(lindex(idx));
    }

    public <T> T lindexObject(Long idx, Class<T> clzz) {
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
        return parseInt(lpop());
    }

    public Float lpopFloat() {
        return parseFloat(lpop());
    }

    public Double lpopDouble() {
        return parseDouble(lpop());
    }

    public BigDecimal lpopBigDecimal() {
        return parseBigDecimal(lpop());
    }

    public <T> T lpopObject(Class<T> clzz) {
        return Json.parse(lpop(), clzz);
    }

    public List<String> lrange(Long start, Long end) {
        return getJedis().lrange(key, start, end);
    }

    public <T> List<T> lrangeObject(Long start, Long end, Class<T> clzz) {
        List<String> jsons = lrange(start, end);
        return parseJsonArray(jsons, clzz) ;
    }

    public Long lrem (Long count, Object val) {
        return getJedis().lrem(key, count, val+"");
    }

    public Long lrem (Long count, Jsonnable val) {
        return getJedis().lrem(key, count, val.toJson());
    }

    public String lset (Long index, Object val) {
        return getJedis().lset(key, index, val+"");
    }

    public String lset (Long index, Jsonnable val) {
        return getJedis().lset(key, index, val.toJson());
    }

    public String ltrim(Long start, Long end) {
        return getJedis().ltrim(key, start, end);
    }

    public String rpop() {
        return getJedis().rpop(key);
    }

    public Integer rpopInt() {
        return parseInt(rpop());
    }

    public Double rpopDouble() {
        return parseDouble(rpop());
    }

    public BigDecimal rpopBigDecimal() {
        return parseBigDecimal(rpop());
    }

    public <T> T rpopObject(Class<T> clzz) {
        return Json.parse(rpop(), clzz);
    }
}
