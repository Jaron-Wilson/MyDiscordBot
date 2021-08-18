package me.jaron.test.listeners;

import me.jaron.test.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.awt.*;

public class Announcement extends ListenerAdapter {

    private String emoji1 = "\u1F4AB";



    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();

        if ("hello".equalsIgnoreCase(messageSent)) {
            event.getMessage().delete().queue();
            event.getChannel().sendMessage("Hello!").queue();
            return;

        } else if (messageSent.equalsIgnoreCase(Main.prefix + "Announce")) {
            event.getMessage().delete().queue();
            event.getChannel().sendMessage("Sending Announcment").queue();
            event.getGuild().getTextChannelById("847611877546524692").sendMessage("Anouncement Test").queue();
//            event.getChannel().addReactionById(messageSent, emoji1); // does not work

        } else if (messageSent.equalsIgnoreCase("Announce")) {
            event.getMessage().delete().queue();
            event.getChannel().sendMessage("Sending Announcment").queue();
            event.getGuild().getTextChannelById("847611877546524692").sendMessage("Anouncement Test").queue();
        }
    }

}
