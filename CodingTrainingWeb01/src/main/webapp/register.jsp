<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>회원가입</title>
  <script>
    // 간단한 AJAX 방식 아이디 중복체크
    function checkId() {
      var id = document.getElementById("id").value;
      if (!id) { alert("ID를 입력하세요."); return; }
      var xhr = new XMLHttpRequest();
      xhr.open("GET", "idCheck.do?id=" + encodeURIComponent(id), true);
      xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
          if (xhr.responseText === "EXISTS") {
            alert("이미 사용중인 ID입니다.");
          } else {
            alert("사용 가능한 ID입니다.");
          }
        }
      };
      xhr.send(null);
    }
  </script>
</head>
<body>
  <h2>회원가입</h2>
  <!-- action은 Front Controller로 (register.do) -->
  <form action="register.do" method="post">
    ID: <input type="text" id="id" name="id"> <button type="button" onclick="checkId()">중복체크</button><br>
    Password: <input type="password" name="password"><br>
    Name: <input type="text" name="name"><br>
    <input type="submit" value="회원가입">
  </form>
  <a href="loginForm.do">로그인으로</a>
</body>
</html>
