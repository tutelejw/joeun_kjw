<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="com.model2.mvc.service.domain.*" %>


<%
	Product vo = (Product)request.getAttribute("vo");
	User user = (User) session.getAttribute("user");
	String userId = user.getUserId();       // 구매자 아이디
	String userName = user.getUserName();   // 구매자 이름
	String phone = user.getPhone();   // 구매자 이름
	out.println("<p style='color:green;'>[디버그] Product loaded: " + vo.getProdName() + "</p>");
	out.println("<p style='color:green;'>[디버그] User.getUserId loaded: " + user.getUserId() + "</p>");
	out.println("<p style='color:green;'>[디버그] User.getUserName loaded: " + user.getUserName() + "</p>");
	out.println("<p style='color:green;'>[디버그] User.getPhone loaded: " + user.getPhone() + "</p>");
	
%>	

<html>
<head>
<title>상품정보조회</title>
<p>구매자 ID: <%= userId %></p>
<p>구매자 이름: <%= userName %></p>
<p>연락처: <%= phone %></p>
<link rel="stylesheet" href="/css/admin.css" type="text/css">

</head>

<body bgcolor="#ffffff" text="#000000">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">상품구매</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif" width="12" height="37"></td>
	</tr>
</table>

<!-- form 시작: 구매 요청 시 필요한 모든 값들을 같이 전달 -->
<form action="/addPurchase.do" method="post">
<!-- 상품정보 테이블 시작 -->
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 13px;">
  <tr><td height="1" colspan="3" bgcolor="D6D6D6"></td></tr>

  <!-- 상품번호 -->
  <tr>
    <td width="104" class="ct_write">상품번호 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/></td>
    <td bgcolor="D6D6D6" width="1"></td>
    <td class="ct_write01">
      <%=vo.getProdNo() %>
      <input type="hidden" name="prodNo" value="<%=vo.getProdNo()%>"/>
    </td>
  </tr>
  <tr><td height="1" colspan="3" bgcolor="D6D6D6"></td></tr>

  <!-- 상품명 -->
<tr>
  <td width="104" class="ct_write">
    상품명 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
  </td>
  <td bgcolor="D6D6D6" width="1"></td>
  <td class="ct_write01"><%=vo.getProdName()%></td>
</tr>
<tr>
  <td height="1" colspan="3" bgcolor="D6D6D6"></td>
</tr>

<!-- 상품상세정보 -->
<tr>
  <td width="104" class="ct_write">
    상품상세정보 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
  </td>
  <td bgcolor="D6D6D6" width="1"></td>
  <td class="ct_write01"><%=vo.getProdDetail()%></td>
</tr>
<tr>
  <td height="1" colspan="3" bgcolor="D6D6D6"></td>
</tr>

<!-- 제조일자 -->
<tr>
  <td width="104" class="ct_write">제조일자</td>
  <td bgcolor="D6D6D6" width="1"></td>
  <td class="ct_write01"><%=vo.getManuDate()%></td>
</tr>
<tr>
  <td height="1" colspan="3" bgcolor="D6D6D6"></td>
</tr>

<!-- 가격 -->
<tr>
  <td width="104" class="ct_write">가격</td>
  <td bgcolor="D6D6D6" width="1"></td>
  <td class="ct_write01"><%=vo.getPrice()%></td>
</tr>
<tr>
  <td height="1" colspan="3" bgcolor="D6D6D6"></td>
</tr>

<!-- 등록일자 -->
<tr>
  <td width="104" class="ct_write">등록일자</td>
  <td bgcolor="D6D6D6" width="1"></td>
  <td class="ct_write01">
    <%
      java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
      out.print(sdf.format(vo.getRegDate()));
    %>
  </td>
</tr>
<tr>
  <td height="1" colspan="3" bgcolor="D6D6D6"></td>
</tr>
	<tr>
		<td width="104" class="ct_write">
			구매자아이디 <img 	src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01"><%= userId %></td>
		<input type="hidden" name="userId" value="<%= userId %>" />
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">구매방법</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<select 	name="paymentOption"		class="ct_input_g" 
							style="width: 100px; height: 19px" maxLength="20">
				<option value="1" selected="selected">현금구매</option>
				<option value="2">신용구매</option>
			</select>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write"><%= userName %>></td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input type="text" name="userName" 	class="ct_input_g" 
						style="width: 100px; height: 19px" maxLength="20" value="<%= userName %>" />
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">구매자연락처</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="phone" class="ct_input_g" 
							style="width: 100px; height: 19px" maxLength="20" value="<%= phone %>" />
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">구매자주소</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="receiverAddr" class="ct_input_g" 
							style="width: 100px; height: 19px" maxLength="20" 	value="111" />
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">구매요청사항</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input		type="text" name="receiverRequest" 	class="ct_input_g" 
							style="width: 100px; height: 19px" maxLength="20" />
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">배송희망일자</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td width="200" class="ct_write01">
<!--			<input 	type="text" readonly="readonly" name="receiverDate" class="ct_input_g" 
							style="width: 100px; height: 19px" maxLength="20"/>
 			<img 	src="../images/ct_icon_date.gif" width="15" height="15"	
						onclick="show_calendar('document.addPurchase.receiverDate', document.addPurchase.receiverDate.value)"/>
-->
				<input type="date" name="receiverDate" class="ct_input_g" style="width: 130px; height: 19px"/>

		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
</table>










<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top:10px;">
	<tr>
		<td width="53%"></td>
		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0">

 
	 				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<!--  구매버튼 추가함 25/09/03 추가함  -->
					<!-- 
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
					<input type="submit" value="구매">
					</td>
					 -->
					
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
					<button type="submit" style="all:unset; cursor:pointer;">구매</button>
					</td>
					
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
					<td width="30"></td>					
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:history.go(-1);">확인</a>
					</td>
					<td width="14" height="23"><img src="/images/ct_btnbg03.gif" width="14" height="23"></td>
				</tr>
 
			</table>
		</td>
	</tr>
</table>

</form>

</body>
</html>