<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- /////////////////////// EL / JSTL 적용으로 주석 처리 ////////////////////////
<%@ page import="com.model2.mvc.service.domain.VolOffer" %>
<%
	VolOffer volOffer=(VolOffer)request.getAttribute("volOffer");
%>/////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////// --%>

<html>
<head>
<title>손길 제공 수정</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
function fncUpdateVolOffer() {
	document.detailForm.action='/updateVolOffer.do';
	document.detailForm.submit();
}

function resetData() {
	document.detailForm.reset();
}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<form name="detailForm"  method="post" >

<%--<input type="hidden" name="postId" value="<%=volOffer.getPostId() %>"> --%>
<input type="hidden" name="postId" value="${volOffer.postId }">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">손길나눔 수정</td>					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">			<img src="/images/ct_ttl_img03.gif" width="12" height="37" />		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:13px;">
	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
	
	<tr>
		<td width="104" class="ct_write">			손길나눔No <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle" />		</td>
		<td bgcolor="D6D6D6" width="1"></td>		<td class="ct_write01">${volOffer.postId}	</td>
	</tr>
	
	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
	
	<tr>
		<td width="104" class="ct_write">			내용 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle" />		</td>
		<td bgcolor="D6D6D6" width="1"></td>		<td class="ct_write01">${volOffer.content}	</td>
	</tr>
	
	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
	
	<tr>
		<td width="104" class="ct_write">			연락처 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle" />		</td>
		<td bgcolor="D6D6D6" width="1"></td>		<td class="ct_write01">${volOffer.phone}	</td>
	</tr>

	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
	
	<tr>
		<td width="104" class="ct_write">			지역 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle" />		</td>
		<td bgcolor="D6D6D6" width="1"></td>		<td class="ct_write01">${volOffer.region}	</td>
	</tr>
	
	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td width="53%">	</td>
		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23" />
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncUpdateVolOffer();">수정</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23" />
					</td>
					<td width="30"></td>					
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23" />
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						 <a href="javascript:history.go(-1);">취소</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23" />
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

</form>

</body>
</html>
