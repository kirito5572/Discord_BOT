package BOT.Commands.Moderator;

import BOT.Objects.ICommand;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class BotOwnerNoticeCommand implements ICommand {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        StringBuilder temp = new StringBuilder();
        for (String arg : args) {
            temp.append(arg).append(" ");
        }
        String message = temp.toString();
        if(message.contains("!here")) {
            message = message.replaceFirst("!here", "@here");
        }
        if(message.contains("!everyone")) {
            message = message.replaceFirst("!everyone", "@everyone");
        }
        if (event.getJDA().getSelfUser().getId().equals("592987181186940931")) {
            event.getJDA().getGuildById("617222347425972234").getTextChannelById("617224261139955722") //캣카페
                    .sendMessage(message).queue();
            event.getJDA().getGuildById("617757206929997895").getTextChannelById("617759661881556994") //데어라
                    .sendMessage(message).queue();
            event.getJDA().getGuildById("479625309788962816").getTextChannelById("479625309788962818") //심플
                    .sendMessage(message).queue();
            event.getJDA().getGuildById("508913681279483913").getTextChannelById("539470263121608740") //선우형
                    .sendMessage(message).queue();
            event.getJDA().getGuildById("453817631603032065").getTextChannelById("574856464347430914") //주먹밥
                    .sendMessage(message).queue();
            event.getJDA().getGuildById("607390893804093442").getTextChannelById("620082301413621771") //소프냥이
                    .sendMessage(message).queue();
            event.getJDA().getGuildById("607390203086372866").getTextChannelById("607543076734369792") //제이
                    .sendMessage(message).queue();
        } else {
            event.getJDA().getGuildById("600010501266866186").getTextChannelById("600015178821664769").sendMessage(message).queue();
        }
    }

    @Override
    public String getHelp() {
        return "디스코드 제작자가 공지하기 위해 쓰는 것입니다.";
    }

    @Override
    public String getInvoke() {
        return "공지";
    }

    @Override
    public String getSmallHelp() {
        return "other";
    }
}
