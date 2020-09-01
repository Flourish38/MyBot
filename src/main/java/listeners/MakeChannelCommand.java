package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MakeChannelCommand extends ParameterCommand {
    public MakeChannelCommand() {
        super("makechannel", "createchannel", "newchannel");
    }

    @Override
    void command(@NotNull GuildMessageReceivedEvent event, List<String> params) {
        if(params.size() < 1){
            event.getChannel().sendMessage("Usage: !makechannel <name...>").queue();
            return;
        }
        event.getChannel().getParent().createTextChannel(String.join("-", params)).setPosition(event.getChannel().getPosition() + 1).queue();
    }
}
