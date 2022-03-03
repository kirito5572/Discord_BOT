package com.kirito5572.commands.main;

import com.kirito5572.App;
import com.kirito5572.objects.main.EventPackage;
import com.kirito5572.objects.main.ICommand;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PingCommand implements ICommand {
    @Override
    public void handle(@NotNull List<String> args, @NotNull EventPackage event) {
        long a = event.getJDA().getRestPing().complete();
        long b = event.getJDA().getGatewayPing();
        event.getChannel().sendMessage("퐁!").queue((message) ->
                message.editMessageFormat("main_core\n" +
                        "게이트웨이 핑: %sms\n" +
                        "Rest 핑: %sms", b, a).queue()
        );
    }

    @NotNull
    @Override
    public String getHelp() {
        return "레이턴시 ㄴㅇㄱ!\n" +
                "명령어: `" + App.getPREFIX() + getInvoke() + "`";
    }

    @NotNull
    @Override
    public String getInvoke() {
        return "핑";
    }

    @NotNull
    @Override
    public String getSmallHelp() {
        return "other";
    }
}