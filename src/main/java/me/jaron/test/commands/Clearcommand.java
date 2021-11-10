package me.jaron.test.commands;

import me.jaron.test.Main;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Clearcommand extends ListenerAdapter {


    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();
         if (messageSent.equalsIgnoreCase(Main.prefix + "clear")) {
            java.util.List<Message> messages = event.getChannel().getHistory().retrievePast(2).complete();
            OffsetDateTime twoWeeksAgo = OffsetDateTime.now().minus(1, ChronoUnit.WEEKS);

            messages.removeIf(m -> m.getTimeCreated().isBefore(twoWeeksAgo));
            event.getChannel().deleteMessages(messages).complete();
        }

        else if (messageSent.equalsIgnoreCase(Main.prefix + "clearall")) {
            List<Message> messages = event.getChannel().getHistory().retrievePast(100).complete();
            OffsetDateTime twoWeeksAgo = OffsetDateTime.now().minus(2, ChronoUnit.WEEKS);

            messages.removeIf(m -> m.getTimeCreated().isBefore(twoWeeksAgo));
            event.getChannel().deleteMessages(messages).complete();

        }
    }
}
