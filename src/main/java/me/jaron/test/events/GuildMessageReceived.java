package me.jaron.test.events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMessageReceived extends ListenerAdapter  {
    public GuildMessageReceived() {
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        event.getMessage().addReaction("❌").queue();
    }
}
