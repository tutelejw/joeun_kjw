<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- /////////////////////// EL / JSTL 적용으로 주석 처리 ////////////////////////
<%@ page import="com.model2.mvc.service.domain.Product" %>
<%
	Product product=(Product)request.getAttribute("product");
%>/////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////// --%>

<html>
<head>
<title>상품 정보 수정</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
function fncUpdateProduct() {
	// Form 유효성 검증
	var name=document.detailForm.prodName.value;
		
	document.detailForm.action='/updateProduct.do';
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

<%--<input type="hidden" name="prodNo" value="<%=product.getProdNo() %>"> --%>
<input type="hidden" name="prodNo" value="${product.prodNo }">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">상품정보수정</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37" />
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:13px;">
	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
	
	<tr>
		<td width="104" class="ct_write">상품명 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle" />
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<%--<td class="ct_write01"><%=product.getProdNo() %>	</td> --%>
		<td class="ct_write01">${product.prodName}	</td>
	</tr>
	
	<tr>		<td height="1" colspan="3" bgcolor="D6D6D6"></td>	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			상품상세정보 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle" />
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<%-- <input type="text" name="prodName" value="<%=product.getProdName() %>" class="ct_input_g" style="width:100px; height:19px"  maxLength="50" >--%>
			<input 	type="text" name="prodDetail" value="${product.prodDetail}" class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="50" >
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">주소</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<%--<input type="text" name="addr" value="<%=product.getAddr() %>" class="ct_input_g" style="width:370px; height:19px"  maxLength="100">--%>
			<input 	type="text" name="addr" value="${product.addr}" class="ct_input_g" 
							style="width:370px; height:19px"  maxLength="100">
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">휴대전화번호</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<select name="phone1" class="ct_input_g" style="width:50px; height:25px" 
							onChange="document.detailForm.phone2.focus();">
				<%--
					String phone1 = "";
					String phone2 = "";
					String phone3 = "";
					if( product.getPhone() != null){
						phone1 = product.getPhone().split("-")[0] ;
						phone2 = product.getPhone().split("-")[1] ;
						phone3 = product.getPhone().split("-")[2] ;
					}
											
				<option value="010" <%= phone1.equals("010") ? "selected" : "" %> >010</option>
				<option value="011" <%= phone1.equals("011") ? "selected" : "" %> >011</option>
				<option value="016" <%= phone1.equals("016") ? "selected" : "" %> >016</option>
				<option value="018" <%= phone1.equals("018") ? "selected" : "" %> >018</option>
				<option value="019" <%= phone1.equals("019") ? "selected" : "" %> >019</option>
				--%>
				
				<option value="010" ${ ! empty product.phone1 && product.phone1 == "010" ? "selected" : ""  } >010</option>
				<option value="011" ${ ! empty product.phone1 && product.phone1 == "011" ? "selected" : ""  } >011</option>
				<option value="016" ${ ! empty product.phone1 && product.phone1 == "016" ? "selected" : ""  } >016</option>
				<option value="018" ${ ! empty product.phone1 && product.phone1 == "018" ? "selected" : ""  } >018</option>
				<option value="019" ${ ! empty product.phone1 && product.phone1 == "019" ? "selected" : ""  } >019</option>
				
			</select>
			<%--  <input 	type="text" name="phone2" value="<%= phone2.equals("")? "" : phone2 %>" 
							class="ct_input_g" style="width:100px; height:19px"  maxLength="9" >
			- 
			<input 	type="text" name="phone3" value="<%= phone3.equals("")? "" : phone3 %>"  
							class="ct_input_g"  style="width:100px; height:19px"  maxLength="9" >
			<input type="hidden" name="phone" class="ct_input_g"  />     --%>
			
			<input 	type="text" name="phone2" value="${ ! empty product.phone2 ? product.phone2 : ''}" 
							class="ct_input_g" style="width:100px; height:19px"  maxLength="9" >
			- 
			<input 	type="text" name="phone3" value="${ ! empty product.phone3 ? product.phone3 : ''}"  
							class="ct_input_g"  style="width:100px; height:19px"  maxLength="9" >
							
			<input type="hidden" name="phone" class="ct_input_g"  />
		</td>
	</tr>

	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">이메일 </td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<%--<input type="text" name="email" value="<%=product.getEmail() %>" class="ct_input_g" style="width:100px; height:19px" onChange="check_email(this.form);">--%>
			<input 	type="text" name="email" value="${product.email}" class="ct_input_g" 
							style="width:100px; height:19px" onChange="check_email(this.form);">
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
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
						<a href="javascript:fncUpdateProduct();">수정</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23" />
					</td>
					<td width="30"></td>					
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23" />
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:resetData();">취소</a>
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
