package me.jaron.test.games.pong;

import me.jaron.test.Main;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
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

        Message msg = event.getMessage();
        if (msg.getContentRaw().equals(Main.prefix + "!ping")) {
            MessageChannel channel = event.getChannel();
            long time = System.currentTimeMillis();
            channel.sendMessage("Pong!") /* => RestAction<Message> */
                    .queue(response /* => Message */ -> {
                        response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
                    });
        }
    }
}
