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

    public void sendPrivateMessage(User user, String content) {
        // openPrivateChannel provides a RestAction<PrivateChannel>
        // which means it supplies you with the resulting channel
        user.openPrivateChannel().queue((channel) ->
        {
            channel.sendMessage(content).queue();
        });
    }

    public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        String message = event.getMessage().getContentRaw();

        if (message.equalsIgnoreCase("Hello" + "!")) {
            sendPrivateMessage(event.getAuthor(), "Well hello! How can I help you, today?");
        }else if (message.equalsIgnoreCase("Hi" + "!")) {
            sendPrivateMessage(event.getAuthor(), "Hi! How can I help you, today?");
        }else if (message.equalsIgnoreCase("Hi")) {
            sendPrivateMessage(event.getAuthor(), "Hi! How can I help you, today?");
        }


        else if (message.equalsIgnoreCase(message)) {
            sendPrivateMessage(event.getAuthor(), "Please explain what you need.");
        }

    }

}