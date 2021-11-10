package me.jaron.test.messages;

import me.jaron.test.Main;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class PrivateMessage extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {

        String messageSent = event.getMessage().getContentRaw();

        if (messageSent.equalsIgnoreCase(Main.prefix + "PrivateMessage")) {
            if (event.getMember() != null) {
                event.getMember().getUser().openPrivateChannel().queue((channel) -> channel.sendMessage("Hello!").queue());
            }
        } else if (messageSent.equalsIgnoreCase(Main.prefix + "PM")) {
            if (event.getMember() != null) {
                event.getMember().getUser().openPrivateChannel().queue((channel) -> channel.sendMessage("Hello!").queue());
            }
        }
        else if (messageSent.contains(Main.prefix + "dm")) {
            for (Member member : event.getMessage().getMentionedMembers()) {
                PrivateChannel channel = member.getUser().openPrivateChannel().complete();
                String content = event.getMessage().getContentDisplay().replace("@" + member.getEffectiveName(), "").replace("-dm", "");

                channel.sendMessage(content.length() > 1 ? content : "Hello").complete();
            }
        }

    }



}