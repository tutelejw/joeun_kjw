<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title><c:out value="${pageTitle != null ? pageTitle : '온세상'}"/></title>
  <link rel="stylesheet" href="<c:url value='/css/site.css'/>">
</head>
<body>
  <%@ include file="/common/top.jspf" %>        <!-- 여기!  /WEB-INF/jsp/... 아님 -->

  <main class="container-main">
    <jsp:include page="${contentPage}" />
  </main>

  <%@ include file="/common/footer.jspf" %>     <!-- 여기! -->
</body>
</html>
