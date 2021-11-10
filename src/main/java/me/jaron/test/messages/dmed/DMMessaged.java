package me.jaron.test.messages.dmed;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import javax.annotation.Nonnull;

public class DMMessaged {

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
