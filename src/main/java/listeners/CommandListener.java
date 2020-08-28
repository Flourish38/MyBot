package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class CommandListener extends ListenerAdapter {
    protected final List<String> commands;

    public CommandListener(String command, String... commands){
        this.commands = Stream.concat(Stream.of(command), Arrays.stream(commands))
                .sorted(Comparator.comparingInt(String::length).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        var raw = event.getMessage().getContentRaw();
        String mention = event.getJDA().getSelfUser().getAsMention();
        for(var command : commands)
        {
            if((raw.startsWith(mention + " " + command)) // ping as prefix
                    || raw.startsWith(BotConfig.PREFIX + command)) // msg starts with prefix
            {
                command(event);
                break;
            }
        }
    }

    abstract void command(@Nonnull GuildMessageReceivedEvent event);

}
