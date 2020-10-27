package me.rumdum.modules;

import me.rumdum.Cache;
import me.rumdum.utils.Color;
import me.rumdum.utils.MESSAGES;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;

public class Advertise extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.getMessage().isWebhookMessage()) return;

        if (!Cache.ADVERTISE_STATUS) return;

        if (event.getChannel().getIdLong() == Cache.ADVERTISE_CHANNEL_ID) {

            Message message = event.getMessage();
            if (event.getMessage().getAttachments().isEmpty()) {

                event.getMessage().delete().queue();
                event.getChannel().sendMessage(getMessage(message).build()).queue();

            } else {

                try {

                    String temp = event.getMessage().getAttachments().get(0).downloadToFile().get().getName();
                    event.getMessage().delete().queue();
                    event.getChannel().sendMessage(" ").addFile(new File(temp)).
                            embed(getMessage(message, temp).build()).queue();

                }catch (Exception exc) {
                    System.out.println(MESSAGES.ERROR_MESSAGE.getMessage());
                    exc.printStackTrace();
                }

            }

        }
    }

    private EmbedBuilder getMessage(Message message) {
        EmbedBuilder advertise = new EmbedBuilder();
        advertise.setColor(Color.getColor(Cache.ADVERTISE_COLOR));
        advertise.setTitle(Cache.SERVER_NAME);
        advertise.setDescription(message.getContentRaw());
        advertise.setTimestamp(message.getTimeCreated());
        if (!Cache.ADVERTISE_EMOJI_URL.equals("")) {
            advertise.setFooter("Advertise", Cache.ADVERTISE_EMOJI_URL);
        }
        return advertise;
    }

    private EmbedBuilder getMessage(Message message, String image) {
        EmbedBuilder advertise = new EmbedBuilder();
        advertise.setColor(Color.getColor(Cache.ADVERTISE_COLOR));
        advertise.setTitle(Cache.SERVER_NAME);
        advertise.setDescription(message.getContentRaw());
        advertise.setTimestamp(message.getTimeCreated());
        advertise.setImage("attachment://" + image);
        if (!Cache.ADVERTISE_EMOJI_URL.equals("")) {
            advertise.setFooter("Advertise", Cache.ADVERTISE_EMOJI_URL);
        }
        return advertise;
    }

}
