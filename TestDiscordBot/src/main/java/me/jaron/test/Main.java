package me.jaron.test;

import me.jaron.test.commands.Clearcommand;
import me.jaron.test.commands.PrivateMessage;
import me.jaron.test.embeds.HelpAndInfo;
import me.jaron.test.embeds.TestEmbeds;
import me.jaron.test.games.GuessingGame;
import me.jaron.test.listeners.Announcement;
import me.jaron.test.messages.Messages;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Main {

    public static JDABuilder builder;

    public static String prefix;





    public static void main(String[] args) throws LoginException {
        String token = "YourToken";
        prefix = "-";
        builder = JDABuilder.createDefault(token);


        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setCompression(Compression.NONE);

        builder.setActivity(Activity.watching("Me being made"));
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);

        registerListeners();

        builder.build();
    }

    public static void registerListeners(){
        builder.addEventListeners(new Announcement());
        builder.addEventListeners(new GuessingGame());
        builder.addEventListeners(new Clearcommand());
        builder.addEventListeners(new PrivateMessage());
        builder.addEventListeners(new Messages());
        builder.addEventListeners(new HelpAndInfo());
        builder.addEventListeners(new TestEmbeds());
    }

}
