package me.jaron.test.messages.reactions;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GuildMessageReactionAdded extends ListenerAdapter {
    public GuildMessageReactionAdded() throws IOException {}
    List<String> list = Files.readAllLines(Paths.get("src/main/java/me/jaron/test/examplebot/config.txt"));//"config.txt"));


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
