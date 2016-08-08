package kante.jofoo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by moh on 8/8/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/*.xml")
@ActiveProfiles("test")
public class HashKeyTest
{
    @Autowired
    Jedis jedisCon;

    DocumentKey doc;

    static class DocumentKey extends HashKey {

        {
            key = "/test/doc:?";
        }
        DocumentKey(Jedis con) {
            super(con);
        }
    }

    @Before
    public void before() {
        doc = new DocumentKey(jedisCon);
        doc.setKeyVars(200);
        doc.del();
    }

    @Test
    public void getterAndSetter() {

        assertTrue(!doc.exists());
        doc.hset("name", "unit_testing");
        doc.hset("date", "2016/07/08");
        doc.hset("page", 1129);

        assertEquals(doc.hget("name"), "unit_testing");
        assertEquals(doc.hget("date"), "2016/07/08");
        assertTrue(doc.hgetInt("page") == 1129);

        doc.hset("meta", "{\"type\":\"PDF\"}");
        Map<String,Object> meta = doc.hgetObject("meta", Map.class);
        assertEquals(meta.get("type"), "PDF");

        assertTrue(!doc.hexists("time"));
    }

    @Test
    public void incrBy() {

        doc.hset("page", 22);
        assertTrue(doc.hgetInt("page") == 22);

        doc.hincrBy("page", 10L);
        assertTrue(doc.hgetInt("page") == 32);
    }

    @Test
    public void keysAndVals() {

        doc.hset("name", "unit_testing");
        doc.hset("date", "2016/07/08");
        doc.hset("page", 19);
        // Keys
        Set<String> keys = doc.hkeys();
        assertTrue(keys.size() == 3);
        assertTrue(keys.contains("name"));
        assertTrue(keys.contains("date"));
        assertTrue(keys.contains("page"));
        // Values
        List<String> vals = doc.hvals();
        assertTrue(vals.size() == 3);
        assertTrue(vals.contains("unit_testing"));
        assertTrue(vals.contains("2016/07/08"));
        assertTrue(vals.contains("19"));
    }

    @Test
    public void hdel() {

        assertTrue(!doc.hexists("name"));
        doc.hset("name", "fly_mouse");
        assertTrue(doc.hexists("name"));
        doc.hdel("name");
        assertTrue(!doc.hexists("name"));
    }
}
