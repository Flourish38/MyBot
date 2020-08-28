import listeners.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Bot {

    public static void main(String[] args) throws IOException, LoginException {
        var file = new File("token.txt");
        if(!file.exists()){
            System.out.println("A new file \"token.txt\" has been created. Put the OAuth token of the bot in that file.");
            System.out.println(file.createNewFile());
            return;
        }
        JDA jda = JDABuilder.createDefault(new Scanner(file).nextLine())
                .addEventListeners(
                        new ReadyTimer(),
                        new PingCommand(),
                        new ShutdownCommand()
                ).build();
    }
}
