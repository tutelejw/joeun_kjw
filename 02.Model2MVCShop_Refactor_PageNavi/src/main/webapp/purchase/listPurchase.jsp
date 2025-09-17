<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.service.domain.Purchase" %>
<%@ page import="com.model2.mvc.service.domain.User" %>
<%@ page import="com.model2.mvc.common.Search" %>
<%@page import="com.model2.mvc.common.Page"%>
<%@page import="com.model2.mvc.common.util.CommonUtil"%>

<%
String uri = request.getRequestURI();
String url = request.getRequestURL().toString();
String query = request.getQueryString();
String method = request.getMethod();

System.out.println("[로그] 요청 URI: " + uri);
System.out.println("[로그] 전체 URL: " + url);
System.out.println("[로그] 쿼리스트링: " + query);
System.out.println("[로그] 요청 방식: " + method);

	List<Purchase> list= (List<Purchase>)request.getAttribute("list");
	Page resultPage=(Page)request.getAttribute("resultPage");
	
	Search search = (Search)request.getAttribute("search");
	//==> null 을 ""(nullString)으로 변경
	String searchCondition = CommonUtil.null2str(search.getSearchCondition());
	String searchKeyword = CommonUtil.null2str(search.getSearchKeyword());

    String menu = "search";
    String menuParam = request.getParameter("menu");
    String link;
    String title = "구매 목록 조회";
    
    if ("manage".equals(menuParam)) {
        title = "구매목록";
    }
    
%>










<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetPurchaseList(page) {
		document.getElementById("currentPage").value = page;
		document.detailForm.submit();
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/listPurchase.do" method="post">
<input type="hidden" id="currentPage" name="currentPage" value="1"/>
<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">
					전체  <%= resultPage.getTotalCount() %> 건수,	현재 <%= resultPage.getCurrentPage() %> 페이지
		</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">전화번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">정보수정</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

	<%
		for(int i=0; i<list.size(); i++) {
			Purchase vo = list.get(i);
	%>
	
	<tr class="ct_list_pop">
		<td align="center"> <a href="/getPurchase.do?tranNo=<%= vo.getTranNo() %>"><%=  i + 1 %></a>		</td>
		<td></td>
		<td align="left">
			<a href="/getUser.do?userId=<%= vo.getBuyer().getUserId() %>"><%= vo.getBuyer().getUserId() %></a>
		</td>
		<td></td>
		<td align="left"><%=vo.getBuyer().getUserName()%></td>
		<td></td>
		<td align="left"><%= vo.getReceiverPhone() %></td>
		<td></td>
		<td align="left"><%= vo.getTranCode() %></td>		
		<td></td>
		<td align="left">
		            <%
    // tranCode가 "2"일 때만 링크 표시
    /* if ("2".equals(vo.getTranCode())) { */
    if ("배송중".equals(vo.getTranCode())) {
%>
    &nbsp;&nbsp;<a href="/updatePurchaseDelivery.do?prodNo=<%= vo.getPurchaseProd().getProdNo() %>&tranCode=3">[물건도착]</a>
<%
    }
%>
		<!-- 강사님 버전 <a href="/updateTranCode.do?tranNo=10012&tranCode=3">물건도착</a> -->

			
		</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	<% } %>

</table>

<!-- PageNavigation Start... -->
<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top:10px;">
	<tr>
		<td align="center">
		   <input type="hidden" id="currentPage" name="currentPage" value="1"/>
<% if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
	◀ 이전
<% }else{ %>
	<a href="javascript:fncGetPurchaseList('<%=resultPage.getCurrentPage()-1%>')">◀ 이전</a>
<% } %>

<% for(int i=resultPage.getBeginUnitPage(); i<= resultPage.getEndUnitPage(); i++){ %>
	<a href="javascript:fncGetPurchaseList('<%= i %>');"><%= i %></a>
<% } %>

<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
	이후 ▶
<% }else{ %>
	<a href="javascript:fncGetPurchaseList('<%=resultPage.getEndUnitPage()+1%>')">이후 ▶</a>
<% } %>
		
    	</td>
	</tr>
</table>
<!-- PageNavigation End... -->
</form>

</div>

</body>
</html>