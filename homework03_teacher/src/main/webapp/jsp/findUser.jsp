<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="jw.services.user.vo.UserVO" %>   
<%@ page import="jw.services.user.dao.UserDAO" %>
<%
	request.setCharacterEncoding("UTF-8");
	
	//client에서 넘어온 값을 받자.
	String id = request.getParameter("id");
	//==>id가 null인경우는 ?
	//==><a href='/myWeb/SearchUserServlet'>내정보보기2(session사용)</a>
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
		
		<h2>요청하신 회원정보</h2>
		<% if(userVO != null){  %>
		    no  :  <%= userVO.getNo()  %> <br/>
		    id   :  <%= userVO.getId() %> <br/>
		    pwd : <%= userVO.getPwd() %> <br/>
		<% }else{  %>
			<%= id  %> 에 해당하는 data는 없습니다.<br/>
		<% }  %>

	</body>
</html>