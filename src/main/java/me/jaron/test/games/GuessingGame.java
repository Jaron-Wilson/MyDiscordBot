package me.jaron.test.games;

import me.jaron.test.Main;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class GuessingGame extends ListenerAdapter {
    public GuessingGame() throws IOException {}
    List<String> list = Files.readAllLines(Paths.get("src/main/java/me/jaron/test/examplebot/config.txt"));//"config.txt"));


    private Random r = new Random();
    private int answer = 0;
    private boolean isGameStarted = false;

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {

        String messageSent = event.getMessage().getContentRaw();

        if (messageSent.equalsIgnoreCase(list.get(2) + "StartGGame")) {
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
