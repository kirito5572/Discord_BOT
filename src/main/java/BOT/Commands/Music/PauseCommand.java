package BOT.Commands.Music;

import BOT.Music.GuildMusicManager;
import BOT.Music.PlayerManager;
import BOT.Objects.ICommand;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class PauseCommand implements ICommand {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        TextChannel channel = event.getChannel();
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(event.getGuild());
        AudioPlayer player = musicManager.player;

        if(player.getPlayingTrack() != null) {
            player.setPaused(true);
            channel.sendMessage("일시 정지 되었습니다.").queue();
        } else {
            channel.sendMessage("노래가 재생중이 아닙니다.").queue();
        }
    }

    @Override
    public String getHelp() {
        return "재생중인 노래를 일시정지 합니다.";
    }

    @Override
    public String getInvoke() {
        return "일시정지";
    }

    @Override
    public String getSmallHelp() {
        return "music";
    }
}
