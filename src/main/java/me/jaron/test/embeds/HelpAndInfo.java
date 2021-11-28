package me.jaron.test.embeds;

import me.jaron.test.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.awt.*;

public class HelpAndInfo extends ListenerAdapter {

    private String HELP = "help";
    private String HELP2 = "Help!";
    private String INFO = "Info";
    private String COMMANDS = "Commands:";
    private String LachlanIcon = "https://cdn.discordapp.com/avatars/339081730273181697/471c9d9a1cf3ecb385dc7e867174cac3.png?size=256";
    private String JA_RONIcon = "https://cdn.discordapp.com/avatars/679851025544642581/6f62f4bf6c87bd322de9a9313643e99c.png?size=256";

    private String Whomade = "Made by JA_RON#9792 and a lot of help by Lachlan#4122";

    private String Infomessage = "This bot was made by JA_RON and Lachlan." +
            "\n \nThey made this bot using Intellij." +
            "\n They spent a lot of time to make this bot so please don't abuse it." +
            "\n If you want to make a bot please feel free to make a group dm with us." +
            "\n Our numbers are in the -help command. Or you can look below." +
            "\n Lachlan did the clearall command and the dm command so if you want to know how to do that just ask nicely!" +
            "\n If you have any questions or comments please refer to us and message us below is our info." +
            "";

    private String Helpmessage =
                    "\n - help (help, Help, Help!, help!) (Shows this menu)" +
                    "\n - Announce (sends a message to a certain channel)" +
                    "\n - embed (show an embed message)" +
                    "\n - clear (clears the message you just sent) (so 1 message before you type this command)" +
                    "\n - clearall (clears all before 2 weeks)" +
                    "\n - PrivateMessage " + "or" + " - PM" + " (Bot sends a private message to you)" +
                    "\n - StartGGame (number guessing game 0 - 10)" +
                    "\n - dm @Name 'message'" + " (Sends a message to the person in the @name and then you put your message in the 'message')" +
                    "\n - Info (info, info!, Info, Info!) (shows info on this bot)" +
                    "\n - clone Clones the channel you are on." +
                    "\n - remove" +
                    "\n - bob (URL)" +
                    "\n - image" +
                    "\n - delete enable (true) OR (false)" +
                    "\n AboutLach (About Lachlan)" +
                    "\n AboutJA (About JA_RON)" +
                    "\n Got some code from: https://github.com/nkomarn/JDA-Tutorial/releases/tag/5.0";


    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();

        if (messageSent.contains(Main.prefix + HELP)) {
            event.getMessage().delete().queue();
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle(COMMANDS);
            embedBuilder.setColor(Color.BLUE);
            embedBuilder.setDescription(Helpmessage);
            embedBuilder.setFooter(Whomade);

            event.getChannel().sendMessage(embedBuilder.build()).queue();

        }else if (messageSent.contains(Main.prefix + HELP2)){
            EmbedBuilder embedBuilder = new EmbedBuilder();
            event.getMessage().delete().queue();
            embedBuilder.setTitle(COMMANDS);
            embedBuilder.setColor(Color.BLUE);
            embedBuilder.setDescription(Helpmessage);
            embedBuilder.setFooter(Whomade);

            event.getChannel().sendMessage(embedBuilder.build()).queue();

        }else if (messageSent.contains("AboutLach")){
            EmbedBuilder embedBuilder = new EmbedBuilder();
            event.getMessage().delete().queue();
            embedBuilder.setTitle("About Lachlan");
            embedBuilder.setColor(Color.ORANGE);
            embedBuilder.setDescription("Lachlan helped make this bot and...");
            embedBuilder.setFooter(Whomade);
            embedBuilder.setImage(LachlanIcon);

            event.getChannel().sendMessage(embedBuilder.build()).queue();

        }else if (messageSent.contains("AboutJA")) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            event.getMessage().delete().queue();
            embedBuilder.setTitle("About JA_RON");
            embedBuilder.setColor(Color.GRAY);
            embedBuilder.setDescription("JA_RON#9792 made this bot using intellij's newest update and got help from Lachlan");
            embedBuilder.setFooter(Whomade);
            embedBuilder.setImage(JA_RONIcon);

            event.getChannel().sendMessage(embedBuilder.build()).queue();
        }else if (messageSent.contains(HELP)){
            EmbedBuilder embedBuilder = new EmbedBuilder();
            event.getMessage().delete().queue();
            embedBuilder.setTitle(COMMANDS);
            embedBuilder.setColor(Color.BLUE);
            embedBuilder.setDescription(Helpmessage);
            embedBuilder.setFooter(Whomade);

            event.getChannel().sendMessage(embedBuilder.build()).queue();

        } else if (INFO.contains(messageSent)) {
            event.getMessage().delete().queue();
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle(INFO);
            embedBuilder.setColor(Color.MAGENTA);
            embedBuilder.setDescription(Infomessage);
            embedBuilder.setFooter(Whomade);

            event.getChannel().sendMessage(embedBuilder.build()).queue();

        } else if (messageSent.contains(Main.prefix + INFO)) {
            event.getMessage().delete().queue();
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle(INFO);
            embedBuilder.setColor(Color.MAGENTA);
            embedBuilder.setDescription(Infomessage);

            event.getChannel().sendMessage(embedBuilder.build()).queue();

        }else if (messageSent.contains(INFO + "!")) {
            event.getMessage().delete().queue();
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle(INFO);
            embedBuilder.setColor(Color.MAGENTA);
            embedBuilder.setDescription(Infomessage);

            event.getChannel().sendMessage(embedBuilder.build()).queue();

        }
    }
}
