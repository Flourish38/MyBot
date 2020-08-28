package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class GetPositionCommand extends Command {
    public GetPositionCommand() {
        super("getpos", "channelpos", "getposition", "channelposition");
    }

    @Override
    void command(@NotNull GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage(Integer.toString(event.getChannel().getPosition())).queue();
    }
}
