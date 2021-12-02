package me.jaron.test.events.channels;

import net.dv8tion.jda.api.events.channel.category.update.CategoryUpdateNameEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CategoryUpdate extends ListenerAdapter {
    public CategoryUpdate() throws IOException {}
    List<String> list = Files.readAllLines(Paths.get("src/main/java/me/jaron/test/examplebot/config.txt"));//"config.txt"));


    @Override
    public void onCategoryUpdateName(@NotNull CategoryUpdateNameEvent event) {
        String newName = event.getNewName();
        String oldName = event.getOldName();
        int timeMade = event.getCategory().getTimeCreated().getDayOfYear();
        event.getCategory().createTextChannel("General" + timeMade).queue();
    }

}
