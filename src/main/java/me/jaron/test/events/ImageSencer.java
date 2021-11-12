package me.jaron.test.events;

import me.jaron.test.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class ImageSencer extends ListenerAdapter {

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        // Get channel name and send in current channel
        String channelName = event.getChannel().getName();
        String message = event.getMessage().getContentRaw();


// Upload and send an image to current channel
        if (message.equalsIgnoreCase(Main.prefix + "coolimage")) {
            event.getChannel().sendMessage(channelName).queue();
            try {
                URL url = new URL("https://images-na.ssl-images-amazon.com/images/I/71IHB9-yapL.png");
                BufferedImage img = ImageIO.read(url);
                File file = new File("coolimage.png"); // change the '.jpg' to whatever extension the image has
                ImageIO.write(img, "png", file); // again, change 'jpg' to the correct extension
                event.getChannel().sendFile(file).queue();
            } catch (Exception e) {
                event.getChannel().sendMessage("Error fetching image.").queue();
            }
        }


// Upload and send an image to current channel
        if (message.equalsIgnoreCase(Main.prefix + "chearup")) {
            event.getChannel().sendMessage(channelName).queue();
            try {
                URL url = new URL("https://quoteswell.com/wp-content/uploads/2017/07/cheer-up-meme-9-min.jpg");
                BufferedImage img = ImageIO.read(url);
                File file = new File("chearupmeme.jpg"); // change the '.jpg' to whatever extension the image has
                ImageIO.write(img, "jpg", file); // again, change 'jpg' to the correct extension
                event.getChannel().sendFile(file).queue();
            } catch (Exception e) {
                event.getChannel().sendMessage("Error fetching image.").queue();
            }
        }

        if (message.equalsIgnoreCase("im sad")) {

            Random rand = new Random(); //instance of random class
            int upperbound = 25;
            //generate random values from 0-24
            int int_random = rand.nextInt(upperbound);

            EmbedBuilder embedBuilder = new EmbedBuilder();

            if (int_random >= 5) {


                embedBuilder.setTitle("Channel you are on: " + channelName);
                embedBuilder.setColor(Color.GREEN.darker());
                embedBuilder.setDescription("Here Is your meme to cheer you up!");
                embedBuilder.setFooter("Have a great day and hope you chear up! ");
                embedBuilder.setImage("https://quoteswell.com/wp-content/uploads/2017/07/cheer-up-meme-9-min.jpg");
//            embedBuilder.setThumbnail("https://images-na.ssl-images-amazon.com/images/I/71IHB9-yapL.png");
                embedBuilder.setThumbnail("https://images.unsplash.com/photo-1610641818049-524b36d85b61?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8YmVhY2glMjBsYW5kc2NhcGV8ZW58MHx8MHx8&ixlib=rb-1.2.1&w=1000&q=80");
                // You can do other stuff, like footers and images, but this is the basic idea

                // To send it, do this:
                event.getChannel().sendMessage(embedBuilder.build()).queue();
            }
        } else {
            event.getChannel().sendMessage("Nope").queue();
            try {
                event.getChannel().sendFile(File.createTempFile("Not_Today", "NO!", new File("C:/Users/jaron/IdeaProjects/DiscordBot/src/main/java/me/jaron/test/FIles"))).queue();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}