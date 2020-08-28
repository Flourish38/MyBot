package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public abstract class ParameterCommand extends CommandListener {
    public ParameterCommand(String command, String... commands) {
        super(command, commands);
    }

    @Override
    void command(@Nonnull GuildMessageReceivedEvent event) {
        String raw = event.getMessage().getContentRaw();
        for(var command : commands){
            raw = raw.substring(raw.indexOf(command) + command.length()).strip();
            command(event, Arrays.asList(raw.split(" ")));
            break;
        }
    }

    abstract void command(@Nonnull GuildMessageReceivedEvent event, List<String> params);
}