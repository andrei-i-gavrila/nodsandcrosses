package com.agavrila.xo.dao;

import com.agavrila.xo.model.User;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.stream.Stream;

public class UserDAO extends DAO {

    public UserDAO() throws SQLException {
        Long userCount = queryRunner.query("select count(*) from users", new ScalarHandler<>());


        if (userCount != 3) {
            Stream.of("player1", "player2", "player3").forEach(playerName -> {
                var pass = BCrypt.hashpw("parola", BCrypt.gensalt());
                try {
                    queryRunner.insert("insert into users(username, password) values(?, ?)", new ArrayListHandler(), playerName, pass);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public User getUserForUsername(String username) {
        try {
            return queryRunner.query("select * from users where username = ?", new BeanHandler<>(User.class), username);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
