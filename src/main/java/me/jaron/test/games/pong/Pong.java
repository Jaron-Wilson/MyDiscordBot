package me.jaron.test.games.pong;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Pong  extends ListenerAdapter{

    public void onMessageReceived(MessageReceivedEvent event) {
        System.out.println("We recieved a message from " +
                event.getAuthor().getName() + ": " +
                event.getMessage().getContentDisplay() + " \n" +
                "Channel name/id: " +
                event.getChannel()

        );
        if (event.getMessage().getContentRaw().equals("!ping")) {
            event.getChannel().sendMessage("Pong!").queue();
        }
    }
}
