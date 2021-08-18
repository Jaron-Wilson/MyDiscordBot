package me.jaron.test.games;

import me.jaron.test.Main;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class GuessingGame extends ListenerAdapter {

    private Random r = new Random();
    private int answer = 0;
    private boolean isGameStarted = false;

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();

        if (messageSent.equalsIgnoreCase(Main.prefix + "StartGGame")) {
            event.getMessage().delete().queue();
            event.getChannel().sendMessage("Game started! Start guessing numbers now! 0-10").queue();
            answer = r.nextInt(10);
            isGameStarted = true;
        } else if (messageSent.equalsIgnoreCase(String.valueOf(answer))) {
            if (isGameStarted) {
                event.getChannel().sendMessage("Correct!").queue();
                isGameStarted = false;
            }
        }

    }

}
