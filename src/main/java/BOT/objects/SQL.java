package BOT.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQL {
    private static final Logger logger = LoggerFactory.getLogger(SQL.class);
    private static int caseID;
    private static Connection connection;
    private static Connection loggingConnection;
    private static Statement statement;
    private static Statement loggingStatement;
    private static ResultSet resultSet;
    private static ResultSet loggingResultSet;
    private static String driverName;
    private static String url;
    private static String user;
    private static String password;
    public SQL() {
        //init
        StringBuilder caseIDBuilder = new StringBuilder();
        try {
            File file = new File("C:\\DiscordServerBotSecrets\\rito-bot\\caseID.txt");
            FileReader fileReader = new FileReader(file);
            int singalCh;
            while((singalCh = fileReader.read()) != -1) {
                caseIDBuilder.append((char) singalCh);
            }
        } catch (Exception e) {

            StackTraceElement[] eStackTrace = e.getStackTrace();
            StringBuilder a = new StringBuilder();
            for (StackTraceElement stackTraceElement : eStackTrace) {
                a.append(stackTraceElement).append("\n");
            }
            logger.warn(a.toString());
        }
        caseID = Integer.parseInt(caseIDBuilder.toString());
        StringBuilder SQLPassword = new StringBuilder();
        try {
            File file = new File("C:\\DiscordServerBotSecrets\\rito-bot\\SQLPassword.txt");
            FileReader fileReader = new FileReader(file);
            int singalCh;
            while((singalCh = fileReader.read()) != -1) {
                SQLPassword.append((char) singalCh);
            }
        } catch (Exception e) {

            StackTraceElement[] eStackTrace = e.getStackTrace();
            StringBuilder a = new StringBuilder();
            for (StackTraceElement stackTraceElement : eStackTrace) {
                a.append(stackTraceElement).append("\n");
            }
            logger.warn(a.toString());
        }
        StringBuilder endPoint = new StringBuilder();
        try {
            File file = new File("C:\\DiscordServerBotSecrets\\rito-bot\\endPoint.txt");
            FileReader fileReader = new FileReader(file);
            int singalCh;
            while((singalCh = fileReader.read()) != -1) {
                endPoint.append((char) singalCh);
            }
        } catch (Exception e) {

            StackTraceElement[] eStackTrace = e.getStackTrace();
            StringBuilder a = new StringBuilder();
            for (StackTraceElement stackTraceElement : eStackTrace) {
                a.append(stackTraceElement).append("\n");
            }
            logger.warn(a.toString());
        }
        driverName = "com.mysql.cj.jdbc.Driver";
        url = "jdbc:mysql://" + endPoint.toString() + "/ritobotDB?serverTimezone=UTC";
        user = "admin";
        password = SQLPassword.toString();
    }
    public static void SQLupload(String SteamID, String time, String reason, String confirmUser) {
        caseIDup();

        Date date = new Date();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd aa hh:mm:ss");
        String DBWriteTime = dayTime.format(date);
        String queryString = "INSERT INTO Sanction_Infor VALUE (\"" + caseID + "\",\""+ SteamID + "\", \"" + DBWriteTime + "\", \"" + time + "\", \"" + reason + "\", \"" + confirmUser + "\" );";


        System.out.println(queryString);
        try {
            Class.forName(driverName);

            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.executeUpdate(queryString);
            statement.close();
            connection.close();
        } catch (Exception e) {

            StackTraceElement[] eStackTrace = e.getStackTrace();
            StringBuilder a = new StringBuilder();
            for (StackTraceElement stackTraceElement : eStackTrace) {
                a.append(stackTraceElement).append("\n");
            }
            logger.warn(a.toString());
        }
    }
    public static String[][] SQLdownload(String SteamID) throws SQLException, ClassNotFoundException {
        String[][] data = new String[10][7];
        for (int i = 0; i < 10; i++) {
            for(int j = 0; j < 7; j++) {
                data[i][j] = null;
            }
        }

        String queryString = "SELECT FROM Sanction_Infor WHERE SteamID =\"" + SteamID +"\";";

        Class.forName(driverName);

        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
        resultSet = statement.executeQuery(queryString);
        statement.close();
        connection.close();
        int i = 0;
        do {
            data[i][0] = resultSet.getString("caseID");
            data[i][1] = resultSet.getString("SteamID");
            data[i][2] = resultSet.getString("sanctionTime");
            data[i][3] = resultSet.getString("endTime");
            data[i][4] = resultSet.getString("reason");
            data[i][5] = resultSet.getString("sendServer");
            data[i][6] = resultSet.getString("serverID");
            i++;
        } while (resultSet.next());
        return data;
    }
    private static void caseIDup() {
        caseID++;
        try {
            String message = String.valueOf(caseID);

            File file = new File("C:\\DiscordServerBotSecrets\\rito-bot\\caseID.txt");
            FileWriter writer;


            writer = new FileWriter(file, false);
            writer.write(message);
            writer.flush();

            if (writer != null) writer.close();
        } catch (IOException e) {

            StackTraceElement[] eStackTrace = e.getStackTrace();
            StringBuilder a = new StringBuilder();
            for (StackTraceElement stackTraceElement : eStackTrace) {
                a.append(stackTraceElement).append("\n");
            }
            logger.warn(a.toString());
        }
    }
    public static boolean loggingMessageUpLoad(String guildId, String messageId, String contentRaw) {
        String queryString = "INSERT INTO messageLogging VALUE (" + guildId + ","+ messageId + ", \"" + contentRaw + "\" );";
        System.out.println(queryString);
        try {
            Class.forName(driverName);

            loggingConnection = DriverManager.getConnection(url, user, password);
            loggingStatement = connection.createStatement();
            loggingStatement.executeUpdate(queryString);
            loggingStatement.close();
            loggingConnection.close();
        } catch (Exception e) {
            StackTraceElement[] eStackTrace = e.getStackTrace();
            StringBuilder a = new StringBuilder();
            for (StackTraceElement stackTraceElement : eStackTrace) {
                a.append(stackTraceElement).append("\n");
            }
            logger.warn(a.toString());
            return false;
        }
        return true;
    }
    public static String[] loggingMessageDownLoad(String guildId, String messageId) {
        String[] data = new String[2];
        String queryString = "SELECT FROM messageLogging WHERE MessageId=" + messageId + ", GuildId=" + guildId + ";";
        System.out.println(queryString);
        try {
            Class.forName(driverName);

            loggingConnection = DriverManager.getConnection(url, user, password);
            loggingStatement = connection.createStatement();
            loggingResultSet = loggingStatement.executeQuery(queryString);
            loggingStatement.close();
            loggingConnection.close();
        } catch (Exception e) {
            try {
                StackTraceElement[] eStackTrace = e.getStackTrace();
                StringBuilder a = new StringBuilder();
                for (StackTraceElement stackTraceElement : eStackTrace) {
                    a.append(stackTraceElement).append("\n");
                }
                logger.warn(a.toString());
                do {
                    data[0] = loggingResultSet.getString("ContentRaw");
                    data[1] = loggingResultSet.getString("Author");
                } while (resultSet.next());
            } catch(SQLException e1){
                StackTraceElement[] eStackTrace = e1.getStackTrace();
                StringBuilder a = new StringBuilder();
                for (StackTraceElement stackTraceElement : eStackTrace) {
                    a.append(stackTraceElement).append("\n");
                }
                logger.warn(a.toString());
            }
        }

        return data;
    }
}
