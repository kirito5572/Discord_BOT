package BOT.Commands.GreenServerCustom;

import BOT.App;
import BOT.Objects.ICommand;
import com.jagrosh.jdautilities.commons.utils.FinderUtil;
import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class GreenServerMuteCommand implements ICommand {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd kk:mm");
        String time_st;
        TextChannel channel = event.getChannel();
        if(event.getGuild().getId().equals("600010501266866186")) {
            if(!event.getMember().hasPermission(Permission.MANAGE_ROLES)) {
                if(!event.getMember().getRoles().contains(event.getGuild().getRoleById("600012069559074822"))) {
                    channel.sendMessage(event.getMember().getAsMention() + ", 당신은 이 명령어를 사용할 권한이 없습니다.").queue();

                    return;
                }
            }
            if(args.isEmpty()) {
                event.getChannel().sendMessage("인수 부족 '" + App.getPREFIX() + "help" +
                        getInvoke() + "'").queue();
                return;
            }
            String users;
            String time;
            try {
                users = args.get(0);
            } catch (Exception e) {
                channel.sendMessage("유저 명이 없습니다.").queue();

                return;
            }
            try {
                time = args.get(1);
            } catch (Exception e) {
                channel.sendMessage("시간이 없습니다.").queue();

                return;
            }

            List<User> foundUsers = FinderUtil.findUsers(users, event.getGuild().getJDA());

            if(foundUsers.isEmpty()) {
                List<Member> foundMember = FinderUtil.findMembers(users, event.getGuild());
                if(foundMember.isEmpty()) {
                    event.getChannel().sendMessage("'" + users + "' 라는 유저는 없습니다.").queue();
                    return;
                }

                foundUsers = foundMember.stream().map(Member::getUser).collect(Collectors.toList());
            }
            User user = foundUsers.get(0);
            Member member = event.getGuild().getMember(user);

            if(member.hasPermission(Permission.MANAGE_ROLES) || member.hasPermission(Permission.MESSAGE_MANAGE) ||
                    member.hasPermission(Permission.MANAGE_PERMISSIONS) || member.hasPermission(Permission.MANAGE_SERVER) ||
                    member.hasPermission(Permission.ADMINISTRATOR)) {
                channel.sendMessage("이 사람에게는 채팅금지를 먹일 수 없습니다.").queue();

                return;
            }
            Role role;
            try {
                role = event.getGuild().getRolesByName("채팅 금지", true).get(0);
            } catch (Exception e) {
                channel.sendMessage("채팅 금지 역할을 먼저 생성해주세요.").queue();

                return;
            }
            long temp;
            Date date;
            if (time.contains("m")) {
                time = time.substring(0,time.indexOf("m"));
                try {
                    temp = Integer.parseInt(time);
                } catch (Exception e) {
                    channel.sendMessage("time의 숫자가 잘못 입력되었습니다.").queue();

                    return;
                }
                date = new Date();
                date.setTime(date.getTime() + temp * 60000L);
                time = String.valueOf(date.getTime());

            } else if(time.contains("h")) {
                time = time.substring(0,time.indexOf("h"));
                try {
                    temp = Integer.parseInt(time);
                } catch (Exception e) {
                    channel.sendMessage("time의 숫자가 잘못 입력되었습니다.").queue();

                    return;
                }

                date = new Date();
                date.setTime(date.getTime() + temp * 3600000L);
                time = String.valueOf(date.getTime());

            } else if(time.contains("d")) {
                time = time.substring(0,time.indexOf("d"));
                try {
                    temp = Integer.parseInt(time);
                } catch (Exception e) {
                    channel.sendMessage("time의 숫자가 잘못 입력되었습니다.").queue();

                    return;
                }
                date = new Date();
                date.setTime(date.getTime() + temp * 86400000L);
                time = String.valueOf(date.getTime());

            } else if(time.contains("M")) {
                time = time.substring(0,time.indexOf("M"));
                try {
                    temp = Integer.parseInt(time);
                } catch (Exception e) {
                    channel.sendMessage("time의 숫자가 잘못 입력되었습니다.").queue();

                    return;
                }
                date = new Date();
                date.setTime(date.getTime() + temp * 2629800000L);
                time = String.valueOf(date.getTime());
            } else {
                channel.sendMessage("time의 단위가 잘못되었습니다.").queue();

                return;
            }
            try {
                time_st = sdf.format(date);
                StringBuilder roles = new StringBuilder();
                for(int i = 0; i < member.getRoles().size(); i++) {
                    roles.append(member.getRoles().get(i).getAsMention()).append("\n");
                }
                Thread.sleep(1);
                EmbedBuilder builder = EmbedUtils.defaultEmbed()
                        .setTitle("채팅 금지 제재")
                        .addField("유저명", user.getName(), false)
                        .addField("멘션명", member.getAsMention(), false)
                        .addField("삭제되는 역할", roles.toString(), false)
                        .addField("제재 해제 시간", time_st, false)
                        .setColor(Color.RED);
                Thread.sleep(1);
                event.getGuild().getTextChannelById("609781460785692672").sendMessage(builder.build()).complete();
            } catch (Exception e) {
                channel.sendMessage("메세지를 보내기 전에 문제가 발생했습니다.").complete();

                e.printStackTrace();

                return;
            }
            time = time.substring(0, time.length() - 4);
            time += "0000";

            StringBuilder roles = new StringBuilder();
            for(int i = 0; i < member.getRoles().size(); i++) {
                roles.append("^").append(member.getRoles().get(i).getName()).append("$").append("\n");
            }

            try {
                filesave(time, user.getName(), roles.toString(), user.getId());
            } catch (IOException e) {
                channel.sendMessage("파일을 저장하면서 오류가 발생했습니다.").complete();

                e.printStackTrace();

                return;
            }

            event.getGuild().getController().removeRolesFromMember(member, member.getRoles()).complete();

            event.getGuild().getController().addSingleRoleToMember(member, role).queue();
        } else {
            channel.sendMessage("이 명령어는 이 서버에서 지원하지 않습니다.").complete();
        }
    }

    @Override
    public String getHelp() {
        return "채팅 금지를 먹입니다.";
    }

    @Override
    public String getInvoke() {
        return "채팅금지";
    }

    @Override
    public String getSmallHelp() {
        return "serverCustom";
    }

    private void filesave(String time, String userName, String roles, String discordID) throws IOException {
        String message = "Discord ID = " + discordID + "\n" +
                "Discord name = " +userName + "\n" +
                "Roles = " + roles;

        File file = new File("C:\\디스코드 유저 제재기록\\" + time + ".txt");
        FileWriter writer = null;


        writer = new FileWriter(file, true);
        writer.write(message);
        writer.flush();

        if(writer != null) writer.close();

    }
}
