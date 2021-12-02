package me.jaron.test.embeds;

import me.jaron.test.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestEmbeds extends ListenerAdapter {
    public TestEmbeds() throws IOException {}
    List<String> list = Files.readAllLines(Paths.get("src/main/java/me/jaron/test/examplebot/config.txt"));//"config.txt"));


    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();

        if (messageSent.equalsIgnoreCase(list.get(2) + "embed")) {
            EmbedBuilder embedBuilder = new EmbedBuilder();

            embedBuilder.setTitle("Title");
            embedBuilder.setColor(Color.RED);
            embedBuilder.setDescription("Description");
            embedBuilder.setFooter("Footer");
            // You can do other stuff, like footers and images, but this is the basic idea

            // To send it, do this:
            event.getChannel().sendMessage(embedBuilder.build()).queue();

        } else if (messageSent.equalsIgnoreCase("embed")) {
            EmbedBuilder embedBuilder = new EmbedBuilder();

            embedBuilder.setTitle("Title");
            embedBuilder.setColor(Color.RED);
            embedBuilder.setDescription("Description");
            embedBuilder.setFooter("Footer");

            // You can do other stuff, like footers and images, but this is the basic idea

            // To send it, do this:
            event.getChannel().sendMessage(embedBuilder.build()).queue();


        }
    }
}
