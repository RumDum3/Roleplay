package me.rumdum;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @author RumDum
 */

public class Cache {

    public static String TOKEN;
    public static String SERVER_NAME;

    public static boolean TWITTER_STATUS;
    public static long TWITTER_CHANNEL_ID;
    public static String TWITTER_COLOR;
    public static String TWITTER_EMOJI_URL;

    public static boolean DARKNET_STATUS;
    public static long DARKNET_CHANNEL_ID;
    public static long DARKNET_LOGS_CHANNEL_ID;
    public static String DARKNET_COLOR;
    public static String DARKNET_EMOJI_URL;

    public static boolean INSTAGRAM_STATUS;
    public static long INSTAGRAM_CHANNEL_ID;
    public static String INSTAGRAM_COLOR;
    public static String INSTAGRAM_EMOJI_URL;

    public static boolean ADVERTISE_STATUS;
    public static long ADVERTISE_CHANNEL_ID;
    public static String ADVERTISE_COLOR;
    public static String ADVERTISE_EMOJI_URL;

    public static void loadConfig() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("conf.json");
        JSONObject obj = (JSONObject) parser.parse(reader);
        // --------------------------------------------------------------------------------
        try {
            TOKEN = (String) obj.get("token");
            SERVER_NAME = (String) obj.get("server-name");
        } catch (Exception exc) { System.exit(0); }
        // --------------------------------------------------------------------------------
        JSONObject modules = (JSONObject) obj.get("modules");
        // --------------------------------------------------------------------------------
        try {
            JSONObject twitter = (JSONObject) modules.get("twitter");
            TWITTER_STATUS = (Boolean) twitter.get("status");
            TWITTER_CHANNEL_ID = Long.parseLong(String.valueOf(twitter.get("channel-id")));
            TWITTER_COLOR = (String) twitter.get("color");
            TWITTER_EMOJI_URL = (String) twitter.get("emoji-url");
        } catch (Exception ignored) {}
        // --------------------------------------------------------------------------------
        try {
            JSONObject darknet = (JSONObject) modules.get("darknet");
            DARKNET_STATUS = (Boolean) darknet.get("status");
            DARKNET_CHANNEL_ID = Long.parseLong(String.valueOf(darknet.get("channel-id")));
            DARKNET_LOGS_CHANNEL_ID = Long.parseLong(String.valueOf(darknet.get("logs-channel-id")));
            DARKNET_COLOR = (String) darknet.get("color");
            DARKNET_EMOJI_URL = (String) darknet.get("emoji-url");
        } catch (Exception ignored) {}
        // --------------------------------------------------------------------------------
        try {
            JSONObject instagram = (JSONObject) modules.get("instagram");
            INSTAGRAM_STATUS = (Boolean) instagram.get("status");
            INSTAGRAM_CHANNEL_ID = Long.parseLong(String.valueOf(instagram.get("channel-id")));
            INSTAGRAM_COLOR = (String) instagram.get("color");
            INSTAGRAM_EMOJI_URL = (String) instagram.get("emoji-url");
        } catch (Exception ignored) {}
        // --------------------------------------------------------------------------------
        try {
            JSONObject advertise = (JSONObject) modules.get("advertise");
            ADVERTISE_STATUS = (Boolean) advertise.get("status");
            ADVERTISE_CHANNEL_ID = Long.parseLong(String.valueOf(advertise.get("channel-id")));
            ADVERTISE_COLOR = (String) advertise.get("color");
            ADVERTISE_EMOJI_URL = (String) advertise.get("emoji-url");
        } catch (Exception ignored) {}
        // --------------------------------------------------------------------------------
    }

}
