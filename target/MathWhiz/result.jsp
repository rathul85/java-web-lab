<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Math Whiz - Result</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="card">
  <h1>Result</h1>
  <p>Player: <b><c:out value="${sessionScope.playerName}"/></b></p>
  <h2>Score: ${score} / 10</h2>
  <ul class="res">
    <li>Q1 Prime: ${primeOk ? "Correct (+2)" : "Wrong (0)"}</li>
    <li>Q2 Modulus: ${modOk ? "Correct (+2)" : "Wrong (0)"}</li>
    <li>Q3 Trigonometry: ${t1Ok ? "Correct (+3)" : "Wrong (0)"}</li>
    <li>Q4 Trigonometry: ${t2Ok ? "Correct (+3)" : "Wrong (0)"}</li>
  </ul>
  <c:if test="${not empty error}"><p class="error"><c:out value="${error}"/></p></c:if>
  <a href="index.jsp">Play Again</a> | <a href="game?action=leaderboard">Leaderboard</a>
</div>
</body>
</html>
