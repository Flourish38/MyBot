import listeners.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

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
        Scanner scanner = new Scanner(file);
        if(!scanner.hasNext()){
            System.out.println("Put the OAuth token of the bot into \"token.txt\"");
            return;
        }
        JDA jda = JDABuilder.createDefault(new Scanner(file).nextLine())
                .setChunkingFilter(ChunkingFilter.ALL)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(
                        new ReadyTimer(),
                        new PingCommand(),
                        new ShutdownCommand(),
                        new NicknameCommand(),
                        new GetPositionCommand(),
                        new MoveChannelCommand(),
                        new MakeChannelCommand()
                ).build();
    }
}
