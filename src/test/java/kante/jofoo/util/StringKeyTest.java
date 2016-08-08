package kante.jofoo.util;

import kante.jofoo.StringKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

/**
 * Created by moh on 8/7/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/*.xml")
@ActiveProfiles("test")
public class StringKeyTest
{

    static class UserKey extends StringKey {
        {
            key = "/test/user";
            database = 1;
        }

        UserKey(Jedis con) {
            super(con);
        }
    }

    @Autowired
    protected Jedis db;

    @Test
    public void setterAndGetter() {
        //
        UserKey uk = new UserKey(db) ;
        uk.set("brandy") ;
        assertEquals(uk.get(), "brandy");
        //
        uk.set(200) ;
        assertEquals(uk.get(), "200");
        assertTrue(uk.getInt() == 200);
        assertTrue(uk.getDouble() == 200);
        //
        uk.set(20.90) ;
        assertTrue(uk.getDouble() == 20.90);
        //
        uk.set("{\"name\":\"who_is\", \"track\":19, \"artist\":\"Brandy\"}") ;
        Song song = uk.getObject(Song.class);
        assertEquals(song.name, "who_is");
        assertTrue(song.track == 19);
        assertEquals(song.artist, "Brandy");
    }

    @Test
    public void incrAndDecr() {

        UserKey uk = new UserKey(db) ;
        uk.set("20") ;
        assertTrue(uk.getInt() == 20);
        //
        uk.incr();
        uk.incr();
        assertTrue(uk.getInt() == 22);
        //
        uk.decr();
        assertTrue(uk.getInt() == 21);
        //
        uk.incrBy(3);
        assertTrue(uk.getInt() == 24);
        //
        uk.decrBy(9);
        assertTrue(uk.getInt() == 15);
    }

    @Test
    public void append() {

        UserKey uk = new UserKey(db) ;
        uk.set("almost") ;
        uk.append(" doesn't count");
        assertEquals(uk.get(), "almost doesn't count");
    }
}
