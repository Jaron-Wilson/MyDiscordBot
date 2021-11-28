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

    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();

       if (messageSent.equalsIgnoreCase(Main.prefix + "delete enable " + !isEnabledd)) {
            if (isEnabledd) {

                event.getChannel().sendMessage("It is currently: " + !isEnabledd + " ,It is currently: disabled").queue();
            }else {
                event.getChannel().sendMessage("It is currently: " + !isEnabledd + " ,It is currently: enabled").queue();
            }
            isEnabledd = !isEnabledd;
        } else {
            if (!event.getMessage().getAuthor().isBot()) {
                event.getChannel().sendMessage("Deleting game/count is: " + isEnabledd).queue();
            }else {
                System.out.println("TRIED: " + timesTried);
            }

        }
    }


    public void onMessageDelete(@NotNull MessageDeleteEvent event) {
        if (isEnabledd == false) {
            event.getChannel().sendMessage(String.format("You need to enable it with: %s Delete %b", Main.prefix, isEnabledd)).queue();
        }else if (isEnabledd == true) {
            counter++;
            event.getChannel().sendMessage(String.format("Your deleted count is up to: %d", counter)).queue();
        } else {
            event.getChannel().sendMessage(String.format("You need to enable it with: %s Delete %b", Main.prefix, isEnabledd)).queue();
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
