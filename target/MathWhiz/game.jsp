<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Math Whiz - Game</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="card">
  <h1>RATHUL – MATH WHIZ</h1>
  <p>Player: <b><c:out value="${sessionScope.playerName}"/></b> | Difficulty: NORMAL</p>
  <form action="game" method="post">
    <input type="hidden" name="action" value="submit">

    <label>Q1 (2 marks): Is <b>${sessionScope.primeNum}</b> a prime number? (1 = Prime, 0 = Not prime)</label>
    <select name="ans1" required>
      <option value="">-- choose --</option>
      <option value="1">1 (Prime)</option>
      <option value="0">0 (Not prime)</option>
    </select>

    <label>Q2 (2 marks): What is the remainder of <b>${sessionScope.modA} % ${sessionScope.modB}</b> ?</label>
    <input type="number" name="ans2" step="1" required>

    <label>Q3 (3 marks): What is <b>${sessionScope.t1Type}(${sessionScope.t1Angle}°)</b> ? (2 decimal places)</label>
    <input type="text" name="ans3" placeholder="e.g. 0.50" required>

    <label>Q4 (3 marks): What is <b>${sessionScope.t2Type}(${sessionScope.t2Angle}°)</b> ? (2 decimal places)</label>
    <input type="text" name="ans4" placeholder="e.g. -0.87" required>

    <button type="submit">Submit Answers</button>
  </form>
</div>
</body>
</html>
