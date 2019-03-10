package com.papa.dao;

import com.papa.config.Config;
import com.papa.config.ErrorMessage;
import com.papa.model.User;

import java.io.File;
import java.sql.*;

public class DAO {
    public DAO() {
        try {
            Class.forName(Config.databaseDriver);
        } catch (ClassNotFoundException e) {
            System.out.println(ErrorMessage.DATABASE_DRIVER_ERROR);
            System.exit(-1);
        }
        databaseInit();
    }

    public void addUser(User user) {
        sqlTemplate(connection -> {
            String sql = "insert into user (account, password) values (?, ?)";
            try(
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                statement.setString(1, user.account);
                statement.setString(2, user.password);
                statement.executeUpdate();
            }
        });
    }

    public User getUserByAccount(String account) {
        final User[] user = {null};
        sqlTemplate(connection -> {
            String sql = "select * from user where account=?";
            try(
                    PreparedStatement statement = connection.prepareStatement(sql)
            ){
                statement.setString(1, account);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    user[0] = new User(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    );
                }
            }
        });
        return user[0];
    }

    private void databaseInit() {
        String path = Config.databasePath;
        File databaseFile = new File(path);
        if (!databaseFile.exists()) {
            if (!databaseFile.getParentFile().exists()) {
                databaseFile.getParentFile().mkdirs();
            }
            System.out.println("Create database file");
            sqlTemplate(connection -> {
                try(
                        Statement statement = connection.createStatement()
                ){
                    String sql = "create table user" +
                            "(" +
                            "`id` integer primary key autoincrement, "+
                            "`account` varchar(255) default null, " +
                            "`password` varchar(255) default null" +
                            ")";
                    statement.execute(sql);
                }
            });
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(Config.databaseURL);
    }

    private void sqlTemplate(SQLQuery query) {
        try(Connection connection = createConnection()) {
            query.query(connection);
        } catch (SQLException e) {
            System.out.println(ErrorMessage.DATABASE_QUERY_ERROR);
            System.exit(-1);
        }
    }

    interface SQLQuery {
        void query(Connection connection) throws SQLException;
    }
}
