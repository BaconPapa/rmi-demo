package com.papa.config;

import java.io.InputStream;
import java.util.Properties;

/**
 * Config file
 * It read the property file first
 */
public class Config {
    public static String databaseDriver = "org.sqlite.JDBC" ;
    public static String databasePath;
    public static String databaseURL;
    public static int serverPort;
    public static String userServerName;
    
    static {
        Properties properties = new Properties();
        try(InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("application.properties")){
            if (inputStream != null) {
                properties.load(inputStream);
            }
            databasePath = (String) properties.getOrDefault("database.path", "db/database.db");
            databaseURL = "jdbc:sqlite:" + databasePath;
            serverPort = Integer.parseInt((String) properties.getOrDefault("rmi.port", "8888"));
            userServerName = (String) properties.getOrDefault("rmi.userServerName", "User");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(ErrorMessage.CONFIG_INIT_ERROR);
        }
    }
}
