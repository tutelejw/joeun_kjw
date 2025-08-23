<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>비밀번호 확인</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" />
  <script>
    function validateConfirmPwd() {
      var v = document.getElementById("currentPwd").value.trim();
      if (!v) {
        alert("현재 비밀번호를 입력하세요.");
        document.getElementById("currentPwd").focus();
        return false;
      }
      return true;
    }
  </script>
</head>
<body>
  <h2>비밀번호 확인</h2>

  <c:if test="${not empty errorMsg}">
    <div class="error">${errorMsg}</div>
  </c:if>

  <form method="post"
        action="${pageContext.request.contextPath}/confirmPwd.do"
        onsubmit="return validateConfirmPwd();">
    <div class="form-row">
      <label for="currentPwd">현재 비밀번호</label>
      <input type="password" id="currentPwd" name="currentPwd" autocomplete="current-password" />
    </div>

    <div class="form-actions">
      <button type="submit">기존 비밀번호 확인</button>
      <a href="${pageContext.request.contextPath}/index.jsp">취소</a>
    </div>
  </form>
</body>
</html>
