package me.jaron.test.messages.reactions;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMessageReactionAdded extends ListenerAdapter {

    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
        if (event.getReactionEmote().getName().equalsIgnoreCase("\uD83D\uDC7E") &&
                !event.getUser().equals(event.getJDA().getSelfUser())) {
            if (event.getMember().getUser().equals(event.getChannel().getLatestMessageId())) {
                event.getChannel().createCopy().queue();

                event.getMember().modifyNickname("Mute").queue();
                event.getMember().mute(true).queue();
            } else {
                event.getReaction().clearReactions().queue();
            }
        }
    }
}
