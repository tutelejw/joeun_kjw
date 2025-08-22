<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>봉사 목록</title>
</head>
<body>
    <h2>봉사 목록</h2>
    <hr>
    
    <table border="1" style="width:100%; text-align:center;">
        <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="offer" items="${requestScope.list}">
                <tr>
                    <td>${offer.offerNo}</td>
                    <td>${offer.title}</td>
                    <td>${offer.writer}</td>
                </tr>
            </c:forEach>
            
            <c:if test="${empty requestScope.list}">
                <tr>
                    <td colspan="3">등록된 봉사활동이 없습니다.</td>
                </tr>
            </c:if>
        </tbody>
    </table>

</body>
</html>