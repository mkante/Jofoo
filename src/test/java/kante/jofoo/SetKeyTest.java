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

        tokens.sadd("token-1", "token-2");
        assertTrue(tokens.scard() == 2);

        assertTrue(tokens.spop().equals("token-1"));
        assertTrue(tokens.scard() == 1);
    }

    @Test
    public void smember() {

        tokens.sadd("token-2", "token-1");
        assertTrue(tokens.scard() == 2);

        Set o = tokens.smember();
        assertTrue(o.size() == 2);

        Object[] arrayA = o.toArray();
        assertTrue(arrayA[0].equals("token-2"));
        assertTrue(arrayA[1].equals("token-1"));
    }
}
