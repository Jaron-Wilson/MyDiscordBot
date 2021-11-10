package me.jaron.test.messages;

import me.jaron.test.Main;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class Messages extends ListenerAdapter {

    String messages = "hi";
    String emojiuni = "U+1F47E";

    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();

            event.getMessage().addReaction(emojiuni).queue();
        event.getMessage().addReaction("😝").queue();
        event.getMessage().addReaction("🗡").queue();

        if (messageSent.equalsIgnoreCase(Main.prefix + "clone")) {
            event.getChannel().createCopy().queue();
            return;
        }
        if (messageSent.equalsIgnoreCase(Main.prefix + messages)) {
            event.getMessage().addReaction("🤔").queue();
            event.getMessage().addReaction("🗡").queue();
            return;
        }
    }


}