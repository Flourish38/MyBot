package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class ShutdownCommand extends CommandListener {
    public ShutdownCommand(){
        super("shutdown");
    }

    @Override
    void command(@NotNull GuildMessageReceivedEvent event) {
        if(event.getAuthor().getIdLong() != BotConfig.BOT_ADMIN_ID) return;
        event.getChannel().sendMessage("Shutting down...").queue();
        ShutdownHandler.handle(event);
        event.getJDA().shutdown();
    }

    private static class ShutdownHandler {

        public static void handle(GuildMessageReceivedEvent event) {
        }
    }
}

