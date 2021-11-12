package me.jaron.test.events;


import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.channel.category.CategoryCreateEvent;
import net.dv8tion.jda.api.events.channel.priv.PrivateChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateSlowmodeEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.user.UserTypingEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.requests.Route;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

    String[] messages1 = new String[]{"[member] Won a new Role: [role]. You must have great helping skills [member].", "Never gonna give [role] up. Never let other members down [member]!", "Hey! Listen! [member] is the new [role]!"};

    public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
        Random rand = new Random();
        int number = rand.nextInt(this.messages1.length);
        EmbedBuilder join = new EmbedBuilder();
        join.setColor(6740223);
        join.setDescription(this.messages1[number].replace("[member]", event.getMember().getAsMention()).replace("[role]", event.getMember().getRoles().toString().replace(":", "").replace(")]", "").replace("(", "").replace("[", "").toLowerCase(Locale.ROOT)));
        event.getGuild().getDefaultChannel().sendMessage(join.build()).queue();
//        event.getGuild().addRoleToMember(event.getMember(), (Role) event.getGuild().getRolesByName("Member", true)).complete();
    }

    int counter = 0;
    public void onMessageDelete(@NotNull MessageDeleteEvent event) {

        counter++;
        event.getChannel().sendMessage(String.format("Your deleted count is up to: %d", counter)).queue();
        event.getChannel().sendTyping().queue();
        event.getChannel().sendTyping().completeAfter(3, TimeUnit.SECONDS);
    }

    @Override
    public void onTextChannelUpdateSlowmode(@NotNull TextChannelUpdateSlowmodeEvent event) {
        event.getOldSlowmode();
        event.getChannel().sendMessageFormat("slowmode updated", event.getNewSlowmode()).queue();
    }
}
