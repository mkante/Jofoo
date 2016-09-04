package kante.jofoo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by moh on 8/8/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/*.xml")
@ActiveProfiles("test")
public class SetKeyTest
{

    Logger log  = LoggerFactory.getLogger(SetKeyTest.class);

    static class TokenKey extends SetKey {
        {
            key = "/test/sam/tokens";
        }

        TokenKey(Jedis con) {
            super(con);
        }
    }

    @Autowired
    protected Jedis db;

    private TokenKey tokens;

    @Before
    public void before() {
        tokens = new TokenKey(db);
        tokens.del();
        assertTrue(!tokens.exists());
    }

    @Test
    public void sadd() {

        tokens.sadd("token-1");
        tokens.sadd("token-2", "token-1");
        assertTrue(tokens.scard() == 2);

        assertTrue(tokens.sismember("token-1"));
        assertTrue(tokens.sismember("token-2"));
    }

    @Test
    public void spop() {

        // SADD is not sorted but it push new elements at the top
        tokens.sadd("token-1", "token-2");
        assertTrue(tokens.scard() == 2);
        log.info("Set list: "+tokens.smembers());

        tokens.spop();
        assertTrue(tokens.scard() == 1);
    }

    @Test
    public void smembers() {

        // SADD is not sorted but it push new elements at the end
        tokens.sadd("token-1", "token-2");
        assertTrue(tokens.scard() == 2);

        Set o = tokens.smembers();
        log.info("Set list: "+o);
        assertTrue(o.size() == 2);

        Object[] arrayA = o.toArray();
        assertEquals(arrayA[0], "token-1");
        assertEquals(arrayA[1], "token-2");
    }
}
