package kante.jofoo.error;

/**
 * Created by moh on 8/6/16.
 */
public class JsonError extends Error
{

    public JsonError(String msg) {
        super(msg);
    }

    public JsonError(Throwable e) {
        super(e);
    }
}
