package com.papa.config;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static String host;
    public static int port;
    public static String userServer;

    static {
        Properties properties = new Properties();
        try(InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("application.properties")){
            if (inputStream != null) {
                properties.load(inputStream);
            }
            host = (String) properties.getOrDefault("rmi.host", "localhost");
            port = Integer.parseInt((String)properties.getOrDefault("rmi.port", "8888"));
            userServer = (String) properties.getOrDefault("rmi.userServerName", "localhost");
        } catch (Exception e) {
            System.out.println(ErrorMessage.CONFIG_INIT_ERROR);
            System.exit(-1);
        }
    }
}
