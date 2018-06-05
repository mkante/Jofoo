package io.kante.jofoo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by moh on 8/8/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/*.xml")
@ActiveProfiles("test")
public class RedisKeyTest
{
    @Autowired
    Jedis con;

    static class A1Key extends RedisKey {
        {
            key = "/test/song:{id}/track:{tid}";
        }
        public A1Key(Jedis con) {
            super(con);
        }
    };

    static class A2Key extends RedisKey {
        {
            key = "/test/album:?/track:?";
        }
        public A2Key(Jedis con) {
            super(con);
        }
    };

    @Test
    public void keyVars() {

        RedisKey model= new A1Key(con);
        assertEquals(model.getKey(), "/test/song:{id}/track:{tid}");

        Map<String,Object> params = new LinkedHashMap();
        params.put("id", 1000);
        params.put("tid", 89);
        model.setKeyVars(params);
        assertEquals(model.getKey(), "/test/song:1000/track:89");
    }

    @Test
    public void keyVars2() {

        RedisKey model= new A2Key(con);
        assertEquals(model.getKey(), "/test/album:?/track:?");

        model.setKeyVars("views", 6);
        assertEquals(model.getKey(), "/test/album:views/track:6");
    }
}
