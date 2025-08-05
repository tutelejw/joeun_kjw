<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="jw.services.user.vo.UserVO" %>   
<%@ page import="jw.services.user.dao.UserDAO" %>

<%
	request.setCharacterEncoding("UTF-8");
	
	//client에서 넘어온 값을 받자.
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	int no = Integer.parseInt(request.getParameter("no"));
	
	//Client에 받은 data로 UserVO instance 생성 및 Value set
	UserVO userVO = new UserVO();
	userVO.setId(id);
	userVO.setPwd(pwd);
	userVO.setNo(no);
	
	//DB에 접근하는 UserDAO를 이용 회원정보 Insert
	UserDAO userDAO = new UserDAO();
	userDAO.updateUser(userVO);

%>    
    
<html>
	<body>
		
		<h2>요청하신 회원정보</h2>
		<% if(userVO.isActive() ){  %>
		    수정완료 <br/>
		<% }else{  %>
			  수정 error<br/>
		<% }  %>
		
		<br/><br/><br/>
		
	</body>
</html>