<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<body>

 회원정보 검색입력화면.....(검색할 ID을 입력하세요.)<br/>

	<form method="post" action="/homework04_teacher/jsp01/findUser.jsp">
		
		<table border="1" width="80%">
			<tr>
				<td>이이디</td>
				<td><input type="text" name="id"/></td>
			</tr>
			<tr>
				<td colspan="2"><center><input type="submit" value=" 입력완료"/></center></td>
			</tr>
		</table>
		
	</form>	
	
</body>
</html>