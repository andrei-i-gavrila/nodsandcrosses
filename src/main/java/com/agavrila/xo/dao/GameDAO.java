package com.agavrila.xo.dao;

import com.agavrila.xo.model.Game;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class GameDAO extends DAO {

    public GameDAO() {
    }


    public void startGame() {
        try {
            queryRunner.insert("insert into game () values ()", new ArrayListHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Game getCurrentGame() {
        try {
            return queryRunner.query("select * from game where winner is null", new BeanHandler<>(Game.class));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateGame(Game game) {
        try {
            queryRunner.update(
                    "update game set player1Id = ?, player2Id = ?, winner = ?, gameState = ? where id = ?",
                    game.getPlayer1Id(), game.getPlayer2Id(), game.getWinner(),
                    game.getGameState(), game.getId()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Game getLastGame() {
        try {
            return queryRunner.query("select * from game order by id desc limit 1", new BeanHandler<>(Game.class));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
