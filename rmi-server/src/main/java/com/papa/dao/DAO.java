package com.papa.dao;

import com.papa.config.Config;
import com.papa.config.ErrorMessage;
import com.papa.model.User;

import java.io.File;
import java.sql.*;

/**
 * Data Access Object
 */
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

    /**
     * @param user user model which need to be added to the database
     * Add user to database
     */
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

    /**
     * @param account account string
     * @return a user with the same account of parameter, return null if none of any users has that account
     */
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

    /**
     * Init database while launched
     * If database file does not exist, it will generate both the database file and the user table to complete the initialization
     */
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

    /**
     * @return SQL connection
     * @throws SQLException throws while databaseURL is incorrect
     */
    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(Config.databaseURL);
    }

    /**
     * @param query specific query process
     */
    private void sqlTemplate(SQLQuery query) {
        try(Connection connection = createConnection()) {
            query.query(connection);
        } catch (SQLException e) {
            System.out.println(ErrorMessage.DATABASE_QUERY_ERROR);
            System.exit(-1);
        }
    }

    /**
     * query database
     * it's used to take lambda function
     */
    interface SQLQuery {
        void query(Connection connection) throws SQLException;
    }
}
