package me.jaron.test.counters;

import me.jaron.test.Main;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class DeleteCounter extends ListenerAdapter {
    public boolean isEnabledd = false;
    public int timesTried = 0;
    private int counter = 0;
    public boolean sendDeleteMessage = false;

    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();

       if (messageSent.equalsIgnoreCase(Main.prefix + "delete " + !isEnabledd)) {
            if (isEnabledd) {
                event.getChannel().sendMessage("It is currently: " + !isEnabledd + ", It is currently: disabled").queue();
            }else {
                event.getChannel().sendMessage("It is currently: " + !isEnabledd + ", It is currently: enabled").queue();
            }
            isEnabledd = !isEnabledd;
       } else if (messageSent.equalsIgnoreCase(Main.prefix + "delete messages " + !sendDeleteMessage)){
           event.getChannel().sendMessage("Delete Messages are: " + !sendDeleteMessage).queue();
           sendDeleteMessage = !sendDeleteMessage;

       } else if (messageSent.equalsIgnoreCase(Main.prefix + "delete messages " + sendDeleteMessage)){
           event.getChannel().sendMessage("Delete Messages are: " + sendDeleteMessage).queue();

       }
    }


    int countdeletetrys = 0;
    public void onMessageDelete(@NotNull MessageDeleteEvent event) {
        if (isEnabledd == false) {
            if ((countdeletetrys <= 4) && (sendDeleteMessage == true))  {
                event.getChannel().sendMessage(String.format("You need to enable it with: %sdelete true" + "Or disable this message with %sdelete messages false", Main.prefix)).queue();countdeletetrys++;
            }
        }else if ((isEnabledd == true) && (sendDeleteMessage == true)) {
            counter++;
            event.getChannel().sendMessage(String.format("Your deleted count is up to: %d", counter)).queue();
        } else {
            if ((countdeletetrys <= 4) && (sendDeleteMessage == true)) {
                event.getChannel().sendMessage(String.format("You need to enable it with: %s Delete %b !", Main.prefix, isEnabledd)).queue();
                countdeletetrys++;
            } else {
                System.out.println("NO");
            }
        }
    }


    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setEnabledd(boolean enabledd) {
        isEnabledd = enabledd;
    }

    public boolean isEnabledd() {
        return isEnabledd;
    }
}
