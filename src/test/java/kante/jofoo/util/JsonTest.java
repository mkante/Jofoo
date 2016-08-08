package kante.jofoo.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by moh on 8/8/16.
 */
public class JsonTest
{

    @Test
    public void fromJson() {

        String json = "{\"name\":\"who_is\", \"track\":19, \"artist\":\"Brandy\"}";
        Song song = Json.parse(json, Song.class);
        assertTrue(song instanceof Song);
        assertEquals(song.name, "who_is");
        assertTrue(song.track == 19);
        assertEquals(song.artist, "Brandy");
    }

    @Test
    public void toJson() {

        String json = "{\"name\":\"sitting\",\"track\":9,\"artist\":\"Brandy\"}";
        Song song = new Song();
        song.name = "sitting";
        song.track= 9;
        song.artist = "Brandy";

        assertTrue(song instanceof Song);
        assertEquals(Json.stringify(song), json);
    }
}
