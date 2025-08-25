<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- /////////////////////// EL / JSTL 적용으로 주석 처리 ////////////////////////
<%@ page import="com.model2.mvc.service.domain.VolOffer" %>
<%
	VolOffer volOffer = (VolOffer)request.getAttribute("volOffer");
%>	/////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////// --%>

    <!-- 세션에서 volOffer 가져오기 -->
    <c:set var="volOffer" value="${sessionScope.volOffer}" />
<html>
<head>
<title>손길나눔상세보기</title>

<link rel="stylesheet" href="/css/site.css" type="text/css">

</head>

<body bgcolor="#ffffff" text="#000000">
	<%@ include file="/common/top.jspf"%>
<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif" width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">손길나눔조회</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:13px;">
	
	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
	
	<tr>
		<td width="104" class="ct_write">			작성자ID <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<%--  <td class="ct_write01"><%=volOffer.getPostId() %></td> --%>
		<td class="ct_write01">${volOffer.authorId}</td>
	</tr>

	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
	
	<tr>
		<td width="104" class="ct_write">			제목 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle" />		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${volOffer.title}</td>
	</tr>
	
	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
		
	<tr>		<td width="104" class="ct_write">	손길나눔 시작시간</td>		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${volOffer.startTime}</td>
	</tr>
	
	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
	
	<tr>		<td width="104" class="ct_write">	손길나눔 종료시간</td>		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${volOffer.endTime}</td>
	</tr>

	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
	
	<tr>		<td width="104" class="ct_write">손길나눔 내용 </td>		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${volOffer.content}</td>
	</tr>

	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
	
	<tr>		<td width="104" class="ct_write">전화번호</td>		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${volOffer.phone}</td>
	</tr>
	
	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
	
		<tr>		<td width="104" class="ct_write">지역</td>		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${volOffer.region}</td>
	</tr>
	
	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
	
		<tr>		<td width="104" class="ct_write">카테고리</td>		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${volOffer.category}</td>
	</tr>
	
	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top:10px;">
	<tr>
		<td width="53%"></td>
		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23"><img src="/images/ct_btnbg01.gif" width="17" height="23"></td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<%--<a href="/updateVolOfferView.do?postId=<%=volOffer.getPostId()%>">수정</a> --%>
						<a href="/updateVolOfferView.do?postId=${volOffer.postId}">수정</a>
					</td>
					<td width="14" height="23"><img src="/images/ct_btnbg03.gif" width="14" height="23"></td>
					<td width="30"></td>					
					<td width="17" height="23"><img src="/images/ct_btnbg01.gif" width="17" height="23"></td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:history.go(-1);">확인</a>
					</td>
					<td width="14" height="23"><img src="/images/ct_btnbg03.gif" width="14" height="23"></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
	  
</body>
<%@ include file="/common/footer.jspf" %>     <!-- 여기! -->
</html>