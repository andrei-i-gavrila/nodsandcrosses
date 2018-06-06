package com.agavrila.xo.servlet;

import com.agavrila.xo.dao.GameDAO;
import com.agavrila.xo.dao.UserDAO;
import com.agavrila.xo.service.GameService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    private GameDAO gameDAO = new GameDAO();
    private UserDAO userDAO = new UserDAO();
    private GameService gameService = new GameService();

    public GameServlet() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        var answer = new HashMap<String, Object>();
        Object username = req.getSession().getAttribute("username");
        if (username == null) {
            resp.setStatus(401);
            resp.sendRedirect("/");
            return;
        }
        var user = userDAO.getUserForUsername(username.toString());
        if (user == null) {
            resp.setStatus(401);
            return;
        }

        var currentGame = gameDAO.getCurrentGame();
        if (currentGame == null) {
            req.getSession().removeAttribute("username");

            currentGame = gameDAO.getLastGame();
        }

        var status = "Waiting for second player";
        if (currentGame.getPlayer2Id() != null) {
            status = "Playing";
            if (currentGame.getWinner() != null) {
                req.getSession().removeAttribute("username");
                if (user.getId().equals(currentGame.getWinner())) {
                    status = "You won";
                } else {
                    status = "You lost";
                }
            }
        }

        answer.put("game", currentGame);
        answer.put("status", status);

        new ObjectMapper().writeValue(resp.getWriter(), answer);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Object username = req.getSession().getAttribute("username");
        if (username == null) {
            resp.setStatus(401);
            return;
        }
        var user = userDAO.getUserForUsername(username.toString());
        if (user == null) {
            resp.setStatus(401);
            return;
        }
        var game = gameDAO.getCurrentGame();
        if (game == null) {
            resp.setStatus(403);
            return;
        }

        if (game.getPlayer2Id() == null) {
            return;
        }

        if (!gameService.isAllowedToMove(user, game)) {
            resp.setStatus(403);
            return;
        }

        int move = Integer.valueOf(req.getParameter("move"));

        if (game.getGameState().charAt(move) != '-') {
            resp.setStatus(403);
            return;
        }

        var newGameState = new StringBuilder(game.getGameState())
                .replace(move, move + 1, user.getId().equals(game.getPlayer1Id()) ? "X" : "0")
                .toString();

        game.setGameState(newGameState);
        game.setWinner(gameService.getWinner(game));

        gameDAO.updateGame(game);
    }
}
