<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Math Whiz - Leaderboard</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="card wide">
  <h1>Top 10 Leaderboard</h1>
  <c:if test="${not empty error}"><p class="error"><c:out value="${error}"/></p></c:if>
  <table>
    <tr><th>Rank</th><th>Player</th><th>Score</th><th>Difficulty</th><th>Date/Time</th></tr>
    <c:forEach var="s" items="${scores}" varStatus="st">
      <tr>
        <td>${st.index + 1}</td>
        <td><c:out value="${s.playerName}"/></td>
        <td>${s.score}</td>
        <td>${s.difficulty}</td>
        <td>${s.playedAt}</td>
      </tr>
    </c:forEach>
    <c:if test="${empty scores}">
      <tr><td colspan="5">No scores yet. Be the first to play!</td></tr>
    </c:if>
  </table>
  <a href="index.jsp">Back to Home</a>
</div>
</body>
</html>
