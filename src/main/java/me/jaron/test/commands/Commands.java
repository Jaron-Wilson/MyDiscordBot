package me.jaron.test.commands;

import me.jaron.test.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
    public Commands() {
    }

//    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
//        String[] args = event.getMessage().getContentRaw().split("\\s+");
//        if (args[0].equalsIgnoreCase(Main.prefix + "info")) {
//            EmbedBuilder info = new EmbedBuilder();
//            info.setTitle("\ud83d\udcfa Television");
//            info.setDescription("Completely useless information about a useless bot called 'Television'.");
//            info.setColor(16012866);
//            info.setFooter("Created by techtoolbox", event.getMember().getUser().getAvatarUrl());
//            event.getChannel().sendTyping().queue();
//            event.getChannel().sendMessage(info.build()).queue();
//            info.clear();
//        }
//
//    }
}
