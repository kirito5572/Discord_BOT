package BOT.Commands.GameCommand;

import BOT.App;
import BOT.Objects.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RockPaperScissorsCommand implements ICommand {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        event.getMessage().delete().queue();
        String message;
        String mymessage;
        boolean botwin = false;
        boolean userwin = false;
        switch (args.get(0)) {
            case "가위":
                message = "가위";
                break;
            case "바위":
                message = "바위";
                break;
            case "보":
                message = "보";
                break;
            default:
                event.getChannel().sendMessage("가위/바위/보중 하나를 입력하세요").complete().delete().queueAfter(7, TimeUnit.SECONDS);
                return;
        }
        Random random = new Random();
        switch (random.nextInt(3)) {
            case 0:
                mymessage = "가위";
                break;
            case 1:
                mymessage = "바위";
                break;
            case 2:
                mymessage = "보";
                break;
            default: event.getChannel().sendMessage("에러가 발생했습니다.").complete().delete().queueAfter(7, TimeUnit.SECONDS);
                return;
        }
        switch (message) {
            case "가위":
                switch (mymessage) {
                    case "가위":
                        botwin = false;
                        userwin = false;
                        break;
                    case "바위":
                        botwin = true;
                        userwin = false;
                        break;
                    case "보":
                        botwin = false;
                        userwin = true;
                        break;
                }
                break;
            case "바위":
                switch (mymessage) {
                    case "가위":
                        botwin = false;
                        userwin = true;
                        break;
                    case "바위":
                        botwin = false;
                        userwin = false;
                        break;
                    case "보":
                        botwin = true;
                        userwin = false;
                        break;
                }
                break;
            case "보":
                switch (mymessage) {
                    case "가위":
                        botwin = true;
                        userwin = false;
                        break;
                    case "바위":
                        botwin = false;
                        userwin = true;
                        break;
                    case "보":
                        botwin = false;
                        userwin = false;
                        break;
                }
                break;
            default: return;
        }
        if(botwin && (!userwin)) {
            event.getChannel().sendMessage("봇이 이겼습니다.\n" +
                    "당신: " + message + "\n" +
                    "봇: " + mymessage).queue();
        } else if((!botwin) && userwin) {
            event.getChannel().sendMessage("당신이 이겼습니다.\n" +
                    "당신: " + message + "\n" +
                    "봇: " + mymessage).queue();
        } else if((!botwin) && (!userwin)) {
            event.getChannel().sendMessage("비겼습니다.\n" +
                    "당신: " + message + "\n" +
                    "봇: " + mymessage).queue();
        } else {
            event.getChannel().sendMessage("에러가 발생했습니다.").complete().delete().queueAfter(7, TimeUnit.SECONDS);
        }
    }

    @Override
    public String getHelp() {
        return "봇과 가위바위보를 합니다. \n" +
                "사용법: `" + App.getPREFIX() + getInvoke() + " <가위/바위/보>`";
    }

    @Override
    public String getInvoke() {
        return "가위바위보";
    }

    @Override
    public String getSmallHelp() {
        return "game";
    }
}
