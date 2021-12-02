package me.jaron.test.events.filter;

import me.jaron.test.Main;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FilterMessage extends ListenerAdapter {
    public FilterMessage() throws IOException {}
    List<String> list = Files.readAllLines(Paths.get("src/main/java/me/jaron/test/examplebot/config.txt"));//"config.txt"));


    public static boolean allowed = false;

    public void onGuildMessageReceived(GuildMessageReceivedEvent e){
        if(FilterOnOff.filterOn) {
            if (e.getMessage().getContentRaw().equalsIgnoreCase(list.get(2) + "filtermessage") && !allowed) {
                e.getChannel().sendMessage("Filter Response Has Been Enabled.").queue();
                System.out.println("Enabled");
                allowed = true;
            } else if (e.getMessage().getContentRaw().equalsIgnoreCase(list.get(2) + "filtermessage") && allowed) {
                e.getChannel().sendMessage("Filter Response Has Been Disabled.").queue();
                System.out.println("Disabled");
                allowed = false;
            }
        }else if(e.getMessage().getContentRaw().equalsIgnoreCase(list.get(2) + "filtermessage") && !FilterOnOff.filterOn){
            e.getChannel().sendMessage("You can't toggle filter response while the filter is off. To turn the filter on, run -togglefilter").queue();
        }


    }

}