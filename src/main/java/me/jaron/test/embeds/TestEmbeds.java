package me.jaron.test.embeds;

import me.jaron.test.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.awt.*;

public class TestEmbeds extends ListenerAdapter {

    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();

        if (messageSent.equalsIgnoreCase(Main.prefix + "embed")) {
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
