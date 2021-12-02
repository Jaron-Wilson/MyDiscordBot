package me.jaron.test.events.filter;

import me.jaron.test.Main;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FilterOnOff extends ListenerAdapter {
    public FilterOnOff() throws IOException {}
    List<String> list = Files.readAllLines(Paths.get("src/main/java/me/jaron/test/examplebot/config.txt"));//"config.txt"));


    public static boolean filterOn = true;

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equalsIgnoreCase(list.get(2) + "togglefilter") && filterOn){
            filterOn = false;
            event.getChannel().sendMessage("The Curse-Filter has been disabled by " + event.getMember().getUser().getName()).queue();
        }else if(event.getMessage().getContentRaw().equalsIgnoreCase(list.get(2) + "togglefilter") && !filterOn){
            filterOn = true;
            event.getChannel().sendMessage("The Curse-Filter has been enabled by " + event.getMember().getUser().getName()).queue();
        }
    }
}