package me.jaron.test.events.image;

import me.jaron.test.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Random;

public class ImageSencer extends ListenerAdapter {

    public String description = "Here Is your meme to cheer you up!";

    public String[] images = new String[]{
            //PUT IN IMAGES HERE:
            "https://quoteswell.com/wp-content/uploads/2017/07/cheer-up-meme-9-min.jpg", "https://images.unsplash.com/photo-1610641818049-524b36d85b61?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8YmVhY2glMjBsYW5kc2NhcGV8ZW58MHx8MHx8&ixlib=rb-1.2.1&w=1000&q=80", "https://quoteswell.com/wp-content/uploads/2017/07/cheer-up-meme-9-min.jpg", "https://images-na.ssl-images-amazon.com/images/I/71IHB9-yapL.png"
    };
    public String[] titlesArray = new String[] {
            //PUT IN TITLES HERE:
            "URDUMB!", "UR GAY", "THATS Nice"
    };

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        // Get channel name and send in current channel
        String channelName = event.getChannel().getName();
        String message = event.getMessage().getContentRaw();


                        // Upload and send an image to current channel
        if (message.equalsIgnoreCase(Main.prefix + "coolimage") || message.equalsIgnoreCase( "coolimage")) {
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
            // Upload and send an image to current channel
        }

        if ((message.equalsIgnoreCase( Main.prefix + "image") || message.equalsIgnoreCase("image"))) {

            Random rand = new Random(); //instance of random class
            int upperbound = 10;
            //generate random values from 0-24
            int int_random = rand.nextInt(upperbound);
            int int_randomMIN = rand.nextInt(5);
            int titleLength = rand.nextInt(titlesArray.length);
            int arrayIntThumbnail = rand.nextInt(images.length);
            int imagelength = rand.nextInt(images.length);

            EmbedBuilder embedBuilder = new EmbedBuilder();

             if ((int_random <= 4) && (int_random > 1)) {
                if (!(int_randomMIN != 1)) {
                    embedBuilder.setTitle(titlesArray[titleLength]);
                    embedBuilder.setDescription("Here Is your meme to cheer you up!");
                } else if (!(int_randomMIN != 2)) {
                    embedBuilder.setTitle(titlesArray[titleLength]);
                    embedBuilder.setDescription("Here Is your meme to cheer you up!");
                } else {
                    embedBuilder.setTitle(titlesArray[titleLength]);
                    embedBuilder.setDescription("Here Is your meme to cheer you up!");
                }

                embedBuilder.setColor(Color.GREEN.darker());

                embedBuilder.setImage(images[imagelength]);
                embedBuilder.setThumbnail(images[imagelength]);
                // You can do other stuff, like footers and images, but this is the basic idea
                embedBuilder.setFooter("Channel name you are on: " + channelName);

                // To send it, do this:
                event.getChannel().sendMessage(embedBuilder.build()).queue();
            } else if ((int_random >= 5)) {
                if (!(int_randomMIN != 1)) {
                    embedBuilder.setTitle(titlesArray[titleLength]);
                    embedBuilder.setDescription(description);
                } else if (!(int_randomMIN != 2)) {
                    embedBuilder.setTitle(titlesArray[titleLength]);
                    embedBuilder.setDescription(description);
                } else {
                    embedBuilder.setTitle(titlesArray[titleLength]);
                    embedBuilder.setDescription(description);
                }

                embedBuilder.setColor(Color.MAGENTA.darker());
                embedBuilder.setImage(images[imagelength]);
                embedBuilder.setThumbnail(images[imagelength]);
                // You can do other stuff, like footers and images, but this is the basic idea
                embedBuilder.setFooter("Channel name you are on: " + channelName);

                // To send it, do this:
                event.getChannel().sendMessage(embedBuilder.build()).queue();
            }
        }
    }
}