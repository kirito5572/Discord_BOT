package BOT.Listener;

import BOT.Objects.CommandManager;
import BOT.Objects.FilterList;
import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class filterListener extends ListenerAdapter {
    private final CommandManager manager;
    private final Logger logger = LoggerFactory.getLogger(Listener.class);
    public filterListener(CommandManager manager) {
        this.manager = manager;
    }
    private boolean publicflag = false;
    private String latestMessage = "";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User author = event.getAuthor();
        Message message = event.getMessage();
        Guild guild = event.getGuild();

        String[] List = FilterList.getList();
        String[] list = FilterList.getCharList();
        String[] Lists = FilterList.getWebList();
        String[] greenList = FilterList.getGreenList();
        String id = "";
        String rawMessage;
        try {
            rawMessage = message.getContentRaw();
        } catch (Exception e) {
            return;
        }
        if(!rawMessage.equals(latestMessage)) {
            latestMessage = rawMessage;
        } else {

            return;
        }
        Logger logger = LoggerFactory.getLogger(filterListener.class);
        try {
            if (guild.getId().equals("453817631603032065")) {
                return;

            }
        } catch (Exception ignored) {

        }
        for (String s : Lists) {
            if(rawMessage.contains(s)) {
                try {
                    try {
                        if (event.getMember().getRoles().contains(guild.getRolesByName("공개 처형", true).get(0))) {
                            message.delete().complete();
                            event.getChannel().sendMessage(author.getAsMention() + ", 공개처형자의 링크는 예외 없이 즉시 차단됩니다.").queue();
                        }
                    } catch (Exception ignored) {
                    }
                    if(guild.getSelfMember().getUser().getId().equals(event.getMember().getUser().getId())) {
                        return;
                    }
                    if(message.getMember().getUser().isBot()) {
                        return;
                    }
                    if(rawMessage.contains("cdn.discord")) {
                        return;
                    }
                    if(rawMessage.contains("discordapp")) {
                        return;
                    }
                    if(rawMessage.contains("tenor.com")) {
                        return;
                    }
                    if(rawMessage.contains("steam")) {
                        return;
                    }
                    if(guild.getId().equals("617222347425972234")) {
                        if(event.getChannel().getId().equals("620104084799750154")) {
                            return;
                        }
                    }
                    if(guild.getId().equals("600010501266866186")) {
                        if(event.getChannel().getId().equals("616229743741632533") || event.getChannel().getId().equals("621976688175349762") ||
                                event.getChannel().getId().equals("616234126663483392")) {
                            if(rawMessage.contains("youtube")) {
                                return;
                            }
                            if(rawMessage.contains("twitch")) {
                                return;
                            }
                            if(rawMessage.contains("youtu")) {
                                return;
                            }
                        }
                    }
                    if(guild.getId().equals("607390893804093442")) {
                        Role role = event.getGuild().getRoleById("616229894401294356");
                        if(event.getChannel().getId().equals("607543954476630016")) {
                            if(event.getMember().getRoles().contains(role)) {
                                return;
                            } else {
                                event.getChannel().sendMessage("이 채널에 링크 메세지를 보내기 위해서는 외무부역할을 부여받아야 합니다.").queue();
                                return;
                            }
                        }
                    }
                    if(rawMessage.contains("&검색")) {
                        return;
                    }
                    if(rawMessage.contains("&search")) {
                        return;
                    }
                    if(rawMessage.contains("&재생")) {
                        return;
                    }
                    if(rawMessage.contains("&play")) {
                        return;
                    }
                    if(rawMessage.contains("&p")) {
                        return;
                    }
                    if(message.getMember().hasPermission(Permission.ADMINISTRATOR) || message.getMember().hasPermission(Permission.MANAGE_ROLES)) {
                        logger.warn("관리자가 링크를 첨부했으나, 관리자는 필터링 되지 않습니다.");

                        return;
                    }
                    if(message.getMember().getUser().getId().equals("342951769627688960")) {
                        return;
                    }
                    if(!guild.getSelfMember().hasPermission(Permission.MESSAGE_MANAGE)) {
                        event.getChannel().sendMessage("링크가 입력되었으나 봇이 삭제할 권한이 없습니다.").queue();

                        return;
                    }
                    message.delete().queue();
                    event.getChannel().sendMessage(event.getMember().getAsMention() + ", 링크를 보내지 마세요.").queue();
                    return;
                } catch (Exception e) {
                    if(event.getJDA().getSelfUser().getId().equals("592987181186940931")) {
                        e.printStackTrace();
                    }
                }
            }
        }
        for (String value : list) {
            try {
                rawMessage = rawMessage.replaceAll(value, "");
            } catch (Exception ignored) {
            }
        }
        for (String s : List) {
            if (rawMessage.contains(s)) {
                try {
                    if(guild.getSelfMember().getUser().getId().equals(event.getMember().getUser().getId())) {

                        return;
                    }
                    if(message.getMember().hasPermission(Permission.ADMINISTRATOR) || message.getMember().hasPermission(Permission.MANAGE_ROLES)) {
                        logger.warn("관리자가 금지어를 말했으나, 관리자는 필터링 되지 않습니다.");

                        return;
                    }
                    if(!guild.getSelfMember().hasPermission(Permission.MESSAGE_MANAGE)) {
                        event.getChannel().sendMessage("금지어가 입력되었으나 봇이 삭제할 권한이 없습니다.").queue();

                        return;
                    }
                    logger.warn(author.getAsMention() + "가 금지어를 사용하였습니다.\n" +
                            "금지어: " + message.getContentRaw());
                    rawMessage = rawMessage.replaceFirst(s,"||[데이터 말소]||");

                    boolean flag = true;
                    publicflag = true;
                    while(flag) {
                        boolean tempflag = false;
                        for(String s1 : List) {
                            if(rawMessage.contains(s1)) {
                                rawMessage = rawMessage.replaceFirst(s1,"||[데이터 말소]||");
                                tempflag = true;
                            }
                        } if(!tempflag) {
                            flag = false;
                        }
                    }
                    message.delete().complete();
                    id = event.getChannel().sendMessage(rawMessage + "\n " + author.getAsMention() + " 금지어가 포함되어 있어 자동으로 필터링 되어, 필터링 된 문장을 출력합니다.").complete().getId();
                    if(guild.getId().equals("617222347425972234")) {
                        guild.getTextChannelById("617244182653829140").sendMessage(author.getAsMention() + "가 금지어를 사용하였습니다.\n" +
                                "금지어: " + message.getContentRaw()).queue();
                    } else if (guild.getId().equals("617757206929997895")) {
                        guild.getTextChannelById("617760924714926113").sendMessage(author.getAsMention() + "가 금지어를 사용하였습니다.\n" +
                                "금지어: " + message.getContentRaw()).queue();
                    } else if(guild.getId().equals("607390893804093442")) {
                        guild.getTextChannelById("620091943522664466").sendMessage(author.getAsMention() + "가 금지어를 사용하였습니다.\n" +
                                "금지어: " + message.getContentRaw()).queue();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if(guild.getId().equals("600010501266866186")) {
            for (String s : greenList) {
                if(rawMessage.contains(s)) {
                    if(guild.getSelfMember().getUser().getId().equals(event.getMember().getUser().getId())) {

                        return;
                    }
                    if(message.getMember().hasPermission(Permission.ADMINISTRATOR) || message.getMember().hasPermission(Permission.MANAGE_ROLES)) {
                        logger.warn("관리자가 금지어를 말했으나, 관리자는 필터링 되지 않습니다.");

                        return;
                    }
                    message.delete().complete();
                    event.getChannel().sendMessage("타 서버 발언은 모두 차단됩니다.").queue();
                }
            }
        }
        Message messages = event.getMessage();

        Role role;
        try {
            role = guild.getRolesByName("공개 처형", true).get(0);
        } catch (Exception e) {
            return;
        }
        int time;
        if(guild.getId().equals("453817631603032065")) {
            time = 10;
        } else if (guild.getId().equals("600010501266866186")) {
            time = 5;
        } else if (guild.getId().equals("617222347425972234")) {
            time = 2;
        } else if (guild.getId().equals("607390893804093442")){
            time = 7;
        } else {
            time = 7;
        }
        try {
            if (messages.getMember().getRoles().contains(role)) {
                if(publicflag) {
                    event.getChannel().deleteMessageById(id).queueAfter(time, TimeUnit.SECONDS);
                    EmbedBuilder embedBuilder = EmbedUtils.defaultEmbed()
                            .addField("공개 처형", "당신의 필터링된 메세지도 " + time + "초후 자동으로 삭제됩니다.", true);
                    event.getChannel().sendMessage(embedBuilder.build()).complete().delete().queueAfter(time, TimeUnit.SECONDS);
                } else {
                    messages.delete().queueAfter(time, TimeUnit.SECONDS);
                    EmbedBuilder embedBuilder = EmbedUtils.defaultEmbed()
                            .addField("공개 처형", "당신의 메세지는 " + time + "초후 자동으로 삭제됩니다.", true);
                    event.getChannel().sendMessage(embedBuilder.build()).complete().delete().queueAfter(time, TimeUnit.SECONDS);
                }
            }
        } catch (Exception ignored) {

        }
    }
}
