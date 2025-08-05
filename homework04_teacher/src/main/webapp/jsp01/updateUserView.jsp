<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="jw.services.user.vo.UserVO" %>   
<%@ page import="jw.services.user.dao.UserDAO" %>

<%
	request.setCharacterEncoding("UTF-8");
	
	//client에서 넘어온 값을 받자.
	String id = request.getParameter("id");
	System.out.println("::"+id);
	//==>id가 null인경우는 ?
	//==><a href='/homework04/updateUserView.jsp'>내정보보기2(session사용)</a>
	if(id == null){
		//==>  Session에 저장된 userVO instance 의 id 사용
		id =  ( (UserVO)session.getAttribute("userVO")).getId();
	}
	
	//DB에 접근하는 UserDAO를 이용 회원정보 select 후
	UserDAO bean = new UserDAO();
	//==> 회원의 정보를 갖는 userVO intance로 받기
	UserVO userVO = bean.findUser(id);
%>
<html>
<body>

회원정보 수정화면 입니다. 수정하시고..... 수정완료.....<br/>

	<form method="Get" action="/homework04_teacher/jsp01/updateUser.jsp">
		
		<table border="1" width="80%">
			<tr>
				<td>이이디</td>
				<td><input type="text" name="id" value ="<%=userVO.getId() %>" /></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="text" name="pwd" value ="<%=userVO.getPwd() %>"/></td>
			</tr>
			<tr>
				<td>행운번호</td>
				<td><input type="text" name="no"  value="<%=userVO.getNo() %>" /> 번호는 수정하면않됨</td>
			</tr>
			<tr>
				<td colspan="2"><center><input type="submit" value="수정완료(정말수정)"/></center></td>
			</tr>
		</table>
		
	</form>	
	
</body>
</html>