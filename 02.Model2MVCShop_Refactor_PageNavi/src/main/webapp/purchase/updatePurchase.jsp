<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.model2.mvc.service.domain.*" %>

<%
	Purchase vo = (Purchase)request.getAttribute("vo");
%>	
<%
    // vo.getDivyDate()가 String 타입이라고 가정
    String divyDateStr = vo.getDivyDate(); 
    SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 원본 포맷
    SimpleDateFormat desiredFormat = new SimpleDateFormat("yyyy-MM-dd"); // 원하는 출력 포맷

    Date divyDate = null;
    String formattedDate = "";

    try {
        divyDate = originalFormat.parse(divyDateStr); // String을 Date로 변환
        formattedDate = desiredFormat.format(divyDate); // 원하는 포맷으로 변환
    } catch (Exception e) {
        e.printStackTrace(); // 예외 처리
    }
%>
<%-- <%
    // vo.getDivyDate()가 Date 객체일 경우
    Date divyDate = vo.getDivyDate(); 
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = sdf.format(divyDate);
%> --%>







<html>
<head>
<link rel="stylesheet" href="/css/admin.css" type="text/css">

<title>구매정보 수정</title>



</head>

<body bgcolor="#ffffff" text="#000000">

<form name="updatePurchase" method="post"	action="/updatePurchase.do?tranNo=<%= vo.getTranNo() %>">

<table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif"  width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매정보수정</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>

<table width="600" border="0" cellspacing="0" cellpadding="0"	align="center" style="margin-top: 13px;">
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">구매자아이디</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01"><%= vo.getBuyer().getUserId() %></td>
		<input type="hidden" name="buyerId" >
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">구매방법</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<select 	name="paymentOption" 	class="ct_input_g" style="width: 100px; height: 19px" 
							maxLength="20">
				<option value="1" selected="selected">현금구매</option>
				<option value="2">신용구매</option>
			</select>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">구매자이름</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="receiverName" 	class="ct_input_g" style="width: 100px; height: 19px" 
							maxLength="20" value="<%=vo.getReceiverName()%>" />
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">구매자 연락처</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="receiverPhone" class="ct_input_g" style="width: 100px; height: 19px" 
							maxLength="20" value="<%=vo.getReceiverPhone()%>" />
		</td>
	</tr>

	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">구매자주소</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="receiverAddr" class="ct_input_g" style="width: 100px; height: 19px" 
							maxLength="20" value="<%=vo.getDivyAddr() %>" />
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">구매요청사항</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="receiverRequest" 	class="ct_input_g" style="width: 100px; height: 19px" 
							maxLength="20" value="<%=vo.getDivyRequest()%>" />
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
    <td width="104" class="ct_write">배송희망일자</td>
    <td bgcolor="D6D6D6" width="1"></td>
    <td width="200" class="ct_write01">
        <!-- value에 vo.getDivyDate() 값을 설정, 사용자가 수정할 수 있도록 수정 -->
<!--         <input type="text" name="divyDate" class="ct_input_g" --> 
        <input type="date" name="divyDate" class="ct_input_g" 
               style="width: 100px; height: 19px" maxLength="20"
                value="<%= formattedDate %>" />
               <%-- value="<%= vo.getDivyDate() %>" /> --%>
        <!-- 달력 아이콘 버튼은 그대로 유지 -->
    <!--     <img src="../images/ct_icon_date.gif" width="15" height="15" 
             onclick="show_calendar('document.updatePurchase.divyDate', document.updatePurchase.divyDate.value)" /> -->
    </td>
</tr>

	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td width="53%"></td>
		<td align="right">
		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="17" height="23">
					<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
				</td>
				<!-- <td background="/images/ct_btnbg02.gif" class="ct_btn01"	style="padding-top: 3px;">
				<input type="submit" value="수정"/>
				</td> -->
				<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
					<button type="submit" style="all:unset; cursor:pointer;">수정</button>
				</td>

				<td width="14" height="23">
					<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
				</td>
				<td width="30"></td>
				<td width="17" height="23">
					<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
				</td>
				<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
					<a href="javascript:history.go(-1)">취소</a>
				</td>
				<td width="14" height="23">
					<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>

</body>
</html>