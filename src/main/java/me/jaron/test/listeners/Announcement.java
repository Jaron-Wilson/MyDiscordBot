package me.jaron.test.listeners;

import me.jaron.test.Main;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class Announcement extends ListenerAdapter {

    private String emoji1 = "\u1F4A";
    String textMessage = null;
    String getChannelId = null;


    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String[] messageSent2 = event.getMessage().getContentRaw().split(" ");
        String messageSent = event.getMessage().getContentDisplay();
        String name = event.getMember().getUser().getName();


        if (messageSent.equalsIgnoreCase(Main.prefix + "ChannelID")) {
            String id = event.getChannel().getId();
            getChannelId = id;
            event.getChannel().sendMessage(id).queue();
            return;

        } else if (messageSent.equalsIgnoreCase(Main.prefix + "ChannelID = getChannelId")) {
            String content = event.getMessage().getContentDisplay().replace("getChannelId", " " + getChannelId);
            event.getChannel().sendMessage("channel id saved \n " + "ChannelId = " + getChannelId + "\n If it is null that means that you need to add the id by typing in help or -help").queue();
        }

        if (messageSent2[0].equalsIgnoreCase(Main.prefix + "AnnounceMessage= " + messageSent2[0])) {
            messageSent2[0] = textMessage;
            String content = event.getMessage().getContentDisplay().replace("AnnounceMessage= ", "");
            event.getChannel().sendMessage(textMessage).queue();
        }

        if ("hello".equalsIgnoreCase(messageSent)) {
            event.getMessage().delete().queue();
            event.getChannel().sendMessage("Hello!").queue();
            return;

        } else if ((messageSent.equalsIgnoreCase(Main.prefix + "Announce")) || (messageSent.equalsIgnoreCase("Announce"))) {
            if (getChannelId != null) {
                event.getMessage().delete().queue();
                event.getChannel().sendMessage("Sending Announcement").queue();
                event.getGuild().getTextChannelById(getChannelId).sendMessage(textMessage).queue();
            } else {
                event.getChannel().sendMessage(String.format("Need to get channelid by typing %schannelid in the channel you want to send announcement,\n then to check type %sChannelID = getChannelId", Main.prefix, Main.prefix)).queue();
            }
        }
    }

}
