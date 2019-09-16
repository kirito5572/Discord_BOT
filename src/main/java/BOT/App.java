package BOT;/*
 * This Java source file was generated by the Gradle 'init' task.
 */

import BOT.Listener.*;
import BOT.Objects.CommandManager;
import BOT.Objects.greenCommandManager;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class App {
    private static boolean DEBUG_MODE = true;
    private static boolean ONLINE_DEBUG = false;
    private String TOKEN;
    private String GreenTOKEN;
    private static Date date;
    private static String Time;
    private static String PREFIX;
    private final Random random = new Random();

    private App() {
        boolean debug;
        date = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat( "yyyy/MM/dd aa hh:mm:ss z");
        Time = format1.format(date);
        CommandManager commandManager = new CommandManager();
        greenCommandManager greenCommandManager = new greenCommandManager();
        MemberCountListener memberCountListener = new MemberCountListener(commandManager);
        Listener listener = new Listener(commandManager);
        filterListener filterlistener = new filterListener(commandManager);
        salListener salListener = new salListener(commandManager);
        ONIGIRIListener onigiriListener = new ONIGIRIListener();
        GreenServerMuteListener greenServerMuteListener = new GreenServerMuteListener();
        GreenAutoBanListener greenAutoBanListener = new GreenAutoBanListener();
        GreenServerNoticeListener greenServerNoticeListener = new GreenServerNoticeListener();
        greenListener greenListener = new greenListener(greenCommandManager);
        workHomeListener workHomeListener = new workHomeListener();

        StringBuilder TOKENreader = new StringBuilder();
        try {
            File file = new File("C:\\DiscordServerBotSecrets\\rito-bot\\TOKEN.txt");
            FileReader fileReader = new FileReader(file);
            int singalCh;
            while((singalCh = fileReader.read()) != -1) {
                TOKENreader.append((char) singalCh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder TOKENreader_DEBUG = new StringBuilder();
        try {
            File file = new File("C:\\DiscordServerBotSecrets\\rito-bot\\TOKEN_DEBUG.txt");
            FileReader fileReader = new FileReader(file);
            int singalCh;
            while((singalCh = fileReader.read()) != -1) {
                TOKENreader_DEBUG.append((char) singalCh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder TOKENreaderGreen= new StringBuilder();
        try {
            File file = new File("C:\\DiscordServerBotSecrets\\rito-bot\\Green_TOKEN.txt");
            FileReader fileReader = new FileReader(file);
            int singalCh;
            while((singalCh = fileReader.read()) != -1) {
                TOKENreaderGreen.append((char) singalCh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(DEBUG_MODE) {
            TOKEN = TOKENreader_DEBUG.toString();
            PREFIX = Constants.PREFIX_DEBUG;
            debug = false;
        } else if(!DEBUG_MODE && ONLINE_DEBUG) {
            TOKEN = TOKENreader.toString();
            PREFIX = Constants.PREFIX_DEBUG;
            debug = false;
        } else {
            TOKEN = TOKENreader.toString();
            PREFIX = Constants.PREFIX;
            GreenTOKEN = TOKENreaderGreen.toString();
            debug = true;
        }
        Logger logger = LoggerFactory.getLogger(App.class);

        WebUtils.setUserAgent("Chrome 75.0.3770.100 kirito's discord bot/kirito5572#5572");

        EmbedUtils.setEmbedBuilder(
                () -> new EmbedBuilder()
                        .setColor(getRandomColor())
                        .setFooter("Made By kirito5572#5572",null)
        );

        try {
            logger.info("부팅");
            JDA jda = new JDABuilder(AccountType.BOT)
                    .setToken(TOKEN)
                    .setAutoReconnect(true)
                    .addEventListener(memberCountListener)
                    .addEventListener(listener)
                    .addEventListener(filterlistener)
                    .addEventListener(salListener)
                    .addEventListener(onigiriListener)
                    .setGame(Game.streaming("사용법: "
                            + PREFIX + "명령어", "https://github.com/kirito5572/DiscordBOT"))
                    .build().awaitReady();
            logger.info("부팅완료");
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
        if(debug) {
            try {
                logger.info("부팅");
                JDA jda1 = new JDABuilder(AccountType.BOT)
                        .setToken(GreenTOKEN)
                        .setAutoReconnect(true)
                        .addEventListener(memberCountListener)
                        .addEventListener(greenListener)
                        .addEventListener(filterlistener)
                        .addEventListener(greenServerMuteListener)
                        .addEventListener(greenAutoBanListener)
                        .addEventListener(greenServerNoticeListener)
                        .addEventListener(workHomeListener)
                        .setGame(Game.streaming("사용법: "
                                + PREFIX + "명령어", "https://github.com/kirito5572/DiscordBOT"))
                        .build().awaitReady();
                logger.info("부팅완료");
            } catch (LoginException | InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    private Color getRandomColor() {
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();

        return new Color(r, g, b);
    }

    public static String getPREFIX() {
        return PREFIX;
    }

    public static boolean isDEBUG_MODE() {
        return DEBUG_MODE;
    }

    public static boolean isONLINE_DEBUG() {
        return ONLINE_DEBUG;
    }

    public static void main(String[] args) {
        new App();
    }

    public static String getTime() {
        return Time;
    }

    public static Date getDate() {
        return date;
    }
}
