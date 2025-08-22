<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>온세상</title>
    <style>
        body { margin: 0; }
        header, footer { background: #f2f2f2; padding: 10px; }
        .content-frame { width: 100%; height: 800px; border: none; }
    </style>
</head>
<body>
    <!-- 상단 메뉴 -->
    <header>
        <%@ include file="/common/top.jspf" %>
    </header>

    <!-- 본문: iframe에 다른 화면을 불러옴 -->
    <main>
        <iframe name="downFrame"
                src="/semiProject_001/home/home.jsp"
                class="content-frame"></iframe>
    </main>

    <!-- 하단 -->
    <footer>
        <%@ include file="/common/footer.jspf" %>
    </footer>
</body>
</html>
