<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>비밀번호 확인</title>

  <!-- global CSS -->
  <%-- <link rel="stylesheet" href="<c:url value='/css/site.css'/>">  --%>
  <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">  

  <!-- global JS -->
  <script src="<c:url value='/js/jquery-1.12.4.min.js'/>"></script>
  <script src="<c:url value='/js/bootstrap.min.js'/>"></script>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" />

  <style>
    /* ===== Theme variables (copied from site.css) ===== */
    :root {
      --bg: #f7f8fa;
      --surface: #ffffff;
      --ink: #333333;
      --subtle-ink: #666666;
      --line: #e6e9ef;
      --hover: #eef2f7;
      --accent: #9db8ff;
    }

    * { box-sizing: border-box; }
    html, body {
      height: 100%;
      margin: 0;
      padding: 0;
      font-family: "Noto Sans KR", system-ui, -apple-system, Segoe UI, Roboto, sans-serif;
      color: var(--ink);
      background: url("<c:url value='/images/main.jpg'/>") no-repeat center center fixed;
      background-size: cover;
    }

   
   
   
   
   /* ===== Top bar (navbar) ===== */
.topbar, .navbar {
  position: fixed;
  top: 0; left: 0; right: 0;
  z-index: 1000;
  width: 100%;
  background: var(--surface);
  border-bottom: 1px solid var(--line);
}
.topbar .container, .navbar .container {
  max-width: none;
  width: 100%;
  margin: 0 auto;
  padding: 12px 37px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.brand { margin-right: auto; }
.brand a {
  display: inline-block;
  font-weight: 700;
  letter-spacing: 0.2px;
  text-decoration: none;
  color: var(--ink);
  background: none; border: 0; padding: 0;
}

.nav { display: flex; align-items: center; gap: 24px; }
.nav a, .nav button {
  appearance: none; border: 0; background: none; padding: 0; border-radius: 0;
  font-weight: 600; text-decoration: none; color: var(--ink); line-height: 1; cursor: pointer;
  transition: color .15s ease;
}
.nav a:hover, .nav button:hover { color: var(--subtle-ink); }
.nav a:focus-visible, .nav button:focus-visible {
  outline: none; box-shadow: 0 0 0 3px var(--accent);
}

/* Dropdown */
.dropdown { position: relative; }
.dropdown > button { display: inline-flex; align-items: center; gap: 6px; }
.dropdown-menu {
  position: absolute; right: 0; top: calc(100% + 8px); min-width: 200px;
  background: var(--surface); border: 1px solid var(--line); border-radius: 12px; padding: 8px;
  display: none; box-shadow: 0 10px 30px rgba(20,20,20,.06); z-index: 10;
}
.dropdown:hover .dropdown-menu,
.dropdown:focus-within .dropdown-menu { display: block; }
.dropdown-menu a {
  display: block; padding: 10px 12px; border-radius: 10px; color: var(--ink);
  text-decoration: none; border: 1px solid transparent;
}
.dropdown-menu a:hover { background: var(--hover); border-color: #dfe5ee; }
   
   
   
   
   
   
   

    /* === Center card === */
    :root { --nav-h: 64px; }

    body.login-page { display: block; min-height: 100vh; }

    .login-card {
      position: relative;
      left: 50%;
      transform: translateX(-50%);
      margin-top: calc(var(--nav-h) + 40px);
      margin-bottom: 60px;
      width: 360px;
      background: rgba(255,255,255,0.85);
      padding: 30px 40px;
      border-radius: 12px;
      box-shadow: 0 4px 15px rgba(0,0,0,0.2);
    }

    .login-card h2 { text-align:center; margin-bottom:20px; font-weight:700; }
    .login-card .form-group { margin-bottom:15px; }
    .login-card label { font-weight:700; display:block; margin-bottom:5px; }
    .login-card input {
      width:100%; padding:10px; border:1px solid #ccc; border-radius:6px; font-size:14px;
    }
    .login-card button {
      width:100%; padding:12px; background:#444; color:#fff; border:0; border-radius:6px;
      font-size:15px; cursor:pointer; margin-top:10px;
    }
    .login-card button:hover { background:#333; }
    .login-card a { display:block; margin-top:12px; text-align:center; color:#007bff; text-decoration:none; }
    .login-card a:hover { text-decoration:underline; }

    .error {
      margin-bottom: 12px;
      padding: 10px 12px;
      border-radius: 8px;
      background: #ffecec;
      border: 1px solid #f5c2c2;
      color: #b10000;
      font-size: 13px;
    }
  </style>








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
<body class="login-page">
  <%@ include file="/common/top.jspf" %>

  <div class="login-card">
    <h2>비밀번호 확인</h2>

    <c:if test="${not empty errorMsg}">
      <div class="error">${errorMsg}</div>
    </c:if>

    <form method="post"
          action="${pageContext.request.contextPath}/confirmPwd.do"
          onsubmit="return validateConfirmPwd();">
      <div class="form-group">
        <label for="currentPwd">현재 비밀번호</label>
        <input type="password" id="currentPwd" name="currentPwd" autocomplete="current-password" />
      </div>

      <button type="submit">기존 비밀번호 확인</button>
      <a href="${pageContext.request.contextPath}/index.jsp">취소</a>
    </form>
  </div>
</body>
</html>
