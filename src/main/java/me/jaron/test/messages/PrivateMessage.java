package me.jaron.test.messages;

import me.jaron.test.Main;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PrivateMessage extends ListenerAdapter {

    public PrivateMessage() throws IOException {}
    List<String> list = Files.readAllLines(Paths.get("src/main/java/me/jaron/test/examplebot/config.txt"));//"config.txt"));

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {

        String messageSent = event.getMessage().getContentRaw();

        if (messageSent.equalsIgnoreCase(list.get(2) + "PrivateMessage")) {
            if (event.getMember() != null) {
                event.getMember().getUser().openPrivateChannel().queue((channel) -> channel.sendMessage("Hello!").queue());
            }
        } else if (messageSent.equalsIgnoreCase(list.get(2) + "PM")) {
            if (event.getMember() != null) {
                event.getMember().getUser().openPrivateChannel().queue((channel) -> channel.sendMessage("Hello!").queue());
            }
        }
        else if (messageSent.contains(list.get(2) + "dm")) {
            for (Member member : event.getMessage().getMentionedMembers()) {
                PrivateChannel channel = member.getUser().openPrivateChannel().complete();
                String content = event.getMessage().getContentDisplay().replace("@" + member.getEffectiveName(), "").replace("-dm", "");

                channel.sendMessage(content.length() > 1 ? content : "Hello").complete();
            }
        }

    }



}