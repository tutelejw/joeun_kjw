<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>비밀번호 변경</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" />
  <script>
    function validateUpdatePwd() {
      var p1 = document.getElementById("newPwd").value;
      var p2 = document.getElementById("newPwd2").value;

      if (!p1.trim() || !p2.trim()) {
        alert("새 비밀번호를 입력하세요.");
        return false;
      }
      if (p1 !== p2) {
        alert("새 비밀번호가 일치하지 않습니다.");
        document.getElementById("newPwd2").focus();
        return false;
      }
      if (p1.length < 6) {
        alert("비밀번호는 6자 이상이어야 합니다.");
        document.getElementById("newPwd").focus();
        return false;
      }
      return true;
    }
  </script>
</head>
<body>
  <h2>비밀번호 변경</h2>

  <c:if test="${not empty errorMsg}">
    <div class="error">${errorMsg}</div>
  </c:if>

  <form method="post"
        action="${pageContext.request.contextPath}/updatePwd.do"
        onsubmit="return validateUpdatePwd();">
    <div class="form-row">
      <label for="newPwd">새 비밀번호</label>
      <input type="password" id="newPwd" name="newPwd" autocomplete="new-password" />
    </div>

    <div class="form-row">
      <label for="newPwd2">새 비밀번호 확인</label>
      <input type="password" id="newPwd2" name="newPwd2" autocomplete="new-password" />
    </div>

    <div class="form-actions">
      <button type="submit">비밀번호 수정 확인</button>
      <a href="${pageContext.request.contextPath}/index.jsp">취소</a>
    </div>
  </form>
</body>
</html>
