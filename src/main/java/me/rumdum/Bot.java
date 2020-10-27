package me.rumdum;

import me.rumdum.modules.Advertise;
import me.rumdum.modules.Darknet;
import me.rumdum.modules.Instagram;
import me.rumdum.modules.Twitter;
import me.rumdum.utils.MESSAGES;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Bot {

    public static void main(String[] args) throws LoginException {

        try { Cache.loadConfig(); } catch (Exception exc) { System.out.println(MESSAGES.ERROR_MESSAGE.getMessage()); exc.printStackTrace(); }

        JDABuilder builder = JDABuilder.createDefault(Cache.TOKEN);

        builder.addEventListeners(new Twitter());
        builder.addEventListeners(new Darknet());
        builder.addEventListeners(new Instagram());
        builder.addEventListeners(new Advertise());


        builder.build();
    }

}
