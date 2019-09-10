package BOT.Commands.GreenServerCustom;

import BOT.App;
import BOT.Objects.ICommand;
import BOT.Objects.getSteamID;
import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.List;

public class gameServerBanCommand implements ICommand {
    private static String command = "서버밴";
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        TextChannel channel = event.getChannel();

        if(event.getGuild().getId().equals("600010501266866186")) {
            if(!event.getMember().hasPermission(Permission.MANAGE_ROLES)) {
                if(!event.getMember().getRoles().contains(event.getGuild().getRoleById("600012069559074822"))) {
                    channel.sendMessage(event.getMember().getAsMention() + ", 당신은 이 명령어를 사용할 권한이 없습니다.").queue();

                    return;
                }
            }
            if(args.isEmpty()) {
                event.getChannel().sendMessage("인수 부족 '" + App.getPREFIX() + "명령어" +
                        getInvoke() + "'").queue();
                return;
            }
            String SteamID;
            String time_non;
            StringBuilder reason;
            try {
                SteamID = args.get(0);

            } catch (Exception e) {
                channel.sendMessage("Steam ID가 입력되지 않았거나, 인수가 잘못 입력되었습니다.").queue();

                return;
            }
            try {
                time_non = args.get(1);
            } catch (Exception e) {
                channel.sendMessage("시간이 입력되지 않았거나, 인수가 맞지 않게 입력되었습니다.").queue();

                return;
            }
            reason = new StringBuilder();
            try {
                for(int i = 2; i <= args.size(); i++) {
                    reason.append(args.get(i));
                }
            } catch (Exception e) {
                if(reason.toString().equals("")) {
                    channel.sendMessage("사유가 입력되지 않았습니다.").queue();

                    return;
                }
            }

            TextChannel botChannel = event.getGuild().getTextChannelById("609055524851286027");
            TextChannel botChannel1 = event.getGuild().getTextChannelById("609057247116525650");
            TextChannel adminChannel = event.getGuild().getTextChannelById("609781460785692672");
            TextChannel reportChannel = event.getGuild().getTextChannelById("600015587544006679");

            String time = time_non;
            String[] returns = getSteamID.SteamID(SteamID);
            String NickName = returns[0];
            String ID = returns[1];

            int temp1;
            if (time.contains("m")) {
                time = time.substring(0,time.indexOf("m"));
                try {
                    temp1 = Integer.parseInt(time);
                } catch (Exception e) {
                    channel.sendMessage("time의 숫자가 잘못 입력되었습니다.").queue();

                    return;
                }
                time = String.valueOf(temp1);

            } else if(time.contains("h")) {
                time = time.substring(0,time.indexOf("h"));
                try {
                    temp1 = Integer.parseInt(time);
                } catch (Exception e) {
                    channel.sendMessage("time의 숫자가 잘못 입력되었습니다.").queue();

                    return;
                }
                time = String.valueOf((temp1 * 60));


            } else if(time.contains("d")) {
                time = time.substring(0,time.indexOf("d"));
                try {
                    temp1 = Integer.parseInt(time);
                } catch (Exception e) {
                    channel.sendMessage("time의 숫자가 잘못 입력되었습니다.").queue();

                    return;
                }
                time = String.valueOf((temp1 * 1440));

            } else if(time.contains("M")) {
                time = time.substring(0,time.indexOf("M"));
                try {
                    temp1 = Integer.parseInt(time);
                } catch (Exception e) {
                    channel.sendMessage("time의 숫자가 잘못 입력되었습니다.").queue();

                    return;
                }
                time = String.valueOf((temp1 * 43830));

            } else if(time.contains("y")) {
                time = time.substring(0,time.indexOf("y"));
                try {
                    temp1 = Integer.parseInt(time);
                } catch (Exception e) {
                    channel.sendMessage("time의 숫자가 잘못 입력되었습니다.").queue();

                    return;
                }
                time = String.valueOf((temp1 * 525960));
            } else if(time.contains("영구")) {
                time = "99999999";
            } else {
                channel.sendMessage("시간 단위가 틀렸거나, 스팀 닉네임의 띄워쓰기를 삭제하여 주세요.\n" +
                        "사용법: `" + App.getPREFIX() + "명령어/도움말/help" +getInvoke() + "`").queue();

                return;
            }

            NickName = NickName.replace(" ", "");
            NickName= NickName.replaceAll("\\p{Z}","");

            EmbedBuilder builder = EmbedUtils.defaultEmbed()
                    .setTitle("인 게임 정지 제재")
                    .setColor(Color.RED)
                    .addField("제재 대상자", NickName, false)
                    .addField("스팀 ID", ID, false)
                    .addField("정지 기간", time_non, false)
                    .addField("위반 규정 조항", reason.toString(), false)
                    .addField("제재 담당자", event.getAuthor().getAsMention(), false);

            adminChannel.sendMessage("" + event.getMember().getAsMention() + ", ` " + NickName + " ( " + ID + " )제재 완료\n" +
                    "기간: " + time + "`").queue();

            reportChannel.sendMessage(builder.build()).queue();


            System.out.println("oban " + NickName + " " + ID + " " + time);
            if(event.getGuild().getMemberById("580691748276142100").getOnlineStatus().equals(OnlineStatus.ONLINE) ||
                    event.getGuild().getMemberById("580691748276142100").getOnlineStatus().equals(OnlineStatus.IDLE)) {
                botChannel.sendMessage("+oban " + NickName + " " + ID + " " + time).queue();
            } else {
                event.getChannel().sendMessage("1서버가 종료상태이므로, 1,2,3,4 서버에 밴이 적용되지 않습니다.").queue();
            }
            if(event.getGuild().getMemberById("600676751118696448").getOnlineStatus().equals(OnlineStatus.ONLINE) ||
                    event.getGuild().getMemberById("600676751118696448").getOnlineStatus().equals(OnlineStatus.IDLE)) {
                botChannel1.sendMessage("+oban " + NickName + " " + ID + " " + time).queue();
            } else {
                event.getChannel().sendMessage("5서버가 종료상태이므로, 5서버에 밴이 적용되지 않습니다.").queue();
            }
            event.getChannel().sendMessage("밴 적용 완료").queue();

        } else {
            channel.sendMessage("이 명령어는 이 서버에서 지원하지 않습니다.").queue();
        }
    }

    @Override
    public String getHelp() {
        return "게임 서버에서 밴을 합니다. \n" +
                "사용법:" + App.getPREFIX() + getInvoke() + " <SteamID/스팀 닉네임> <시간> <사유>" +
                "시간 가능 인수: 1m(1min)/1h(1Hour)/1d(1day)/1M(1month)/1y(1year)/영구(99999999min)" +
                "스팀 닉네임은 꼭 붙혀서 써주시기 바랍니다.";
    }

    @Override
    public String getInvoke() {
        return command;
    }

    @Override
    public String getSmallHelp() {
        return "게임 서버 밴";
    }

    public static String getCommand() {
        return command;
    }
}
