package listeners;

import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MoveChannelCommand extends ParameterCommand{
    public MoveChannelCommand() {
        super("movechannel");
    }

    @Override
    void command(@NotNull GuildMessageReceivedEvent event, List<String> params) {
        if(params.size() < 1){
            event.getChannel().sendMessage("Usage: !movechannel (category...) <position>").queue();
            return;
        }
        String posString = (params.size() < 2 ? params.get(0) : params.get(params.size() - 1));
        if(!posString.matches("^\\d+$")) {
            event.getChannel().sendMessage("Invalid position \"" + posString + "\", must be an integer.").queue();
            return;
        }
        int position = Integer.parseInt(posString);
        if(params.size() > 1){
            StringBuilder sb = new StringBuilder(params.get(0));
            for(int i = 1; i < params.size() - 1; i++){
                sb.append(" ").append(params.get(i));
            }
            List<Category> categories = event.getGuild().getCategoriesByName(sb.toString(), true);
            if(categories.size() < 1){
                event.getChannel().sendMessage("No category \"" + sb.toString() + "\" found.").queue();
                return;
            }
            if(categories.size() > 1){
                event.getChannel().sendMessage("Warning: more than one category found with name \"" + params.get(0) + "\". Picking one...").queue();
            }
            event.getChannel().getManager().setParent(categories.get(0)).setPosition(position).queue();
        } else {
            event.getChannel().getManager().setPosition(position).queue();
        }

    }
}
