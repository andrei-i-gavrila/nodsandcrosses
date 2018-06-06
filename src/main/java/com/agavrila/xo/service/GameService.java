package com.agavrila.xo.service;

import com.agavrila.xo.model.Game;
import com.agavrila.xo.model.User;

import java.util.Arrays;

public class GameService {


    public boolean isAllowedToMove(User user, Game game) {
        var xCount = game.getGameState().chars().filter(value -> value == 'X').count();
        var oCount = game.getGameState().chars().filter(value -> value == '0').count();

        return user.getId().equals(xCount >= oCount ? game.getPlayer2Id() : game.getPlayer1Id());
    }

    private boolean charWins(String gameState, char c) {
        var modes = Arrays.asList(
                Arrays.asList(0, 1, 2),
                Arrays.asList(3, 4, 5),
                Arrays.asList(6, 7, 8),
                Arrays.asList(0, 3, 6),
                Arrays.asList(1, 4, 7),
                Arrays.asList(2, 5, 8),
                Arrays.asList(0, 4, 8),
                Arrays.asList(2, 4, 6)
        );

        return modes.stream().anyMatch(places ->
            places.stream().allMatch(place -> gameState.charAt(place) == c)
        );
    }

    public Long getWinner(Game game) {
        String gameState = game.getGameState();

        if (charWins(gameState, 'X')) {
            return game.getPlayer1Id();
        }
        if (charWins(gameState, '0')) {
            return game.getPlayer2Id();
        }
        return null;
    }

}
