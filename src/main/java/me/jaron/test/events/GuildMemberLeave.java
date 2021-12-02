package me.jaron.test.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class GuildMemberLeave extends ListenerAdapter {
    public GuildMemberLeave() throws IOException {}

    List<String> list = Files.readAllLines(Paths.get("src/main/java/me/jaron/test/examplebot/config.txt"));//"config.txt"));


    String[] messages = new String[]{"[member] left, the party's over."};

    public void onGuildMemberLeave(GuildMemberLeaveEvent event) {
        Random rand = new Random();
        int number = rand.nextInt(this.messages.length);
        EmbedBuilder join = new EmbedBuilder();
        join.setColor(16024386);
        join.setDescription(this.messages[number].replace("[member]", event.getMember().getAsMention()));
        event.getGuild().getDefaultChannel().sendMessage(join.build()).queue();
    }
}
