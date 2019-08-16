package BOT.Commands;

import BOT.App;
import BOT.Objects.ICommand;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class VersionCommand implements ICommand {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        if(App.isDEBUG_MODE() || App.isONLINE_DEBUG()) {
            event.getChannel().sendMessage("빌드 버젼 V 1.3.5 Preview 5(" + App.getTime() + ")").queue();
            return;
        }
        event.getChannel().sendMessage("빌드 버젼 V 1.3.5 Preview 4(" + App.getTime() + ")").queue();
        if(event.getGuild().getId().equals("508913681279483913")) {
            event.getChannel().sendMessage("봇 사이트: ``http://ritobot.mystrikingly.com``").queue();
            event.getChannel().sendMessage("업데이트 내역: \n" +
                    "```" +
                    " - .jar 파일 구동 .bat 머신 파일 변경\n" +
                    " - 버젼 정보를 RC 에서 Preview로 변경\n" +
                    " - 필터링 데이터베이스 추가" +
                    "```").queue();
        }
    }

    @Override
    public String getHelp() {
        return "빌드 버젼을 알려줍니다. \n" +
                "사용법: `" + App.getPREFIX() + getInvoke() + "`";
    }

    @Override
    public String getInvoke() {
        return "봇버젼";
    }

    @Override
    public String getSmallHelp() {
        return "봇의 빌드 버젼을 알려줍니다";
    }
}
