package me.rumdum.modules;

import me.rumdum.Cache;
import me.rumdum.utils.Color;
import me.rumdum.utils.MESSAGES;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;

public class Darknet extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.getMessage().isWebhookMessage()) return;

        if (!Cache.DARKNET_STATUS) return;

        TextChannel logs = event.getGuild().getTextChannelById(Cache.DARKNET_LOGS_CHANNEL_ID);
        if (logs == null) return;

        if (event.getChannel().getIdLong() == Cache.DARKNET_CHANNEL_ID) {

            Message message = event.getMessage();

            if (event.getMessage().getAttachments().isEmpty()) {

                event.getMessage().delete().queue();
                event.getChannel().sendMessage(message).queue();
                logs.sendMessage(getMessage(message).build()).queue();

            } else {

                try {

                    String temp = event.getMessage().getAttachments().get(0).downloadToFile().get().getName();
                    event.getMessage().delete().queue();
                    event.getChannel().sendMessage(" ").addFile(new File(temp)).
                            embed(new EmbedBuilder().setImage("attachment://" + temp).
                                    setColor(java.awt.Color.getColor(Cache.DARKNET_COLOR)).build()).queue();
                    logs.sendMessage(" ").addFile(new File(temp)).embed(getMessage(message, temp).build()).queue();

                }catch (Exception exc) {
                    System.out.println(MESSAGES.ERROR_MESSAGE.getMessage());
                    exc.printStackTrace();
                }

            }
        }
    }

    private EmbedBuilder getMessage(Message message) {
        String name = message.getMember().getEffectiveName() + "#" + message.getMember().getUser().getDiscriminator();
        EmbedBuilder darknet = new EmbedBuilder();
        darknet.setColor(Color.getColor(Cache.DARKNET_COLOR));
        darknet.setTitle("LOGS");
        darknet.addField("User", name, false);
        darknet.addField("Message", message.getContentRaw(), false);
        darknet.setTimestamp(message.getTimeCreated());
        if (Cache.DARKNET_EMOJI_URL.equals("")) {
            darknet.setFooter("Dark Net");
        } else {
            darknet.setFooter("Dark Net", Cache.DARKNET_EMOJI_URL);
        }
        return darknet;
    }

    private EmbedBuilder getMessage(Message message, String image) {
        String name = message.getMember().getEffectiveName() + "#" + message.getMember().getUser().getDiscriminator();
        EmbedBuilder darknet = new EmbedBuilder();
        darknet.setColor(Color.getColor(Cache.DARKNET_COLOR));
        darknet.setTitle("LOGS");
        darknet.addField("User", name, true);
        if (!message.getContentRaw().equals("")) {
            darknet.addField("Message", message.getContentRaw(), true);
        }
        darknet.setTimestamp(message.getTimeCreated());
        darknet.setImage("attachment://" + image);
        if (Cache.DARKNET_EMOJI_URL.equals("")) {
            darknet.setFooter("Dark Net");
        } else {
            darknet.setFooter("Dark Net", Cache.DARKNET_EMOJI_URL);
        }
        return darknet;
    }

}
