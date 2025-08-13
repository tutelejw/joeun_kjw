<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="jw.service.user.vo.UserVO" %>
<%@ page import="jw.service.user.dao.UserDao" %>

<%
// 방어적 코딩 /  WorkFlow Control
	if( request.getMethod().equals("GET") ){
		response.sendRedirect("addUser.html");
		return;
	 }

	request.setCharacterEncoding("UTF-8"); 
	
	// Client Form Data 처리
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	int no = Integer.parseInt(request.getParameter("no"));
	
	// UserVO instance 생성 및 Client Form Data 전달(Binding)
	UserVO userVO = new UserVO();
	userVO.setId(id);
	userVO.setPwd(pwd);
	userVO.setNo(no);
	
	// DB  접근 회원정보 Insert
	ProductDao userDao = new ProductDao();
%>

<%if(  userDao.addUser(userVO) ){%>
		<%= userVO.getId() %> 회원 가입 축하합니다.
<%} else {  %>
		다시 기입요망<br/><hr/>
		<jsp:include page="/jw07/addUser.html" />
<%}%>