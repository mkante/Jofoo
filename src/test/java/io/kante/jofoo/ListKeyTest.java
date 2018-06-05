package io.kante.jofoo;

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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by moh on 8/8/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration("classpath*:spring/*.xml")
public class ListKeyTest
{

    @Autowired
    Jedis jedisCon;

    Album lsk;

    static class Album extends ListKey {
        {
            key = "/test/top5/albums";
        }
        Album(Jedis con) {
            super(con);
        }
    }

    @Before
    public void before() {
        lsk = new Album(jedisCon);
        lsk.del();
    }

    @Test
    public void lpush() {

        lsk.lpush("wu-tang");
        lsk.lpush("all_eyes_on_me");
        assertTrue(lsk.llen() == 2);
        assertEquals(lsk.lindex(0), "all_eyes_on_me");
        assertEquals(lsk.lindex(1), "wu-tang");

        lsk.lpush("{\"album\":\"donkey\"}");
        assertEquals(lsk.lindexObject(0, Map.class).get("album"), "donkey");
    }

    @Test
    public void rpush() {

        lsk.rpush("wu-tang");
        lsk.rpush("all_eyes_on_me");
        assertTrue(lsk.llen() == 2);
        assertEquals(lsk.lindex(0), "wu-tang");
        assertEquals(lsk.lindex(1), "all_eyes_on_me");

    }


    @Test
    public void lpop() {

        lsk.rpush(10);
        lsk.rpush(22);
        assertTrue(lsk.llen() == 2);
        assertTrue(lsk.lpop().equals("10"));
        assertTrue(lsk.lpop().equals("22"));

        assertTrue(lsk.llen() == 0);
        lsk.rpush(9);
        lsk.rpush(82);
        assertTrue(lsk.lpopInt() == 9);
        assertTrue(lsk.lpopInt() == 82);
    }

    @Test
    public void lrange() {

        lsk.rpush(10);
        lsk.rpush(22);
        lsk.rpush("something");
        assertTrue(lsk.llen() == 3);

        List<String> lst = lsk.lrange(1, 2);
        assertTrue(lst.size() == 2);
        assertTrue(lst.contains("22"));
        assertTrue(lst.contains("something"));
    }

    @Test
    public void lrem() {

        lsk.rpush(101);
        lsk.rpush("something");
        lsk.rpush(29.9);
        lsk.rpush("something");

        assertTrue(lsk.llen() == 4);
        lsk.lrem(1, "more");
        assertTrue(lsk.llen() == 4);
        lsk.lrem(1, "something");

        assertTrue(lsk.lindex(1).equals("29.9"));
        assertTrue(lsk.lindex(2).equals("something"));
    }

    @Test
    public void lset() {

        lsk.rpush(101);
        lsk.rpush(29.9);
        lsk.rpush("something");
        lsk.lset(-1, "ok");

        assertTrue(lsk.llen() == 3);
        assertTrue(lsk.lindexInt(0) == 101);
        assertTrue(lsk.lindexDouble(1) == 29.9);
        assertTrue(lsk.lindex(2).equals("ok"));
    }

}
