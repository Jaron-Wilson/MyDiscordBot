package me.jaron.test.commands;

import me.jaron.test.Main;
import me.jaron.test.Main2;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Clearcommand extends ListenerAdapter {
    public Clearcommand() throws IOException {}
    Main2 main2 = new Main2();

//    List<String> list = Files.readAllLines(Paths.get("src/main/java/me/jaron/test/examplebot/config.txt"));//"config.txt"));


    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();
         if (messageSent.equalsIgnoreCase(main2.getList().get(2) + "clear")) {
            java.util.List<Message> messages = event.getChannel().getHistory().retrievePast(2).complete();
            OffsetDateTime twoWeeksAgo = OffsetDateTime.now().minus(1, ChronoUnit.WEEKS);

            messages.removeIf(m -> m.getTimeCreated().isBefore(twoWeeksAgo));
            event.getChannel().deleteMessages(messages).complete();
        }

        else if (messageSent.equalsIgnoreCase(main2.getList().get(2) + "clearall")) {
            List<Message> messages = event.getChannel().getHistory().retrievePast(100).complete();
            OffsetDateTime twoWeeksAgo = OffsetDateTime.now().minus(2, ChronoUnit.WEEKS);

            messages.removeIf(m -> m.getTimeCreated().isBefore(twoWeeksAgo));
            event.getChannel().deleteMessages(messages).complete();

        }

        //NOT MINE!

        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args[0].equalsIgnoreCase(main2.getList().get(2) + "clear")) {
            if (args.length < 2) {
                EmbedBuilder usage = new EmbedBuilder();
                usage.setColor(16726307);
                usage.setTitle("Specify amount to delete");
                usage.setDescription("Usage: `" + main2.getList().get(2) + "clear [# of messages]`");
                event.getChannel().sendMessage(usage.build()).queue();
            } else {
                EmbedBuilder error;
                try {
                    List messages = (List) event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
                    event.getChannel().deleteMessages(messages).queue();
                    error = new EmbedBuilder();
                    error.setColor(2293546);
                    error.setTitle("✅ Successfully deleted " + args[1] + " messages.");
                    event.getChannel().sendMessage(error.build()).queue();
                } catch (IllegalArgumentException var5) {
                    if (var5.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval")) {
                        error = new EmbedBuilder();
                        error.setColor(16726307);
                        error.setTitle("\ud83d\udd34 Too many messages selected");
                        error.setDescription("Between 1-100 messages can be deleted at one time.");
                        event.getChannel().sendMessage(error.build()).queue();
                    } else {
                        error = new EmbedBuilder();
                        error.setColor(16726307);
                        error.setTitle("\ud83d\udd34 Selected messages are older than 2 weeks");
                        error.setDescription("Messages older than 2 weeks cannot be deleted.");
                        event.getChannel().sendMessage(error.build()).queue();
                    }
                }
            }
        }


    }
}
