package com.agavrila.xo.servlet;

import com.agavrila.xo.dao.GameDAO;
import com.agavrila.xo.dao.UserDAO;
import com.agavrila.xo.model.Game;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();
    GameDAO gameDAO = new GameDAO();

    public LoginServlet() throws SQLException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if (req.getSession().getAttribute("username") != null) {
            resp.sendRedirect("/play.jsp");
            return;
        }

        var username = req.getParameter("username");
        var password = req.getParameter("password");

        var user = userDAO.getUserForUsername(username);
        if (user == null) {
            resp.sendRedirect("/");
            return;
        }

        var correct = BCrypt.checkpw(password, user.getPassword());
        if (correct ) {
            var currentGame = gameDAO.getCurrentGame();
            if (currentGame == null) {
                gameDAO.startGame();
                currentGame = gameDAO.getCurrentGame();
                currentGame.setPlayer1Id(user.getId());
                gameDAO.updateGame(currentGame);
            } else if (currentGame.getPlayer2Id() == null) {
                currentGame.setPlayer2Id(user.getId());
                gameDAO.updateGame(currentGame);
            } else {
                resp.setStatus(403);
                return;
            }
            req.getSession().setAttribute("username", username);
        }

        resp.sendRedirect("/play.jsp");
    }
}
