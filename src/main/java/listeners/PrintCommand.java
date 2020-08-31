package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PrintCommand extends ParameterCommand {
    public PrintCommand() {
        super("print", "p");
    }

    @Override
    void command(@NotNull GuildMessageReceivedEvent event, List<String> params) {
        StringBuilder sb = new StringBuilder();
        for(var p : params){
            sb.append(p).append("\n");
        }
        event.getChannel().sendMessage(sb.toString()).queue();
    }
}
