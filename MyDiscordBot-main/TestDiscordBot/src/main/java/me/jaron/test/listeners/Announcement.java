package me.jaron.test.listeners;

import me.jaron.test.Main;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class Announcement extends ListenerAdapter {

    private String emoji1 = "\u1F4AB";
     String getChannelId;


    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();


        if (messageSent.equalsIgnoreCase(Main.prefix + "ChannelID")) {
            String id = event.getChannel().getId();
            getChannelId = id;
            event.getChannel().sendMessage(id).queue();
            return;

        }else if (messageSent.equalsIgnoreCase(Main.prefix + "ChannelID = getChannelId")) {
            String content = event.getMessage().getContentDisplay().replace("getChannelId", " " + getChannelId);//.replace("-dm", "");
            event.getChannel().sendMessage("channel id saved \n " + "ChannelId = " + getChannelId).queue();
        }

        if ("hello".equalsIgnoreCase(messageSent)) {
            event.getMessage().delete().queue();
            event.getChannel().sendMessage("Hello!").queue();
            return;

        } else if (messageSent.equalsIgnoreCase(Main.prefix + "Announce")) {
            event.getMessage().delete().queue();
            event.getChannel().sendMessage("Sending Announcement").queue();
            event.getGuild().getTextChannelById("847611877546524692").sendMessage("Announcement Test").queue();
//            event.getChannel().addReactionById(messageSent, emoji1); // does not work

        } else if (messageSent.equalsIgnoreCase("Announce")) {
            event.getMessage().delete().queue();
            event.getChannel().sendMessage("Sending Announcement").queue();
            event.getGuild().getTextChannelById(getChannelId).sendMessage("Announcement Test").queue();
        }
    }

}
