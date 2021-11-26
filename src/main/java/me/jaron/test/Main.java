package me.jaron.test;

import me.jaron.test.commands.Clear;
import me.jaron.test.commands.Clearcommand;
import me.jaron.test.embeds.HelpAndInfo;
import me.jaron.test.embeds.TestEmbeds;
import me.jaron.test.events.GuildMemberJoin;
import me.jaron.test.events.GuildMemberLeave;
import me.jaron.test.events.ImageSencer;
import me.jaron.test.events.channels.CategoryUpdate;
import me.jaron.test.games.GuessingGame;
import me.jaron.test.games.pong.Pong;
import me.jaron.test.listeners.Announcement;
import me.jaron.test.messages.Messages;
import me.jaron.test.messages.PrivateMessage;
import me.jaron.test.messages.dmed.DMMessaged;
import me.jaron.test.messages.reactions.GuildMessageReactionAdded;
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

//        String token = "YourToken";
        String token = "";
        prefix = "-";
        builder = JDABuilder.createDefault(token);

        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setCompression(Compression.NONE);

        builder.setActivity(Activity.watching("My owner, and making me!"));
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);

        registerListeners();

        builder.build();

    }

    public static void registerListeners(){

        builder.addEventListeners(new Pong());
        builder.addEventListeners(new Announcement());
        builder.addEventListeners(new GuessingGame());
        builder.addEventListeners(new Clearcommand());
        builder.addEventListeners(new PrivateMessage());
        builder.addEventListeners(new Messages());
        builder.addEventListeners(new HelpAndInfo());
        builder.addEventListeners(new TestEmbeds());
        builder.addEventListeners(new DMMessaged());
        builder.addEventListeners(new GuildMessageReactionAdded());
        builder.addEventListeners(new CategoryUpdate());
        builder.addEventListeners(new ImageSencer());

        //not mine copied
        builder.addEventListeners(new Clear());
        builder.addEventListeners(new GuildMemberJoin());
        builder.addEventListeners(new GuildMemberLeave());
//        builder.addEventListeners(new GuildMessageReceived());


    }

    public static String getPrefix() {
        return prefix;
    }

    public static void setPrefix(String prefix) {
        Main.prefix = prefix;
    }
}
