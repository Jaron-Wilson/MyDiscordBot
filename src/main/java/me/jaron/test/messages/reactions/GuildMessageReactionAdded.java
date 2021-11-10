package me.jaron.test.messages.reactions;

import me.jaron.test.Main;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;

public class GuildMessageReactionAdded extends ListenerAdapter {

    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
        if (event.getReactionEmote().getName().equalsIgnoreCase("\uD83D\uDC7E") &&
                !event.getUser().equals(event.getJDA().getSelfUser())) {
            if (event.getMember().getUser().equals(event.getChannel().getLatestMessageId())) {
                event.getChannel().createCopy().queue();
            } else {
                event.getReaction().clearReactions().queue();
                event.getChannel().createCopy().queue();
            }
        }
    }
}
