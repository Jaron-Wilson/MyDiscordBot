package me.jaron.test.events.channels;

import net.dv8tion.jda.api.events.channel.category.update.CategoryUpdateNameEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CategoryUpdate extends ListenerAdapter {

    @Override
    public void onCategoryUpdateName(@NotNull CategoryUpdateNameEvent event) {
        String newName = event.getNewName();
        String oldName = event.getOldName();
        int timeMade = event.getCategory().getTimeCreated().getDayOfYear();
        event.getCategory().createTextChannel("General").queue();
    }

}
