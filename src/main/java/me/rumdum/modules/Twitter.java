package me.rumdum.modules;

import me.rumdum.Cache;
import me.rumdum.utils.Color;
import me.rumdum.utils.MESSAGES;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;

public class Twitter extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.getMessage().isWebhookMessage()) return;

        if (!Cache.TWITTER_STATUS) return;

        if (event.getChannel().getIdLong() == Cache.TWITTER_CHANNEL_ID) {

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
        EmbedBuilder twitter = new EmbedBuilder();
        twitter.setColor(Color.getColor(Cache.TWITTER_COLOR));
        twitter.setTitle(Cache.SERVER_NAME);
        twitter.setDescription(message.getContentRaw());
        twitter.setTimestamp(message.getTimeCreated());
        if (!Cache.TWITTER_EMOJI_URL.equals("")) {
            twitter.setFooter("Twitter", Cache.TWITTER_EMOJI_URL);
        }
        return twitter;
    }

    private EmbedBuilder getMessage(Message message, String image) {
        EmbedBuilder twitter = new EmbedBuilder();
        twitter.setColor(Color.getColor(Cache.TWITTER_COLOR));
        twitter.setTitle(Cache.SERVER_NAME);
        twitter.setDescription(message.getContentRaw());
        twitter.setTimestamp(message.getTimeCreated());
        twitter.setImage("attachment://" + image);
        if (!Cache.TWITTER_EMOJI_URL.equals("")) {
            twitter.setFooter("Twitter", Cache.TWITTER_EMOJI_URL);
        }
        return twitter;
    }

}
