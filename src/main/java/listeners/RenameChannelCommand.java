package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RenameChannelCommand extends ParameterCommand {
    public RenameChannelCommand() {
        super("renamechannel");
    }

    @Override
    void command(@NotNull GuildMessageReceivedEvent event, List<String> params) {
        if(params.size() < 1){
            event.getChannel().sendMessage("Usage: !renamechannel <name>").queue();
            return;
        }
        event.getChannel().getManager().setName(String.join("-", params)).queue();
    }
}