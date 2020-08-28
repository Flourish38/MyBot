package listeners;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ReadyTimer extends ListenerAdapter {
    private final long startTime = System.currentTimeMillis();

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.println("Ready in " + (System.currentTimeMillis() - startTime) + "ms");
    }
}
