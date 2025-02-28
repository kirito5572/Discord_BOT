package com.kirito5572.objects.main;

import com.kirito5572.commands.main.moderator.configCommand;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class SQL {
    static class Data {
        public JsonArray data;
    }
    private static final Logger logger = LoggerFactory.getLogger(SQL.class);
    private static int caseID;
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static ResultSet resultSet6;
    private static String url;
    private static String user;
    private static String password;

    public SQL() {
        StringBuilder SQLPassword = new StringBuilder();
        File file = new File("C:\\DiscordServerBotSecrets\\rito-bot\\SQLPassword.txt");

        try (FileReader fileReader = new FileReader(file)){
            int signalCh;
            while((signalCh = fileReader.read()) != -1) {
                SQLPassword.append((char) signalCh);
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
        file = new File("C:\\DiscordServerBotSecrets\\rito-bot\\endPoint.txt");
        try (FileReader fileReader = new FileReader(file)){
            int signalCh;
            while((signalCh = fileReader.read()) != -1) {
                endPoint.append((char) signalCh);
            }
        } catch (Exception e) {

            StackTraceElement[] eStackTrace = e.getStackTrace();
            StringBuilder a = new StringBuilder();
            for (StackTraceElement stackTraceElement : eStackTrace) {
                a.append(stackTraceElement).append("\n");
            }
            logger.warn(a.toString());
        }
        String driverName = "com.mysql.cj.jdbc.Driver";
        url = "jdbc:mysql://" + endPoint + "/ritobotDB?serverTimezone=UTC";
        user = "ritobot";
        password = SQLPassword.toString();
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

            writer.close();
        } catch (IOException e) {

            StackTraceElement[] eStackTrace = e.getStackTrace();
            StringBuilder a = new StringBuilder();
            for (StackTraceElement stackTraceElement : eStackTrace) {
                a.append(stackTraceElement).append("\n");
            }
            logger.warn(a.toString());
        }
    }
    public static final int color_guild = 1;
    public static final int filter = 2;
    public static final int link_filter = 3;
    public static final int kill_filter = 4;
    public static final int lewdneko =5;
    public static final int color_role = 6;
    public static final int textLogging = 7;
    public static final int channelLogging = 8;
    public static final int memberLogging = 9;
    public static final int notice = 10;
    public static final int filterlog = 11;
    public static final int botchannel = 12;
    public static final int customFilter = 13;

    @NotNull
    public static configCommand.ConfigData configDownLoad(String guildId) {
        configCommand.ConfigData configData = new configCommand.ConfigData();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String queryString;
            queryString = "SELECT * FROM ritobot_config.color_command_guild WHERE guildId =" + guildId;
            statement = connection.createStatement();
            resultSet6 = statement.executeQuery(queryString);
            while (resultSet6.next()) {
                configData.colorCommand = resultSet6.getString("disable").equals("0");
            }
            resultSet6.close();
            queryString = "SELECT * FROM ritobot_config.filter_guild WHERE guildId =" + guildId;
            resultSet6 = statement.executeQuery(queryString);
            while (resultSet6.next()) {
                configData.filter = resultSet6.getString("disable").equals("0");
            }
            queryString = "SELECT * FROM ritobot_config.link_filter_guild WHERE guildId =" + guildId;
            resultSet6 = statement.executeQuery(queryString);
            while (resultSet6.next()) {
                configData.linkFilter = resultSet6.getString("disable").equals("0");
            }
            queryString = "SELECT * FROM ritobot_config.kill_filter_guild WHERE guildId =" + guildId;
            resultSet6 = statement.executeQuery(queryString);
            while (resultSet6.next()) {
                configData.executionCommand = resultSet6.getString("disable").equals("0");
            }
            queryString = "SELECT * FROM ritobot_config.lewdneko_command WHERE guildId =" + guildId;
            resultSet6 = statement.executeQuery(queryString);
            while (resultSet6.next()) {
                configData.lewdCommand = resultSet6.getString("disable").equals("0");
            }
            queryString = "SELECT * FROM ritobot_config.logging_enable WHERE guildId =" + guildId;
            resultSet6 = statement.executeQuery(queryString);
            while (resultSet6.next()) {
                configData.enableChatLog = resultSet6.getString("text_logging").equals("1");
                configData.enableChannelLog = resultSet6.getString("channel_logging").equals("1");
                configData.enableMemberLog = resultSet6.getString("member_logging").equals("1");
            }
            queryString = "SELECT * FROM ritobot_config.color_command_role WHERE guildId =" + guildId;
            resultSet6 = statement.executeQuery(queryString);
            configData.roleColorCommand = resultSet6.next();
            queryString = "SELECT * FROM ritobot_config.notice WHERE guildId =" + guildId;
            resultSet6 = statement.executeQuery(queryString);
            if (resultSet6.next()) {
                configData.enableNotice = resultSet6.getString("disable").equals("0");
                if(configData.enableNotice) {
                    configData.noticeChannelId = resultSet6.getString("channelId");
                }
            }
            queryString = "SELECT * FROM ritobot_config.filter_output_channel WHERE guildId =" + guildId;
            resultSet6 = statement.executeQuery(queryString);
            if (resultSet6.next()) {
                configData.enableFilterLog = resultSet6.getString("disable").equals("0");
                if(configData.enableFilterLog) {
                    configData.filterChannelId = resultSet6.getString("channelId");
                }
            }
            queryString = "SELECT * FROM ritobot_config.bot_channel WHERE guildId =" + guildId;
            resultSet6 = statement.executeQuery(queryString);
            if (resultSet6.next()) {
                configData.enableBotChannel = resultSet6.getString("disable").equals("0");
                if(configData.enableBotChannel) {
                    configData.botChannelId = resultSet6.getString("channelId");
                }
            }
            queryString = "SELECT * FROM ritobot_config.log_channel WHERE guildId =" + guildId;
            resultSet6 = statement.executeQuery(queryString);
            if (resultSet6.next()) {
                configData.chatLogChannelId = resultSet6.getString("messageLog");
                configData.channelLogChannelId = resultSet6.getString("channelLog");
                configData.memberLogChannelId = resultSet6.getString("memberLog");
            } else {
                configData.chatLogChannelId = "0";
                configData.channelLogChannelId = "0";
                configData.memberLogChannelId = "0";
            }
            queryString = "SELECT * FROM ritobot_config.custom_Filter WHERE guildId =" + guildId;
            resultSet6 = statement.executeQuery(queryString);
            if (resultSet6.next()) {
                configData.enableCustomFilter = resultSet6.getString("disable").equals("0");
                if(configData.enableCustomFilter) {
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ritobot_config.custom_Filter WHERE guildId = ?");
                    preparedStatement.setString(1, guildId);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    configData.customFilterList = new ArrayList<>();
                    if(resultSet.next()) {
                        Data a = new Gson().fromJson(JsonParser.parseString(resultSet.getString("data")), Data.class);
                        for(JsonElement jsonElement : a.data) {
                            configData.customFilterList.add(jsonElement.getAsString());
                        }
                    }
                }
            }

            resultSet6.close();
        } catch (Exception e) {
            e.printStackTrace();
            configData = new configCommand.ConfigData();
        }
        return configData;
    }

    public static void configSetup(@NotNull String guildId, String roleId, boolean insert) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String queryString;
            if (guildId.length() < 15) {
                return;
            }
            if (insert) {
                queryString = "INSERT INTO ritobot_config.color_command_role VALUES (" + guildId + ", " + roleId + ")";
            } else {
                queryString = "DELETE FROM ritobot_config.color_command_role WHERE guildId =" + guildId + " AND roleId =" + roleId;
            }
            System.out.println(queryString);
            statement = connection.createStatement();
            statement.executeUpdate(queryString);
            resultSet6.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void configSetup(@NotNull String guildId, String disable, String channelId, int which) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String queryString;
            if (guildId.length() < 15) {
                return;
            }
            switch (which) {
                case textLogging -> {
                    queryString = "UPDATE ritobot_config.log_channel SET messageLog = " + channelId + " WHERE guildId =" + guildId;
                    System.out.println(queryString);
                    statement = connection.createStatement();
                    statement.executeUpdate(queryString);
                }
                case channelLogging -> {
                    queryString = "UPDATE ritobot_config.log_channel SET channelLog = " + channelId + " WHERE guildId =" + guildId;
                    System.out.println(queryString);
                    statement = connection.createStatement();
                    statement.executeUpdate(queryString);
                }
                case memberLogging -> {
                    queryString = "UPDATE ritobot_config.log_channel SET memberLog = " + channelId + " WHERE guildId =" + guildId;
                    System.out.println(queryString);
                    statement = connection.createStatement();
                    statement.executeUpdate(queryString);
                }
                case notice -> {
                    queryString = "UPDATE ritobot_config.notice SET disable=" + disable + ", channelId = " + channelId + " WHERE guildId =" + guildId;
                    System.out.println(queryString);
                    statement = connection.createStatement();
                    statement.executeUpdate(queryString);
                }
                case filterlog -> {
                    queryString = "UPDATE ritobot_config.filter_output_channel SET disable=" + disable + ", channelId = " + channelId + " WHERE guildId =" + guildId;
                    System.out.println(queryString);
                    statement = connection.createStatement();
                    statement.executeUpdate(queryString);
                }
                case botchannel -> {
                    queryString = "UPDATE ritobot_config.bot_channel SET disable=" + disable + ", channelId = " + channelId + " WHERE guildId =" + guildId;
                    System.out.println(queryString);
                    statement = connection.createStatement();
                    statement.executeUpdate(queryString);
                }
                case customFilter -> {
                    queryString = "UPDATE ritobot_config.custom_Filter SET disable=" + disable + " WHERE guildId =" + guildId;
                    System.out.println(queryString);
                    statement = connection.createStatement();
                    statement.executeUpdate(queryString);
                }
            }
            resultSet6.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void configSetup(@NotNull String guildId, List<String> list) {
        try {
            PreparedStatement
                    preparedStatement = connection.prepareStatement("UPDATE ritobot_config.custom_Filter SET custom_Filter.data =? WHERE guildId = ?");
            preparedStatement.setString(2, guildId);
            StringBuilder builder = new StringBuilder();
            builder.append("{\"data\":[\"");
            for(String a : list) {
                builder.append(a).append("\", \"");
            }
            String a = builder.toString();
            a = a.substring(0, a.length() - 4);
            a = a + "\"]}";
            System.out.println(a);
            preparedStatement.setString(1, a);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void configSetup(@NotNull String guildId, int option, @NotNull String disable) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String queryString = "";
            if (guildId.length() < 15) {
                return;
            }
            switch (option) {
                case color_guild ->
                        queryString = String.format("UPDATE ritobot_config.color_command_guild SET disable=%s WHERE guildId =%s", disable, guildId);
                case filter ->
                        queryString = String.format("UPDATE ritobot_config.filter_guild SET disable=%s WHERE guildId =%s", disable, guildId);
                case kill_filter ->
                        queryString = String.format("UPDATE ritobot_config.kill_filter_guild SET disable=%s WHERE guildId =%s", disable, guildId);
                case lewdneko ->
                        queryString = String.format("UPDATE ritobot_config.lewdneko_command SET disable=%s WHERE guildId =%s", disable, guildId);
                case link_filter ->
                        queryString = String.format("UPDATE ritobot_config.link_filter_guild SET disable=%s WHERE guildId =%s", disable, guildId);
                case color_role -> {
                    if (disable.equals("1")) {
                        queryString = String.format("DELETE FROM ritobot_config.color_command_role WHERE guildId =%s", guildId);
                    }
                }
                case textLogging ->
                        queryString = String.format("UPDATE ritobot_config.logging_enable SET text_logging=%s WHERE guildId =%s", disable.equals("1") ? "0" : "1", guildId);
                case channelLogging ->
                        queryString = String.format("UPDATE ritobot_config.logging_enable SET channel_logging=%s WHERE guildId =%s", disable.equals("1") ? "0" : "1", guildId);
                case memberLogging ->
                        queryString = String.format("UPDATE ritobot_config.logging_enable SET member_logging=%s WHERE guildId =%s", disable.equals("1") ? "0" : "1", guildId);
                case notice ->
                        queryString = String.format("UPDATE ritobot_config.notice SET disable=%s WHERE guildId =%s", disable, guildId);
                case filterlog ->
                        queryString = String.format("UPDATE ritobot_config.filter_output_channel SET disable=%s WHERE guildId =%s", disable, guildId);
                case botchannel ->
                        queryString = String.format("UPDATE ritobot_config.bot_channel SET disable=%s WHERE guildId =%s", disable, guildId);
            }
            System.out.println(queryString);
            statement = connection.createStatement();
            statement.executeUpdate(queryString);
            resultSet6.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String configDownLoad(String guildId, int option) {
        String return_data = null;
        switch (option) {
            case notice -> {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String queryString;
                    queryString = String.format("SELECT * FROM ritobot_config.notice WHERE guildId=%s", guildId);
                    System.out.println(queryString);
                    statement = connection.createStatement();
                    resultSet6 = statement.executeQuery(queryString);
                    if (resultSet6.next()) {
                        if (resultSet6.getString("disable").equals("0")) {
                            return_data = resultSet6.getString("channelId");
                        }
                    }
                    resultSet6.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    return_data = "error";
                }
            }
            case filterlog -> {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String queryString;
                    queryString = String.format("SELECT * FROM ritobot_config.filter_output_channel WHERE guildId=%s", guildId);
                    System.out.println(queryString);
                    statement = connection.createStatement();
                    resultSet6 = statement.executeQuery(queryString);
                    if (resultSet6.next()) {
                        if (resultSet6.getString("disable").equals("0")) {
                            return_data = resultSet6.getString("channelId");
                        }
                    }
                    resultSet6.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    return_data = "error";
                }
            }
            case botchannel -> {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String queryString;
                    queryString = String.format("SELECT * FROM ritobot_config.bot_channel WHERE guildId=%s", guildId);
                    System.out.println(queryString);
                    statement = connection.createStatement();
                    resultSet6 = statement.executeQuery(queryString);
                    if (resultSet6.next()) {
                        if (resultSet6.getString("disable").equals("0")) {
                            return_data = resultSet6.getString("channelId");
                        }
                    }
                    resultSet6.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    return_data = "error";
                }
            }
        }
        return return_data;
    }

    public static String[] configDownLoad_array(String guildId, int option) {
        String[] return_data = new String[0];
        switch (option) {
            case color_role -> {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String queryString;
                    int i = 0;
                    queryString = String.format("SELECT * FROM ritobot_config.color_command_role WHERE guildId=%s", guildId);
                    statement = connection.createStatement();
                    resultSet6 = statement.executeQuery(queryString);
                    return_data = new String[resultSet6.getFetchSize()];
                    while (resultSet6.next()) {
                        if (i == 0) {
                            return_data = new String[]{
                                    resultSet6.getString("roleId")};
                            i++;
                        } else {
                            String[] newArray = Arrays.copyOf(return_data, return_data.length + 1);
                            newArray[return_data.length] = resultSet6.getString("roleId");
                            return_data = newArray;
                        }
                    }
                    resultSet6.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    return_data = new String[]{"error"};
                }
            }
            case customFilter -> {
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ritobot_config.custom_Filter WHERE guildId = ?");
                    preparedStatement.setString(1, guildId);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    List<String> arrayList = new ArrayList<>();
                    if (resultSet.next()) {
                        Data a = new Gson().fromJson(JsonParser.parseString(resultSet.getString("data")), Data.class);
                        for (JsonElement jsonElement : a.data) {
                            arrayList.add(jsonElement.getAsString());
                        }
                    }
                    return_data = arrayList.toArray(new String[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                    return_data = new String[]{"error"};
                }
            }
        }
        return return_data;
    }


    @NotNull
    public static String configDownLoad(int option, String guildId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String queryString;
            if(option == filterlog) {
                queryString = "SELECT * FROM ritobot_config.filter_output_channel WHERE disable = 0 AND guildId =" + guildId;
                statement = connection.createStatement();
                resultSet6 = statement.executeQuery(queryString);
                if(resultSet6.next()) {
                    return resultSet6.getString("channelId");
                } else {
                    return "null";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }
    @NotNull
    public static Map<String, String> configDownLoad(int option, boolean temp) {
        Map<String, String> return_data = new HashMap<>();
        switch (option) {
            case botchannel -> {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String queryString;
                    int i = 0;
                    queryString = "SELECT * FROM ritobot_config.bot_channel";
                    statement = connection.createStatement();
                    resultSet6 = statement.executeQuery(queryString);
                    while (resultSet6.next()) {
                        String channelId;
                        if (resultSet6.getString("disable").equals("0")) {
                            channelId = resultSet6.getString("channelId");
                        } else {
                            channelId = "0";
                        }
                        return_data.put(resultSet6.getString("guildId"), channelId);
                    }
                    resultSet6.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    return_data.put("error", "error");
                }
            }
            case customFilter -> {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String queryString;
                    queryString = "SELECT * FROM ritobot_config.custom_Filter";
                    statement = connection.createStatement();
                    resultSet6 = statement.executeQuery(queryString);
                    while (resultSet6.next()) {
                        String a;
                        try {
                            a = resultSet6.getString("data");
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println(resultSet6.getString("guildId"));
                            continue;
                        }
                        if (a != null) {
                            return_data.put(resultSet6.getString("guildId"), a);
                        }
                    }
                    resultSet6.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    return_data.put("error", "error");
                }
            }
        }
        return return_data;
    }

    @NotNull
    public static String[] configDownLoad(int option) {
        String[] return_data = new String[] {"error"};
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String queryString;
            int i = 0;
            switch (option) {
                case color_guild -> {
                    queryString = "SELECT * FROM ritobot_config.color_command_guild WHERE disable = 0";
                    statement = connection.createStatement();
                    resultSet6 = statement.executeQuery(queryString);
                    while (resultSet6.next()) {
                        if (i == 0) {
                            return_data = new String[]{
                                    resultSet6.getString("guildId")};
                            i++;
                        } else {
                            String[] newArray = Arrays.copyOf(return_data, return_data.length + 1);
                            newArray[return_data.length] = resultSet6.getString("guildId");
                            return_data = newArray;
                        }
                    }
                }
                case filter -> {
                    queryString = "SELECT * FROM ritobot_config.filter_guild WHERE disable = 1";
                    statement = connection.createStatement();
                    resultSet6 = statement.executeQuery(queryString);
                    return_data = new String[resultSet6.getFetchSize()];
                    while (resultSet6.next()) {
                        if (i == 0) {
                            return_data = new String[]{
                                    resultSet6.getString("guildId")};
                            i++;
                        } else {
                            String[] newArray = Arrays.copyOf(return_data, return_data.length + 1);
                            newArray[return_data.length] = resultSet6.getString("guildId");
                            return_data = newArray;
                        }
                    }
                }
                case link_filter -> {
                    queryString = "SELECT * FROM ritobot_config.link_filter_guild WHERE disable = 1";
                    statement = connection.createStatement();
                    resultSet6 = statement.executeQuery(queryString);
                    return_data = new String[resultSet6.getFetchSize()];
                    while (resultSet6.next()) {
                        if (i == 0) {
                            return_data = new String[]{
                                    resultSet6.getString("guildId")};
                            i++;
                        } else {
                            String[] newArray = Arrays.copyOf(return_data, return_data.length + 1);
                            newArray[return_data.length] = resultSet6.getString("guildId");
                            return_data = newArray;
                        }
                    }
                }
                case kill_filter -> {
                    queryString = "SELECT * FROM ritobot_config.kill_filter_guild WHERE disable = 1";
                    statement = connection.createStatement();
                    resultSet6 = statement.executeQuery(queryString);
                    return_data = new String[resultSet6.getFetchSize()];
                    while (resultSet6.next()) {
                        if (i == 0) {
                            return_data = new String[]{
                                    resultSet6.getString("guildId")};
                            i++;
                        } else {
                            String[] newArray = Arrays.copyOf(return_data, return_data.length + 1);
                            newArray[return_data.length] = resultSet6.getString("guildId");
                            return_data = newArray;
                        }
                    }
                }
                case customFilter -> {
                    queryString = "SELECT * FROM ritobot_config.custom_Filter WHERE disable = 1";
                    statement = connection.createStatement();
                    resultSet6 = statement.executeQuery(queryString);
                    return_data = new String[resultSet6.getFetchSize()];
                    while (resultSet6.next()) {
                        if (i == 0) {
                            return_data = new String[]{
                                    resultSet6.getString("guildId")};
                            i++;
                        } else {
                            String[] newArray = Arrays.copyOf(return_data, return_data.length + 1);
                            newArray[return_data.length] = resultSet6.getString("guildId");
                            return_data = newArray;
                        }
                    }
                }
                case lewdneko -> {
                    queryString = "SELECT * FROM ritobot_config.lewdneko_command WHERE disable = 1";
                    statement = connection.createStatement();
                    resultSet6 = statement.executeQuery(queryString);
                    return_data = new String[resultSet6.getFetchSize()];
                    while (resultSet6.next()) {
                        if (i == 0) {
                            return_data = new String[]{
                                    resultSet6.getString("guildId")};
                            i++;
                        } else {
                            String[] newArray = Arrays.copyOf(return_data, return_data.length + 1);
                            newArray[return_data.length] = resultSet6.getString("guildId");
                            return_data = newArray;
                        }
                    }
                }
                case color_role -> {
                    queryString = "SELECT * FROM ritobot_config.color_command_role;";
                    statement = connection.createStatement();
                    resultSet6 = statement.executeQuery(queryString);
                    return_data = new String[resultSet6.getFetchSize()];
                    while (resultSet6.next()) {
                        if (i == 0) {
                            return_data = new String[]{
                                    resultSet6.getString("roleId")};
                            i++;
                        } else {
                            String[] newArray = Arrays.copyOf(return_data, return_data.length + 1);
                            newArray[return_data.length] = resultSet6.getString("roleId");
                            return_data = newArray;
                        }
                    }
                }
                case textLogging -> {
                    queryString = "SELECT * FROM ritobot_config.logging_enable WHERE text_logging = 1;";
                    statement = connection.createStatement();
                    resultSet6 = statement.executeQuery(queryString);
                    return_data = new String[resultSet6.getFetchSize()];
                    while (resultSet6.next()) {
                        if (i == 0) {
                            return_data = new String[]{
                                    resultSet6.getString("guildId")};
                            i++;
                        } else {
                            String[] newArray = Arrays.copyOf(return_data, return_data.length + 1);
                            newArray[return_data.length] = resultSet6.getString("guildId");
                            return_data = newArray;
                        }
                    }
                }
                case channelLogging -> {
                    queryString = "SELECT * FROM ritobot_config.logging_enable WHERE channel_logging = 1;";
                    statement = connection.createStatement();
                    resultSet6 = statement.executeQuery(queryString);
                    return_data = new String[resultSet6.getFetchSize()];
                    while (resultSet6.next()) {
                        if (i == 0) {
                            return_data = new String[]{
                                    resultSet6.getString("guildId")};
                            i++;
                        } else {
                            String[] newArray = Arrays.copyOf(return_data, return_data.length + 1);
                            newArray[return_data.length] = resultSet6.getString("guildId");
                            return_data = newArray;
                        }
                    }
                }
                case memberLogging -> {
                    queryString = "SELECT * FROM ritobot_config.logging_enable WHERE member_logging = 1;";
                    statement = connection.createStatement();
                    resultSet6 = statement.executeQuery(queryString);
                    return_data = new String[resultSet6.getFetchSize()];
                    while (resultSet6.next()) {
                        if (i == 0) {
                            return_data = new String[]{
                                    resultSet6.getString("guildId")};
                            i++;
                        } else {
                            String[] newArray = Arrays.copyOf(return_data, return_data.length + 1);
                            newArray[return_data.length] = resultSet6.getString("guildId");
                            return_data = newArray;
                        }
                    }
                }
            }
            resultSet6.close();
        } catch (Exception e) {
            e.printStackTrace();
            return_data = new String [] {"error"};
        }
        return return_data;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void reConnection() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.warn("SQL ReConnection Complete");
    }

    public static String getUrl() {
        return url;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }
}
