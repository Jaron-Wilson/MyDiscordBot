package me.jaron.test.commands.images;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Color;
import java.io.IOException;

import me.jaron.test.Main2;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class PicCommands extends Command {
    Main2 main2 = new Main2();

    public PicCommands() throws IOException {
        this.cooldown = 2;
//        this.ownerCommand = true;

        this.name = "picture";
        this.help = "shows a random Picture";
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS};
        this.guildOnly = false;
    }


    @Override
    protected void execute(CommandEvent event) {
            // use Unirest to poll an API

            Unirest.get("https://results.dogpile.com/serp?qc=images&q=" + "cat").asJsonAsync(new Callback<JsonNode>() {

                // The API call was successful
                @Override
                public void completed(HttpResponse<JsonNode> hr) {
                    System.out.println("COMPLETED");
                    event.reply(new EmbedBuilder()
                            .setColor(event.isFromType(ChannelType.TEXT) ? event.getSelfMember().getColor() : Color.GRAY)
                            .setImage(hr.getBody().getObject().getString("file"))
                            .build());
                    }

                // The API call failed
                @Override
                public void failed(UnirestException ue) {
                    System.out.println("no");
                    event.reactError();
                }

                // The API call was cancelled (this should never happen)
                @Override
                public void cancelled() {
                    System.out.println("nope");
                    event.reactError();
                }
            });
        }
}
