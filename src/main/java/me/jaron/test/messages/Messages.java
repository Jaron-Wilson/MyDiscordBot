package me.jaron.test.messages;

import me.jaron.test.Main;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Messages extends ListenerAdapter {
    public Messages() throws IOException {}
    List<String> list = Files.readAllLines(Paths.get("src/main/java/me/jaron/test/examplebot/config.txt"));//"config.txt"));


    String emojiuni = "U+1F47E";
    String emojiuni1 = "\uD83D\uDC7E";

    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();

        if (messageSent.equalsIgnoreCase(list.get(2) + "clone")) {
            event.getChannel().createCopy().queue();

            event.getMessage().addReaction("😝").queue();
            event.getMessage().addReaction("🗡").queue();
            event.getMessage().addReaction(emojiuni1).queue();
            return;
        }

        if (messageSent.equalsIgnoreCase(list.get(2) + "remove")) {
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


        else if (messageSent.equalsIgnoreCase(list.get(2) + "bob")) {
            String url = "http://results.dogpile.com/serp?qc=images&q=";
            String messageurl = event.getMessage().getContentRaw();
            String content = event.getMessage().getContentDisplay().replace(list.get(2) +  "bob" , "");

            List<Member> other = event.getChannel().getMembers();
            other.get(other.size()).modifyNickname("Billy").queue();

            if (messageSent.contains(list.get(2) + "bob")) {
                event.getChannel().sendMessage("HI").queue();
                event.getMessage().addReaction("🍑").queue();
            }
            event.getChannel().sendMessage(url + content).queue();

            event.getMessage().mentionsEveryone();
        }
    }
}