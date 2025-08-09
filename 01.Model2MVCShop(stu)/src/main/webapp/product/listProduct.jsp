<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.service.product.vo.*" %>
<%@ page import="com.model2.mvc.service.user.vo.UserVO" %>
<%@ page import="com.model2.mvc.common.*" %>
<%
    String uri = request.getRequestURI();         // 예: /product/listProduct.jsp
    String url = request.getRequestURL().toString();  // 예: http://localhost:8080/product/listProduct.jsp
    String query = request.getQueryString();      // 예: prodNo=123&menu=search
    String method = request.getMethod();          // GET or POST

    System.out.println("[로그] 요청 URI: " + uri);
    System.out.println("[로그] 전체 URL: " + url);
    System.out.println("[로그] 쿼리스트링: " + query);
    System.out.println("[로그] 요청 방식: " + method);
    %>

<%
	HashMap<String,Object> map=(HashMap<String,Object>)request.getAttribute("map");
	SearchVO searchVO=(SearchVO)request.getAttribute("searchVO");
	//session.setAttribute("user", userVO);
	// 세션에서 "user"로 저장된 userVO를 가져옵니다.
	UserVO userVO = (UserVO) session.getAttribute("user");
	
	int total=0;
    String menu = "search";
    
	ArrayList<ProductVO> list=null;
	if(map != null){
		total=((Integer)map.get("count")).intValue();
		list=(ArrayList<ProductVO>)map.get("list");
	}
	
	int currentPage=searchVO.getPage();
	
	int totalPage=0;
	if(total > 0) {
		totalPage= total / searchVO.getPageUnit() ;
		if(total%searchVO.getPageUnit() >0)
			totalPage += 1;
	}
 

	String menuParam = request.getParameter("menu");
    String link;
    String title="상품 목록 조회";

    if ("manage".equals(menuParam)) {
        title = "상품관리";
    } 


// userVO가 null이 아니면 처리합니다.
//if (userVO != null) {
//    request.setAttribute("userVO", userVO);
//
//    if ("admin".equals(userVO.getRole())) {
//        menu = "manage";
//    }
//} else {
//    // userVO가 null일 경우 로그인되지 않은 상태
//    out.println("로그인되지 않았습니다.");
 //   // 또는 로그인 페이지로 리디렉션 처리할 수 있습니다.
//    response.sendRedirect("login.jsp");
//    return; // 로그인 페이지로 리디렉션 후 나머지 코드가 실행되지 않도록 합니다.
//}

%>

<html>
<head>
<title><%=title %></title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
function fncGetProductList(){
	document.detailForm.submit();
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01"><%=title %></td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37">
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
	<%
		if(searchVO.getSearchCondition() != null) {
	%>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
		<%
				if(searchVO.getSearchCondition().equals("0")){
		%>
				<option value="0" selected>상품ID</option>
				<option value="1">상품명</option>
		<%
				}else {
		%>
				<option value="0">상품ID</option>
				<option value="1" selected>상품명</option>
		<%
				}
		%>
			</select>
			<input 	type="text" name="searchKeyword"  value="<%=searchVO.getSearchKeyword() %>" 
							class="ct_input_g" style="width:200px; height:19px" >
		</td>
	<%
		}else{
	%>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0">상품ID</option>
				<option value="1">상품명</option>
			</select>
			<input type="text" name="searchKeyword"  class="ct_input_g" style="width:200px; height:19px" >
		</td>
	<%
		}
	%>
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList();">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >전체  <%= total%> 건수, 현재 <%=currentPage %> 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>		
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>


	<% 	
		int no=list.size();
		for(int i=0; i<list.size(); i++) {
			ProductVO vo = (ProductVO)list.get(i);

	%>
<%
if ("manage".equals(menuParam)) {
    link = "/getProduct.do?prodNo=" + vo.getProdNo() + "&menu=manage";
} else {
    link = "/getProduct.do?prodNo=" + vo.getProdNo();  // 기본은 search
}
%>
	<tr class="ct_list_pop">
		<td align="center"><%=no--%></td>
		<td></td>
		<td align="left">
			<a href="<%=link%>"><%= vo.getProdNo() %></a>
			<%-- <a href="/getProduct.do?prodNo=<%=vo.getProdNo()%>"><%= vo.getProdNo() %></a>  --%>
			<%-- <a href="/getProduct.do?prodNo=<%=vo.getProdNo()%>&menu=<%=menu%>"><%= vo.getProdNo() %></a>  --%>
			 
		</td>
		<td></td>
		<td align="left"><%= vo.getProdName() %></td>
		<td></td>
		<td align="left"><%= vo.getRegDate() %>
		</td>		
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	<% } %>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		<%
			for(int i=1;i<=totalPage;i++){
		%>
			<a href="/listProduct.do?page=<%=i%>"><%=i %></a>
		<%
			}
		%>	
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->
</form>
</div>

</body>
</html>