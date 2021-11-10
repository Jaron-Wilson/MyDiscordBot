package me.jaron.test.events;


import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

public class GuildMemberJoin extends ListenerAdapter {
    String[] messages = new String[]{"[member] joined. You must construct additional pylons.", "Never gonna give [member] up. Never let [member] down!", "Hey! Listen! [member] has joined!", "Ha! [member] has joined! You activated my trap card!", "We've been expecting you, [member].", "It's dangerous to go alone, take [member]!", "Swoooosh. [member] just landed.", "Brace yourselves. [member] just joined the server.", "A wild [member] appeared."};

    public GuildMemberJoin() {
    }

    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Random rand = new Random();
        int number = rand.nextInt(this.messages.length);
        EmbedBuilder join = new EmbedBuilder();
        join.setColor(6740223);
        join.setDescription(this.messages[number].replace("[member]", event.getMember().getAsMention()));
        event.getGuild().getDefaultChannel().sendMessage(join.build()).queue();
        event.getGuild().addRoleToMember(event.getMember(), (Role) event.getGuild().getRolesByName("Member", true)).complete();
    }
}

