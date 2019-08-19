package BOT.Commands;

import BOT.App;
import BOT.Objects.CommandManager;
import BOT.Objects.ICommand;
import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.Collection;
import java.util.List;

public class HelpCommand implements ICommand {

    private final CommandManager manager;

    private int page = 0;
    private int x = 0;
    private int i = 0;

    public HelpCommand(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        String joined = String.join(" ", args);

        if (args.contains("1") || args.contains("2") || args.isEmpty() || args.contains("3") || args.contains("4") || args.contains("5")) {
            if(!joined.equals("")) {
                page = Integer.parseInt(joined);
            }
            generateAndSendEmbed(event);
            return;
        }


        ICommand command = manager.getCommand(joined);

        if(command == null) {
            event.getChannel().sendMessage( "`"+joined + "`는 존재하지 않는 명령어 입니다.\n" +
                    "`" + App.getPREFIX() + getInvoke() + "` 를 사용해 명령어 리스트를 확인하세요.").queue();
            return;
        }

        String message = "`" + command.getInvoke() + "`에 대한 설명\n" + command.getHelp();

        event.getChannel().sendMessage(message).queue();
    }

    private void generateAndSendEmbed(GuildMessageReceivedEvent event) {
        i = 0;
        EmbedBuilder builder = EmbedUtils.defaultEmbed().setTitle("명령어 리스트:");
        final int count = 10;
        if(page == 0) {
            x = 0;
        } else if(page == 1){
            x = 0;
        } else {
            x = (page - 1) * count;
        }
        final int y = x + count + 1;
        Collection<ICommand> Commands = manager.getCommands();

        Commands.forEach(iCommand -> {
            i++;
            if((x < i) && (i < y)) {
                if(iCommand.getSmallHelp().equals("")) {
                    i--;
                } else {
                    builder.addField(
                            "`" + iCommand.getInvoke() + "`\n",
                            iCommand.getSmallHelp(),
                            false
                    );
                    if ((i == (y - 1)) && (!iCommand.getInvoke().isEmpty())) {

                        builder.appendDescription("다음 명령어: " + getInvoke() + " " + (y / count + 1));
                    }
                }
            }
        });
        //TODO: Make a permission check to see if the bot can send embeds if not, send plain text
        event.getChannel().sendMessage(builder.build()).queue();
    }

    @Override
    public String getHelp() {
        return "모르는 명령어는 어디서? 여기서.\n" +
                "명령어: `" + App.getPREFIX() + getInvoke() + " [command]`";
    }

    @Override
    public String getInvoke() {
        return "명령어";
    }

    @Override
    public String getSmallHelp() {
        return "도움말";
    }
}
