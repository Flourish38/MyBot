package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public abstract class CommandListener extends ListenerAdapter {
    protected final String command;

    public CommandListener(String command){
        this.command = command;
    }

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        var raw = event.getMessage().getContentRaw();

        if((raw.startsWith(event.getJDA().getSelfUser().getAsMention() + " " + command)) // ping as prefix
                || raw.startsWith(BotConfig.PREFIX + command)) // msg starts with prefix
        {
            command(event);
        }
    }

    abstract void command(@Nonnull GuildMessageReceivedEvent event);

}
