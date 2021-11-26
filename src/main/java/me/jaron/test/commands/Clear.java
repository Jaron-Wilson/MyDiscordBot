package me.jaron.test.commands;

import me.jaron.test.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class Clear extends ListenerAdapter {
    public Clear() {
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args[0].equalsIgnoreCase(Main.prefix + "clear")) {
            if (args.length < 2) {
                EmbedBuilder usage = new EmbedBuilder();
                usage.setColor(16726307);
                usage.setTitle("Specify amount to delete");
                usage.setDescription("Usage: `" + Main.prefix + "clear [# of messages]`");
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

