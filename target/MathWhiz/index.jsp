<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Math Whiz</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="card">
  <h1>RATHUL – MATH WHIZ</h1>
  <p>Answer 4 maths questions and get onto the leaderboard!</p>
  <c:if test="${not empty error}"><p class="error"><c:out value="${error}"/></p></c:if>
  <form action="game" method="post">
    <input type="hidden" name="action" value="start">
    <label for="playerName">Player Name</label>
    <input type="text" id="playerName" name="playerName" placeholder="Enter your name" maxlength="100" required>
    <button type="submit">Start Game</button>
  </form>
  <p style="text-align:center"><a href="game?action=leaderboard">View Leaderboard</a></p>
</div>
</body>
</html>
