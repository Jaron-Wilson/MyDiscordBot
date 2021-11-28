package me.jaron.test.events.image;

import me.jaron.test.Main;
import net.coobird.thumbnailator.Thumbnails;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class ImageManipulator extends ListenerAdapter {
    //https://github.com/coobird/thumbnailator <---- the api I used
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] message = e.getMessage().getContentRaw().split(" ");
        if(message.length > 1 && message[0].equalsIgnoreCase(Main.prefix + "image")){
            try{
                URL imageUrl = new URL(message[3]); //Takes the url and stores it as a URL object
                int width = Integer.parseInt(message[1]);
                int height = Integer.parseInt(message[2]);
                int rotateAmount;
                //If they dont specify a rotation amount in 4th parameter, set to 0(not rotate)
                if (5 == message.length){
                    rotateAmount = Integer.parseInt(message[4]);
                }else{
                    rotateAmount = 0;
                }
                InputStream in = imageUrl.openStream(); //Converts our image URL to an io stream
                BufferedImage image = ImageIO.read(in); //then to image
                OutputStream os = new ByteArrayOutputStream(); //Makes a new outputstream to be used to send the new image once it has been manipulated on the next line of code
                Thumbnails.of(image).forceSize(width,height).rotate(rotateAmount).outputFormat("png").toOutputStream(os); //takes the image, does things to it, sends to our output stream
                byte[] imageInByte = ((ByteArrayOutputStream) os).toByteArray(); //Converts the ByteArrayOutputStream to an actual Byte Array so we can send a file using discord java api
                e.getChannel().sendFile(imageInByte,"newfile.png").queue(); //Sends image in chat
                //Image sent
            }catch (IOException x){
                System.out.println(x);
            }
        }else if(message.length == 1 && message[0].equalsIgnoreCase("$image")){ //Info for the user on how to use command
            e.getChannel().sendMessage("To use the Image Manipulator, type the command like this(without brackets]: $image [width] [height] [image-url] [degrees to rotate(optional)]").queue();
        }
    }
}