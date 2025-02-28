package com.kirito5572.listener.main;

import com.kirito5572.App;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static com.kirito5572.commands.main.VersionCommand.getVersion;

public class activityChangeListener extends ListenerAdapter {
    private static JDA jda;
    private int i = 0;
    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        jda = event.getJDA();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                List<Guild> guilds = jda.getGuilds();
                int memberSize = 0;
                for (Guild guild : guilds) {
                    memberSize += guild.getMembers().size();
                }
                switch (i) {
                    case 0 ->
                            Objects.requireNonNull(jda.getPresence()).setActivity(Activity.playing("도움말:" + App.getPREFIX() + "명령어"));
                    case 1 ->
                            Objects.requireNonNull(jda.getPresence()).setActivity(Activity.playing(guilds.size() + "개의 서버, " + memberSize + " 명의 유저"));
                    case 2 -> Objects.requireNonNull(jda.getPresence()).setActivity(Activity.playing(getVersion()));
                    case 3 -> Objects.requireNonNull(jda.getPresence()).setActivity(Activity.playing("버그/개선 사항은 &제작자"));
                    case 4 ->
                            Objects.requireNonNull(jda.getPresence()).setActivity(Activity.playing("kirito5572#5572 제작"));
                }
                i++;
                if (i > 4) {
                    i = 0;
                }
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 5000);
    }
}
