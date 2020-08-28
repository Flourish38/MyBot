package listeners;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NicknameCommand extends ParameterCommand {

    public NicknameCommand() {
        super("nickname", "nick");
    }

    @Override
    void command(@NotNull GuildMessageReceivedEvent event, List<String> params) {
        if(params.size() < 1){
            event.getChannel().sendMessage("Usage: !nick <User> (Nickname...)").queue();
            return;
        }
        String userId = params.get(0);
        if(userId.startsWith("<@!"))
            userId = userId.substring(3, userId.length() - 1);
        if(!userId.matches("^\\d+$")) {
            event.getChannel().sendMessage("Invalid user ID \"" + userId + "\" in first parameter.").queue();
            return;
        }
        var member = event.getGuild().getMemberById(userId);
        if(member == null){
            event.getChannel().sendMessage("No member found with ID \"" + userId + "\"").queue();
            return;
        }
        StringBuilder nick = new StringBuilder();
        for(int i = 1; i < params.size(); i++){
            nick.append(params.get(i));
        }
        if(member.isOwner()){
            event.getChannel().sendMessage(member.getAsMention() + ": Change nick to \"" + nick.toString() + "\"").queue();
            return;
        }
        member.modifyNickname(nick.toString()).queue();
    }
}
