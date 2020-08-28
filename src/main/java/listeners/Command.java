package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public abstract class Command extends CommandListener {
    public Command(String command, String... commands) {
        super(command, commands);
    }

    @Override
    void command(@NotNull GuildMessageReceivedEvent event, String command) {
        command(event);
    }

    abstract void command(@Nonnull GuildMessageReceivedEvent event);
}
