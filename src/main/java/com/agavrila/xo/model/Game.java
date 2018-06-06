package com.agavrila.xo.model;

public class Game {

    private Long id;
    private String gameState;
    private Long player1Id;
    private Long player2Id;
    private Long winner;

    public Game() {

    }

    public Game(Long id, String gameState, Long player1Id, Long player2Id, Long winner) {
        this.id = id;
        this.gameState = gameState;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.winner = winner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    public Long getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(Long player1Id) {
        this.player1Id = player1Id;
    }

    public Long getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(Long player2Id) {
        this.player2Id = player2Id;
    }

    public Long getWinner() {
        return winner;
    }

    public void setWinner(Long winner) {
        this.winner = winner;
    }
}
