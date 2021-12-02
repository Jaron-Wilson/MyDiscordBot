package me.jaron.test.events.filter;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Filter extends ListenerAdapter {
    public Filter() throws IOException {}
    List<String> list = Files.readAllLines(Paths.get("src/main/java/me/jaron/test/examplebot/config.txt"));//"config.txt"));

    public boolean badWord = false;

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if(FilterOnOff.filterOn){
            String[] LIST_OF_BAD_WORDS = {"anal", "anus", "arse", "ass", "motherfucker", "balls", "bastard", "bitch", "blowjob", "blow job", "buttplug","cock","coon","cunt","dildo","fag","dyke","fuck","fucking","nigger","Goddamn","jizz","nigga","pussy","shit","whore"};
            String[] message = e.getMessage().getContentRaw().split(" ");
            for(int i = 0;i < message.length;i++){
                //Check each message for each bad word
                for(int b = 0; b < LIST_OF_BAD_WORDS.length;b++){
                    if (message[i].equalsIgnoreCase(LIST_OF_BAD_WORDS[b])){
                        e.getMessage().delete().queue();
                        badWord = true;
                        if(FilterMessage.allowed){ //Prints a message saying watch your language IF enabled by -filtermessage
                            e.getChannel().sendMessage("Watch yo Language BOI, " + e.getMember().getUser().getName()).queue();
                        }
                    }
                }
//                System.out.println(message[i] + " " + badWord); //Just a report for console
            }
        }
    }
}