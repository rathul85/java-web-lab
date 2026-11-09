package controller;

import dao.ScoreDAO;
import model.GameScore;
import service.MathService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    private static final String DIFFICULTY = "NORMAL";
    // 90 and 270 are left out so that tan is never undefined
    private static final int[] ANGLES = {0, 30, 45, 60, 120, 135, 150, 180, 210, 225, 240, 300, 315, 330};
    private static final String[] TYPES = {"sin", "cos", "tan"};

    private final ScoreDAO dao = new ScoreDAO();
    private final Random rnd = new Random();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if ("leaderboard".equals(req.getParameter("action"))) {
            try {
                req.setAttribute("scores", dao.getTop10());
            } catch (SQLException e) {
                req.setAttribute("error", "Database error: " + e.getMessage());
            }
            req.getRequestDispatcher("leaderboard.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if ("start".equals(action)) {
            startGame(req, resp);
        } else if ("submit".equals(action)) {
            submitGame(req, resp);
        } else {
            resp.sendRedirect("index.jsp");
        }
    }

    /** Generates the four questions and keeps them in the session (answers are never sent to the browser). */
    private void startGame(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("playerName");
        if (name == null || name.trim().isEmpty()) {
            req.setAttribute("error", "Please enter your name.");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return;
        }
        HttpSession s = req.getSession();
        s.setAttribute("playerName", name.trim());
        s.setAttribute("primeNum", 1 + rnd.nextInt(100));
        s.setAttribute("modA", 10 + rnd.nextInt(90));
        s.setAttribute("modB", 2 + rnd.nextInt(11));            // never 0
        s.setAttribute("t1Type", TYPES[rnd.nextInt(TYPES.length)]);
        s.setAttribute("t1Angle", ANGLES[rnd.nextInt(ANGLES.length)]);
        s.setAttribute("t2Type", TYPES[rnd.nextInt(TYPES.length)]);
        s.setAttribute("t2Angle", ANGLES[rnd.nextInt(ANGLES.length)]);
        req.getRequestDispatcher("game.jsp").forward(req, resp);
    }

    private void submitGame(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("primeNum") == null) {
            resp.sendRedirect("index.jsp");
            return;
        }

        String name = (String) s.getAttribute("playerName");
        int primeNum = (Integer) s.getAttribute("primeNum");
        int modA = (Integer) s.getAttribute("modA");
        int modB = (Integer) s.getAttribute("modB");
        String t1Type = (String) s.getAttribute("t1Type");
        int t1Angle = (Integer) s.getAttribute("t1Angle");
        String t2Type = (String) s.getAttribute("t2Type");
        int t2Angle = (Integer) s.getAttribute("t2Angle");

        boolean primeCorrect = false, modCorrect = false, trig1Correct = false, trig2Correct = false;

        try {
            int ans = Integer.parseInt(req.getParameter("ans1").trim());
            primeCorrect = ans == MathService.isPrime(primeNum);
        } catch (Exception ignored) { }

        try {
            int ans = Integer.parseInt(req.getParameter("ans2").trim());
            modCorrect = ans == MathService.calculateMod(modA, modB);
        } catch (Exception ignored) { }

        try {
            double ans = Double.parseDouble(req.getParameter("ans3").trim());
            trig1Correct = MathService.isClose(ans, MathService.calculateTrig(t1Type, t1Angle));
        } catch (Exception ignored) { }

        try {
            double ans = Double.parseDouble(req.getParameter("ans4").trim());
            trig2Correct = MathService.isClose(ans, MathService.calculateTrig(t2Type, t2Angle));
        } catch (Exception ignored) { }

        int totalScore = 0;
        if (primeCorrect) totalScore += 2;
        if (modCorrect)   totalScore += 2;
        if (trig1Correct) totalScore += 3;
        if (trig2Correct) totalScore += 3;

        try {
            dao.save(new GameScore(name, totalScore, DIFFICULTY, new Timestamp(System.currentTimeMillis())));
        } catch (SQLException e) {
            req.setAttribute("error", "Score could not be saved: " + e.getMessage());
        }

        // clear the questions so the same game cannot be submitted twice
        s.removeAttribute("primeNum");

        req.setAttribute("score", totalScore);
        req.setAttribute("primeOk", primeCorrect);
        req.setAttribute("modOk", modCorrect);
        req.setAttribute("t1Ok", trig1Correct);
        req.setAttribute("t2Ok", trig2Correct);
        req.getRequestDispatcher("result.jsp").forward(req, resp);
    }
}
