package me.jaron.test.events.calculator;

import me.jaron.test.Main;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Calculate extends ListenerAdapter {
    public Calculate() throws IOException {}
    List<String> list = Files.readAllLines(Paths.get("src/main/java/me/jaron/test/examplebot/config.txt"));//"config.txt"));


    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {

        String[] message = e.getMessage().getContentRaw().split(" ");

        if(message.length == 1 && message[0].equalsIgnoreCase(list.get(2) + "calculate")){
            e.getChannel().sendMessage(String.format("To use this command type(without brackets): %scalculate [add/sub] [first-num] [second-num]", list.get(2))).queue();
        }else if(message[0].equalsIgnoreCase( list.get(2) + "calculate") && message[1].equalsIgnoreCase("add")){
            int num1 = Integer.parseInt(message[2]);
            int num2 = Integer.parseInt(message[3]);
            e.getChannel().sendMessage("The Result is: " + (num1 + num2)).queue();
        }else if(message[0].equalsIgnoreCase(list.get(2) + "calculate") && message[1].equalsIgnoreCase("sub")){
            int num1 = Integer.parseInt(message[2]);
            int num2 = Integer.parseInt(message[3]);
            e.getChannel().sendMessage("The Result is: " + (num1 - num2)).queue();
        }
    }
}
