package me.jaron.test.messages;

import me.jaron.test.Main;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class Messages extends ListenerAdapter {

    String messages = "Message!";
    String emojiuni = "U+1F47E";

    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();

        if (messageSent.equalsIgnoreCase(Main.prefix + messages)) {
//            event.getMessage().addReaction(emoji1).queue();
            event.getMessage().getReactionByUnicode(emojiuni);
        }
    }
}