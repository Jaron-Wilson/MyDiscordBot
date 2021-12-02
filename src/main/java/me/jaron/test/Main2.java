package me.jaron.test;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.jagrosh.jdautilities.examples.command.AboutCommand;
import com.jagrosh.jdautilities.examples.command.PingCommand;
import com.jagrosh.jdautilities.examples.command.ShutdownCommand;
import me.jaron.test.commands.Clearcommand;
import me.jaron.test.counters.DeleteCounter;
import me.jaron.test.embeds.HelpAndInfo;
import me.jaron.test.embeds.TestEmbeds;
import me.jaron.test.events.GuildMemberJoin;
import me.jaron.test.events.GuildMemberLeave;
import me.jaron.test.events.calculator.Calculate;
import me.jaron.test.events.channels.CategoryUpdate;
import me.jaron.test.events.filter.Filter;
import me.jaron.test.events.filter.FilterMessage;
import me.jaron.test.events.filter.FilterOnOff;
import me.jaron.test.events.image.ImageManipulator;
import me.jaron.test.events.image.ImageSencer;
import me.jaron.test.examplebot.commands.CatCommands;
import me.jaron.test.examplebot.commands.ChooseCommand;
import me.jaron.test.examplebot.commands.HelloCommand;
import me.jaron.test.games.GuessingGame;
import me.jaron.test.games.pong.Pong;
import me.jaron.test.listeners.Announcement;
import me.jaron.test.messages.Messages;
import me.jaron.test.messages.PrivateMessage;
import me.jaron.test.messages.dmed.DMMessaged;
import me.jaron.test.messages.reactions.GuildMessageReactionAdded;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


class RegisterListeners {
    RegisterListeners() throws IOException {}

    Pong pong = new Pong();
    Announcement announcement = new Announcement();
    GuessingGame guessingGame = new GuessingGame();
    Clearcommand clearcommand = new Clearcommand();
    PrivateMessage privateMessage = new PrivateMessage();
    Messages messages = new Messages();
    HelpAndInfo helpAndInfo = new HelpAndInfo();
    TestEmbeds testEmbeds = new TestEmbeds();
    DMMessaged dmMessaged = new DMMessaged();
    GuildMessageReactionAdded guildMessageReactionAdded = new GuildMessageReactionAdded();
    CategoryUpdate categoryUpdate = new CategoryUpdate();
    ImageSencer imageSencer = new ImageSencer();

    DeleteCounter deleteCounter = new DeleteCounter();
    //not mine copied
    GuildMemberJoin guildMemberJoin = new GuildMemberJoin();
    GuildMemberLeave guildMemberLeave = new GuildMemberLeave();
    ImageManipulator imageManipulator = new ImageManipulator();
    Filter filter = new Filter();
    FilterMessage filterMessage = new FilterMessage();
    FilterOnOff filterOnOff = new FilterOnOff();
    Calculate calculate = new Calculate();

}

public class Main2 {
    public List<String> list = Files.readAllLines(Paths.get("src/main/java/me/jaron/test/examplebot/config.txt"));//"config.txt"));

    public Main2() throws IOException {}


    public static void main(String[] args) throws IOException, LoginException, IllegalArgumentException, RateLimitedException {
        Main2 main2 = new Main2();
        // config.txt contains two lines
//        List<String> list = Files.readAllLines(Paths.get("src/main/java/me/jaron/test/examplebot/config.txt"));//"config.txt"));

        // the first is the bot token
        String token = main2.getList().get(0);

        // the second is the bot's owner's id
        String ownerId = main2.getList().get(1);

        // the third is the prefix
        String prefix = main2.getList().get(2);

        // define an eventwaiter, dont forget to add this to the JDABuilder!
        EventWaiter waiter = new EventWaiter();

        // MY CLASSES
        RegisterListeners registerListeners = new RegisterListeners();

        // define a command client
        CommandClientBuilder client = new CommandClientBuilder();

        // The default is "Type !!help" (or whatver prefix you set)
        client.useDefaultGame();

        // sets the owner of the bot
        client.setOwnerId(ownerId);

        // sets emojis used throughout the bot on successes, warnings, and failures
        client.setEmojis("\uD83D\uDE03", "\uD83D\uDE2E", "\uD83D\uDE26");

        // sets the bot prefix
        client.setPrefix(prefix);

        // adds commands
        client.addCommands(
                // command to show information about the bot
                new AboutCommand(Color.BLUE, "an example bot",
                        new String[]{"Cool commands", "Nice examples", "Lots of fun!"},
                        new Permission[]{Permission.ADMINISTRATOR}),

                // command to show a random cat
                new CatCommands(),

                // command to make a random choice
                new ChooseCommand(),

                // command to say hello
                new HelloCommand(waiter),

                // command to check bot latency
                new PingCommand(),

                // command to shut off the bot
                new ShutdownCommand());

        // start getting a bot account set up
        JDABuilder.createDefault(token)

                // set the game for when the bot is loading
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setActivity(Activity.playing("loading..."))

                // add the listeners
                .addEventListeners(waiter, client.build())

                // add my listeners
                .addEventListeners(
                        registerListeners.announcement,
                        registerListeners.deleteCounter,
                        registerListeners.calculate,
                        registerListeners.filterOnOff,
                        registerListeners.categoryUpdate,
                        registerListeners.clearcommand,
                        registerListeners.dmMessaged,
                        registerListeners.filterMessage,
                        registerListeners.guessingGame,
                        registerListeners.guildMemberJoin,
                        registerListeners.guildMemberLeave,
                        registerListeners.guildMessageReactionAdded,
                        registerListeners.helpAndInfo,
                        registerListeners.imageManipulator,
                        registerListeners.imageSencer,
                        registerListeners.messages,
                        registerListeners.privateMessage,
                        registerListeners.testEmbeds,
                        registerListeners.pong
                )

                // start it up!
                .build();
    }

    public final List<String> getList() {
        return list;
    }


}