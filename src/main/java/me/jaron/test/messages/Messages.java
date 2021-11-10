package me.jaron.test.messages;

import me.jaron.test.Main;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.lang.reflect.Array;
import java.util.Objects;

public class Messages extends ListenerAdapter {

    String messages = "hi";
    String emojiuni = "U+1F47E";
    String emojiuni1 = "\uD83D\uDC7E";

    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();

        if (messageSent.equalsIgnoreCase(Main.prefix + "clone")) {
            event.getChannel().createCopy().queue();

            event.getMessage().addReaction("😝").queue();
            event.getMessage().addReaction("🗡").queue();
            event.getMessage().addReaction(emojiuni1).queue();
            return;
        }

        if (messageSent.equalsIgnoreCase(Main.prefix + "remove")) {
            event.getChannel().delete().queue();
            event.getMessage().addReaction("😝").queue();
            event.getMessage().addReaction("🗡").queue();
            event.getMessage().addReaction(emojiuni1).queue();
            return;
        }


        if (messageSent.equalsIgnoreCase(Main.prefix + messages)) {
            event.getMessage().addReaction("🤔").queue();
            event.getMessage().addReaction("🗡").queue();
        }
    }


}