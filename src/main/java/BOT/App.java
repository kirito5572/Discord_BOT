package BOT;/*
 * This Java source file was generated by the Gradle 'init' task.
 */

import BOT.Listener.*;
import BOT.Objects.CommandManager;
import BOT.Objects.SQL;
import BOT.Objects.config;
import BOT.Objects.getYoutubeSearch;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
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
    private static boolean DEBUG_MODE = false;
    private static boolean ONLINE_DEBUG = false;
    private static Date date;
    private static String Time;
    private static String PREFIX;
    private final Random random = new Random();

    private App() {
        config config = new config();
        date = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd aa hh:mm:ss z");
        Time = format1.format(date);
        getYoutubeSearch getYoutubeSearch = new getYoutubeSearch();
        CommandManager commandManager = new CommandManager();
        MemberCountListener memberCountListener = new MemberCountListener();
        Listener listener = new Listener(commandManager);
        filterListener filterlistener = new filterListener();
        salListener salListener = new salListener();
        ONIGIRIListener onigiriListener = new ONIGIRIListener();
        GreenServerMuteListener greenServerMuteListener = new GreenServerMuteListener();
        GreenServerNoticeListener greenServerNoticeListener = new GreenServerNoticeListener();
        workHomeListener workHomeListener = new workHomeListener();
        nekoDiscordMemberListener nekoDiscordMemberListener = new nekoDiscordMemberListener();
        GreenAutoDBWriteListener greenAutoDBWriteListener = new GreenAutoDBWriteListener();
        SteamServerStatusListener steamServerStatusListener = new SteamServerStatusListener();
        activityChangeListener activityChangeListener = new activityChangeListener();
        Logger logger = LoggerFactory.getLogger(App.class);

        StringBuilder TOKENreader = new StringBuilder();
        try {
            File file = new File("C:\\DiscordServerBotSecrets\\rito-bot\\TOKEN.txt");
            FileReader fileReader = new FileReader(file);
            int singalCh;
            while ((singalCh = fileReader.read()) != -1) {
                TOKENreader.append((char) singalCh);
            }
        } catch (Exception e) {

            StackTraceElement[] eStackTrace = e.getStackTrace();
            StringBuilder a = new StringBuilder();
            for (StackTraceElement stackTraceElement : eStackTrace) {
                a.append(stackTraceElement).append("\n");
            }
            logger.warn(a.toString());
        }
        StringBuilder TOKENreader_DEBUG = new StringBuilder();
        try {
            File file = new File("C:\\DiscordServerBotSecrets\\rito-bot\\TOKEN_DEBUG.txt");
            FileReader fileReader = new FileReader(file);
            int singalCh;
            while ((singalCh = fileReader.read()) != -1) {
                TOKENreader_DEBUG.append((char) singalCh);
            }
        } catch (Exception e) {

            StackTraceElement[] eStackTrace = e.getStackTrace();
            StringBuilder a = new StringBuilder();
            for (StackTraceElement stackTraceElement : eStackTrace) {
                a.append(stackTraceElement).append("\n");
            }
            logger.warn(a.toString());
        }
        StringBuilder TOKENreaderGreen = new StringBuilder();
        try {
            File file = new File("C:\\DiscordServerBotSecrets\\rito-bot\\Green_TOKEN.txt");
            FileReader fileReader = new FileReader(file);
            int singalCh;
            while ((singalCh = fileReader.read()) != -1) {
                TOKENreaderGreen.append((char) singalCh);
            }
        } catch (Exception e) {

            StackTraceElement[] eStackTrace = e.getStackTrace();
            StringBuilder a = new StringBuilder();
            for (StackTraceElement stackTraceElement : eStackTrace) {
                a.append(stackTraceElement).append("\n");
            }
            logger.warn(a.toString());
        }

        String TOKEN;
        String greenTOKEN;
        if (DEBUG_MODE) {
            TOKEN = TOKENreader_DEBUG.toString();
            PREFIX = Constants.PREFIX_DEBUG;
        } else if (!DEBUG_MODE && ONLINE_DEBUG) {
            TOKEN = TOKENreader.toString();
            PREFIX = Constants.PREFIX_DEBUG;
            greenTOKEN = TOKENreaderGreen.toString();
        } else {
            TOKEN = TOKENreader.toString();
            PREFIX = Constants.PREFIX;
            greenTOKEN = TOKENreaderGreen.toString();
            SQL sql = new SQL();
        }

        WebUtils.setUserAgent("Chrome 75.0.3770.100 kirito's discord bot/kirito5572#5572");

        EmbedUtils.setEmbedBuilder(
                () -> new EmbedBuilder()
                        .setColor(getRandomColor())
                        .setFooter("Made By kirito5572#5572", null)
        );

        try {
            logger.info("부팅");
            JDA jda = new JDABuilder(AccountType.BOT)
                    .setToken(TOKEN)
                    .setAutoReconnect(true)
                    .addEventListeners(memberCountListener, listener, filterlistener, salListener, onigiriListener, nekoDiscordMemberListener, steamServerStatusListener,
                            greenServerMuteListener, greenServerNoticeListener, workHomeListener, greenAutoDBWriteListener, activityChangeListener)
                    .build().awaitReady();
            logger.info("부팅완료");
        } catch (LoginException | InterruptedException e) {

            StackTraceElement[] eStackTrace = e.getStackTrace();
            StringBuilder a = new StringBuilder();
            for (StackTraceElement stackTraceElement : eStackTrace) {
                a.append(stackTraceElement).append("\n");
            }
            logger.warn(a.toString());
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
