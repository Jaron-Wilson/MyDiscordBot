package me.jaron.test.messages;

import me.jaron.test.Main;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.List;

public class Messages extends ListenerAdapter {

    String emojiuni = "U+1F47E";
    String emojiuni1 = "\uD83D\uDC7E";

    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();

        if (messageSent.equalsIgnoreCase(Main.prefix + "clone")) {
            event.getChannel().createCopy().queue();

            event.getMessage().addReaction("😝").queue();
            event.getMessage().addReaction("🗡").queue();
            event.getMessage().addReaction(emojiuni1).queue();
            return;
        }

        if (messageSent.equalsIgnoreCase(Main.prefix + "remove")) {
            event.getChannel().delete().queue();
            event.getMessage().addReaction("😝").queue();
            event.getMessage().addReaction("🗡").queue();
            event.getMessage().addReaction(emojiuni1).queue();
            return;
        }


        if (messageSent.equalsIgnoreCase("HI")) {
            event.getMessage().addReaction("🤔").queue();
            event.getMessage().addReaction("🗡").queue();
        }


        else if (messageSent.equalsIgnoreCase(Main.prefix + "bob")) {
            String url = "http://results.dogpile.com/serp?qc=images&q=";
            String messageurl = event.getMessage().getContentRaw();
            String content = event.getMessage().getContentDisplay().replace(Main.prefix +  "bob" , "");

            List<Member> other = event.getChannel().getMembers();
            other.get(other.size()).modifyNickname("Billy").queue();

            if (messageSent.contains(Main.prefix + "bob")) {
                event.getChannel().sendMessage("HI").queue();
                event.getMessage().addReaction("🍑").queue();
            }
            event.getChannel().sendMessage(url + content).queue();

            event.getMessage().mentionsEveryone();
        }
    }
}